const productApiUrl = 'http://localhost:8080/produto';
const categoryApiUrl = 'http://localhost:8080/categoria';

// Função para buscar produtos
async function fetchProducts() {
    try {
        const response = await fetch(productApiUrl);
        const products = await response.json();
        renderProducts(products);
    } catch (error) {
        console.error('Erro ao buscar produtos:', error);
    }
}

// Renderizar produtos na tabela
function renderProducts(products) {
    const tableBody = document.getElementById('product-table-body');
    tableBody.innerHTML = '';

    products.forEach(product => {
        const categories = product.categorias.map(cat => cat.nome).join(', ');
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.id}</td>
            <td>${product.nome}</td>
            <td>${product.valor.toFixed(2)}</td>
            <td>${product.descricao}</td>
            <td>${product.peso} kg</td>
            <td>${categories}</td>
            <td>
                <button onclick="editProduct(${product.id}, '${product.nome}', ${product.valor}, '${product.descricao}', ${product.peso}, ${categoriesToString(product.categorias)})">Editar</button>
                <button onclick="deleteProduct(${product.id})">Deletar</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

function categoriesToString(categories) {
  return JSON.stringify(categories).replace(/"([^"]+)":/g, '$1:').replaceAll("\"", "'")
}

// Função para buscar e preencher as categorias no select
async function fetchCategoriesForSelect() {
    try {
        const response = await fetch(categoryApiUrl);
        const categories = await response.json();
        const categorySelect = document.getElementById('product-categories');
        
        categorySelect.innerHTML = '';
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.id;
            option.textContent = category.nome;
            categorySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao buscar categorias:', error);
    }
}

// Função para salvar ou atualizar produto
async function saveProduct(event) {
    event.preventDefault();

    const id = document.getElementById('product-id').value;
    const name = document.getElementById('product-name').value;
    const value = parseFloat(document.getElementById('product-value').value);
    const description = document.getElementById('product-description').value;
    const weight = parseFloat(document.getElementById('product-weight').value);
    const categoryIds = Array.from(document.getElementById('product-categories').selectedOptions).map(option => parseInt(option.value));

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${productApiUrl}/${id}` : productApiUrl;

    try {
        await fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: name, valor: value, descricao: description, peso: weight, categorias: categoryIds }),
        });
        
        resetForm();
        fetchProducts();
    } catch (error) {
        console.error('Erro ao salvar produto:', error);
    }
}

// Função para editar produto
function editProduct(id, name, value, description, weight, categories) {
    console.log(categories)

    document.getElementById('product-id').value = id;
    document.getElementById('product-name').value = name;
    document.getElementById('product-value').value = value;
    document.getElementById('product-description').value = description;
    document.getElementById('product-weight').value = weight;

    const categorySelect = document.getElementById('product-categories');
    Array.from(categorySelect.options).forEach(option => {
        option.selected = categories.some(cat => cat.id == option.value);
    });
}

// Função para deletar produto
async function deleteProduct(id) {
    try {
        await fetch(`${productApiUrl}/${id}`, { method: 'DELETE' });
        fetchProducts();
    } catch (error) {
        console.error('Erro ao deletar produto:', error);
    }
}

function resetForm() {
    document.getElementById('product-id').value = null;
    document.getElementById('product-name').value = '';
    document.getElementById('product-value').value = '';
    document.getElementById('product-description').value = '';
    document.getElementById('product-weight').value = '';
    Array.from(document.getElementById('product-categories').options).forEach(option => option.selected = false);
}

document.getElementById('product-form').addEventListener('submit', saveProduct);

window.editProduct = editProduct;
window.deleteProduct = deleteProduct;

fetchCategoriesForSelect();
fetchProducts();
