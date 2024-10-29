<template>
  <div class="product-container">
    <h2>Products</h2>

    <ul v-if="products.length">
      <li v-for="product in products" :key="product.id">
        {{ product.name }} - {{ product.description }} - ${{ product.price }}
      </li>
    </ul>
    <p v-else>No products found.</p>

    <h3>Add New Product</h3>
    <form @submit.prevent="addProduct">
      <label>
        Name:
        <input type="text" v-model="newProduct.name" required />
      </label>
      <label>
        Description:
        <input type="text" v-model="newProduct.description" required />
      </label>
      <label>
        Price:
        <input type="number" v-model="newProduct.price" required />
      </label>
      <button type="submit">Add Product</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      products: [],
      newProduct: {
        name: '',
        description: '',
        price: 0,
      },
    };
  },
  methods: {
    async fetchProducts() {
      try {
        const response = await fetch('https://localhost:8080/produto');
        this.products = await response.json();
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    },
    
    async addProduct() {
      try {
        const response = await fetch('https://localhost:8080/produto', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(this.newProduct),
        });

        if (response.ok) {
          const addedProduct = await response.json();
          this.products.push(addedProduct);
          this.newProduct = { name: '', description: '', price: 0 };
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
