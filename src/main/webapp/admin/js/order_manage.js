document.addEventListener('DOMContentLoaded', function () {
    // 防止 HTML 注入
    function escapeHtml(unsafe) {
        return String(unsafe)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }

    // 加载订单数据
    function loadOrders() {
        fetch('http://localhost:8080/MedConnectAPI_war/OrderManageServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('orderTable').querySelector('tbody');
                const fragment = document.createDocumentFragment();
                tableBody.innerHTML = '';
                data.forEach(order => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(order.oid)}</td>
                        <td>${escapeHtml(order.sid)}</td>
                        <td>${escapeHtml(order.uid)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editOrder('${escapeHtml(order.oid)}', '${escapeHtml(order.sid)}', '${escapeHtml(order.uid)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteOrder('${escapeHtml(order.oid)}')">删除</button>
                        </td>
                    `;
                    fragment.appendChild(row);
                });
                tableBody.appendChild(fragment);
            })
            .catch(error => console.error('Error loading orders:', error));
    }

    // 搜索过滤
    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#orderTable tbody tr');
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    // 删除订单
    window.deleteOrder = function (orderId) {
        fetch(`http://localhost:8080/MedConnectAPI_war/OrderManageServlet?oid=${encodeURIComponent(orderId)}&action=delete`, {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
        })
            .then(() => loadOrders())
            .catch(error => console.error('Error deleting order:', error));
    };

    // 编辑订单
    window.editOrder = function (orderId, serviceId, userId) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editOrderId').value = orderId;
        document.getElementById('editServiceId').value = serviceId;
        document.getElementById('editUserId').value = userId;
        editModal.show();
    };

    // 提交编辑表单
    document.getElementById('editOrderForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const orderId = document.getElementById('editOrderId').value;
        const serviceId = encodeURIComponent(document.getElementById('editServiceId').value);
        const userId = encodeURIComponent(document.getElementById('editUserId').value);

        fetch('http://localhost:8080/MedConnectAPI_war/OrderManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=update&oid=${orderId}&sid=${serviceId}&uid=${userId}`,
        })
            .then(() => {
                document.getElementById('editOrderForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
                loadOrders();
            })
            .catch(error => console.error('Error editing order:', error));
    });

    // 添加新订单按钮点击事件
    document.getElementById('addOrderButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    // 提交添加订单表单
    document.getElementById('addOrderForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const sid = encodeURIComponent(document.getElementById('addServiceId').value);
        const uid = encodeURIComponent(document.getElementById('addUserId').value);

        fetch('http://localhost:8080/MedConnectAPI_war/OrderManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=add&sid=${sid}&uid=${uid}`,
        })
            .then(() => {
                loadOrders();
                document.getElementById('addOrderForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
            })
            .catch(error => console.error('Error adding order:', error));
    });

    // 删除订单
    window.deleteOrder = function (oid) {
        fetch(`http://localhost:8080/MedConnectAPI_war/OrderManageServlet?oid=${encodeURIComponent(oid)}&action=delete`, {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
        })
            .then(() => loadOrders())
            .catch(error => console.error('Error deleting order:', error));
    };

    // 加载初始订单数据
    loadOrders();
});