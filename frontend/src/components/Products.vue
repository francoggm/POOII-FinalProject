<template>
  <div class="product-container">
    <h2>Products</h2>

    <ul v-if="products.length">
      <li v-for="product in products" :key="product.id">
        Nome: {{ product.nome }} - Preço: ${{ product.preco }} - Descrição: {{ product.descricao }} - Peso: {{ product.peso }} 
      </li>
    </ul>
    <p v-else>No products found.</p>

    <h3>Add New Product</h3>
    <form @submit.prevent="addProduct">
      <label>
        Nome:
        <input type="text" v-model="newProduct.nome" required />
      </label>
      <label>
        Descrição:
        <input type="text" v-model="newProduct.descricao" required />
      </label>
      <label>
        Preço:
        <input type="number" v-model="newProduct.preco" required />
      </label>
      <label>
        Peso:
        <input type="number" v-model="newProduct.peso" required />
      </label>
      <button type="submit">Adicionar produto</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      products: [],
      newProduct: {
        nome: '',
        preco: 0,
        descricao: '',
        peso: 0
      },
    };
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await fetch('http://127.0.0.1:8080/produto');
        this.products = await response.json();
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    },
    
    async addProduct() {
      try {
        const formData = new FormData();
        formData.append('nome', this.newProduct.nome);
        formData.append('preco', this.newProduct.preco);
        formData.append('descricao', this.newProduct.descricao);
        formData.append('peso', this.newProduct.peso);

        const response = await fetch('http://127.0.0.1:8080/produto', {
          method: 'POST',
          body: formData,
        });

        if (response.ok) {
          const addedProduct = await response.json();
          this.products.push(addedProduct);
          this.newProduct = { nome: '', descricao: '', preco: 0, peso: 0 };
        } else {
          console.error('Error adding product:', response.statusText);
        }
      } catch (error) {
        console.error('Error adding product:', error);
      }
    },
  },
  mounted() {
    this.fetchProducts();
  },
};
</script>

<style scoped>
.product-container {
  max-width: 400px;
  margin: auto;
  font-family: Arial, sans-serif;
}

ul {
  list-style-type: none;
  padding: 0;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  display: block;
  margin-bottom: 10px;
}

button {
  margin-top: 10px;
}
</style>
