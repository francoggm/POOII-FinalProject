const apiUrl = 'http://localhost:8080/categoria';

// Função para buscar categorias
async function fetchCategories() {
    try {
        const response = await fetch(apiUrl);
        const categories = await response.json();
        renderCategories(categories);
    } catch (error) {
        console.error('Erro ao buscar categorias:', error);
    }
}

// Renderizar categorias na tabela
function renderCategories(categories) {
    const tableBody = document.getElementById('category-table-body');
    tableBody.innerHTML = ''; // Limpa a tabela
    categories.forEach(category => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${category.id}</td>
            <td>${category.nome}</td>
            <td>${category.descricao}</td>
            <td>
                <button onclick="editCategory(${category.id}, '${category.nome}', '${category.descricao}')">Editar</button>
                <button onclick="deleteCategory(${category.id})">Deletar</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Função para criar ou atualizar uma categoria
async function saveCategory(event) {
    event.preventDefault();

    const id = document.getElementById('category-id').value;
    const name = document.getElementById('category-name').value;
    const description = document.getElementById('category-description').value;

    const method = id ? 'PUT' : 'POST';
    const url = id ? `${apiUrl}/${id}` : apiUrl;

    try {
        await fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: name, descricao: description }),
        });
        
        resetForm();
        fetchCategories();
    } catch (error) {
        console.error('Erro ao salvar categoria:', error);
    }
}

// Função para editar categoria
function editCategory(id, name, description) {
    document.getElementById('category-id').value = id;
    document.getElementById('category-name').value = name;
    document.getElementById('category-description').value = description;
}

// Função para deletar categoria
async function deleteCategory(id) {
    try {
        await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
        fetchCategories();
    } catch (error) {
        console.error('Erro ao deletar categoria:', error);
    }
}

function resetForm() {
    document.getElementById('category-id').value = null;
    document.getElementById('category-name').value = null;
    document.getElementById('category-description').value = null;
}

// Adiciona evento ao formulário
document.getElementById('category-form').addEventListener('submit', saveCategory);

// Torna as funções editCategory e deleteCategory acessíveis globalmente
window.editCategory = editCategory;
window.deleteCategory = deleteCategory;

// Busca inicial de categorias
fetchCategories();
