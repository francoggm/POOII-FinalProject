const apiUrl = 'http://localhost:8080/usuario';

async function fetchUsers() {
    try {
        const response = await fetch(apiUrl);
        const users = await response.json();
        renderUsers(users);
    } catch (error) {
        console.error('Erro ao buscar usuários:', error);
    }
}

function renderUsers(users) {
    const tableBody = document.getElementById('user-table-body');
    tableBody.innerHTML = ''; // Limpa a tabela
    users.forEach(user => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.nome}</td>
            <td>${user.email}</td>
            <td>${user.senha}</td>
            <td class="actions">
                <button onclick="editUser(${user.id}, '${user.nome}', '${user.email}', '${user.senha}')">Editar</button>
                <button onclick="deleteUser(${user.id})">Deletar</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function saveUser(event) {
    event.preventDefault();

    const id = document.getElementById('user-id').value;
    const name = document.getElementById('user-name').value;
    const email = document.getElementById('user-email').value;
    const password = document.getElementById('user-password').value;

    // Determina se é criação ou atualização
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${apiUrl}/${id}` : apiUrl;

    try {
        await fetch(url, {
            method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ nome: name, email, senha: password })
        });
        resetForm();
        fetchUsers();
    } catch (error) {
        console.error('Erro ao salvar usuário:', error);
    }
}

function editUser(id, name, email, password) {
    document.getElementById('user-id').value = id;
    document.getElementById('user-name').value = name;
    document.getElementById('user-email').value = email;
    document.getElementById('user-password').value = password;
}

async function deleteUser(id) {
    try {
        await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
        fetchUsers();
    } catch (error) {
        console.error('Erro ao deletar usuário:', error);
    }
}

function resetForm() {
  document.getElementById('user-id').value = null;
  document.getElementById('user-name').value = null;
  document.getElementById('user-email').value = null;
  document.getElementById('user-password').value = null;
}

// Torne as funções editUser e deleteUser acessíveis globalmente
window.editUser = editUser;
window.deleteUser = deleteUser;

document.getElementById('user-form').addEventListener('submit', saveUser);
fetchUsers();
