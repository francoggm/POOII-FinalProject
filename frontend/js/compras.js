const purchaseApiUrl = 'http://localhost:8080/compra';
const userApiUrl = 'http://localhost:8080/usuario';
const productApiUrl = 'http://localhost:8080/produto';

// Função para buscar compras
async function fetchPurchases() {
    try {
        const response = await fetch(purchaseApiUrl);
        const purchases = await response.json();
        renderPurchases(purchases);
    } catch (error) {
        console.error('Erro ao buscar compras:', error);
    }
}

function renderPurchases(purchases) {
  const tableBody = document.getElementById('purchase-table-body');
  tableBody.innerHTML = ''; // Limpa a tabela

  purchases.forEach(purchase => {
      // Calcula o valor total da compra
      const totalValue = purchase.produtoCompraList.reduce((sum, pc) => sum + (pc.valor * pc.quantidade), 0);

      // Converte os produtos para exibição
      const products = purchase.produtoCompraList
          .map(pc => `${pc.produto.nome} (Qtd: ${pc.quantidade}, R$${pc.valor.toFixed(2)})`)
          .join(', ');

      const row = document.createElement('tr');
      row.innerHTML = `
          <td>${purchase.id}</td>
          <td>${new Date(purchase.dataCompra).toLocaleDateString()}</td>
          <td>${purchase.parcelas}</td>
          <td>${purchase.tipoPagamento}</td>
          <td>${products}</td>
          <td>R$${totalValue.toFixed(2)}</td> <!-- Exibe o valor total -->
          <td>
              <button onclick="deletePurchase(${purchase.id})">Deletar</button>
          </td>
      `;
      tableBody.appendChild(row);
  });
}

// Função para deletar compra
async function deletePurchase(id) {
    try {
        await fetch(`${purchaseApiUrl}/${id}`, { method: 'DELETE' });
        fetchPurchases();
    } catch (error) {
        console.error('Erro ao deletar compra:', error);
    }
}

// Função para buscar usuários e produtos
async function fetchUsersAndProducts() {
    try {
        const [usersResponse, productsResponse] = await Promise.all([
            fetch(userApiUrl),
            fetch(productApiUrl)
        ]);

        const users = await usersResponse.json();
        const products = await productsResponse.json();

        populateUserSelect(users);
        populateProductOptions(products);
    } catch (error) {
        console.error('Erro ao buscar usuários e produtos:', error);
    }
}

// Popula o select de usuários
function populateUserSelect(users) {
    const userSelect = document.getElementById('user-id');
    users.forEach(user => {
        const option = document.createElement('option');
        option.value = user.id;
        option.textContent = user.nome;
        userSelect.appendChild(option);
    });
}

// Adiciona as opções de produto dinamicamente
function populateProductOptions(products) {
    window.availableProducts = products; // Salva globalmente para reutilizar
}

// Adiciona um novo produto ao formulário
function addProductToForm() {
    const productContainer = document.getElementById('product-container');
    const productItem = document.createElement('div');
    productItem.classList.add('product-item');

    const productSelect = document.createElement('select');
    productSelect.required = true;
    window.availableProducts.forEach(product => {
        const option = document.createElement('option');
        option.value = product.id;
        option.textContent = product.nome;
        productSelect.appendChild(option);
    });

    const quantityInput = document.createElement('input');
    quantityInput.type = 'number';
    quantityInput.placeholder = 'Quantidade';
    quantityInput.min = 1;
    quantityInput.required = true;

    const removeButton = document.createElement('button');
    removeButton.textContent = 'Remover';
    removeButton.type = 'button';
    removeButton.onclick = () => productContainer.removeChild(productItem);

    productItem.appendChild(productSelect);
    productItem.appendChild(quantityInput);
    productItem.appendChild(removeButton);
    productContainer.appendChild(productItem);
}

// Função para criar uma compra
async function savePurchase(event) {
    event.preventDefault();

    const userId = parseInt(document.getElementById('user-id').value);
    const installments = parseInt(document.getElementById('purchase-installments').value);
    const paymentType = document.getElementById('purchase-payment-type').value;

    const productItems = Array.from(document.querySelectorAll('.product-item'));
    const products = productItems.map(item => ({
        produtoId: parseInt(item.querySelector('select').value),
        quantidade: parseInt(item.querySelector('input').value),
    }));

    if (!userId || !installments || !paymentType || products.length === 0) {
        alert('Preencha todos os campos corretamente.');
        return;
    }

    const purchaseData = {
        usuarioId: userId,
        parcelas: installments,
        tipoPagamento: paymentType,
        produtos: products,
    };

    try {
        await fetch(purchaseApiUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(purchaseData),
        });

        resetForm();
        fetchPurchases();
    } catch (error) {
        console.error('Erro ao salvar compra:', error);
    }
}

// Reseta o formulário
function resetForm() {
    document.getElementById('purchase-form').reset();
    document.getElementById('product-container').innerHTML = '';
}

// Inicializa a página
fetchPurchases();
fetchUsersAndProducts();

window.deletePurchase = deletePurchase;

// Eventos
document.getElementById('add-product').addEventListener('click', addProductToForm);
document.getElementById('purchase-form').addEventListener('submit', savePurchase);
