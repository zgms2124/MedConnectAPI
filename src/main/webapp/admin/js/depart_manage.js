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

    // 加载部门数据
    function loadDeparts() {
        fetch('http://localhost:8080/MedConnectAPI_war/DepartManageServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('departTable').querySelector('tbody');
                const fragment = document.createDocumentFragment();
                tableBody.innerHTML = '';
                data.forEach(depart => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(depart.departId)}</td>
                        <td>${escapeHtml(depart.departName)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editDepart('${escapeHtml(depart.departId)}', '${escapeHtml(depart.departName)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteDepart('${escapeHtml(depart.departId)}')">删除</button>
                        </td>
                    `;
                    fragment.appendChild(row);
                });
                tableBody.appendChild(fragment);
            })
            .catch(error => console.error('Error loading departments:', error));
    }

    // 搜索过滤
    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#departTable tbody tr');
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    // 删除部门
    window.deleteDepart = function (departId) {
        fetch(`http://localhost:8080/MedConnectAPI_war/DepartManageServlet?departId=${encodeURIComponent(departId)}&action=delete`, {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
        })
            .then(() => loadDeparts())
            .catch(error => console.error('Error deleting department:', error));
    };

    // 编辑部门
    window.editDepart = function (departId, departName) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editDepartId').value = departId;
        document.getElementById('editDepartName').value = departName;
        editModal.show();
    };

    // 提交编辑表单
    document.getElementById('editDepartForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const departId = document.getElementById('editDepartId').value;
        const departName = encodeURIComponent(document.getElementById('editDepartName').value);

        fetch('http://localhost:8080/MedConnectAPI_war/DepartManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=update&departId=${departId}&departName=${departName}`,
        })
            .then(() => {
                document.getElementById('editDepartForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
                loadDeparts();
            })
            .catch(error => console.error('Error editing department:', error));
    });

    // 添加新部门按钮点击事件
    document.getElementById('addDepartButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    // 提交添加部门表单
    document.getElementById('addDepartForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const departName = encodeURIComponent(document.getElementById('addDepartName').value);

        fetch('http://localhost:8080/MedConnectAPI_war/DepartManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=add&departName=${departName}`,
        })
            .then(() => {
                loadDeparts();
                document.getElementById('addDepartForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
            })
            .catch(error => console.error('Error adding department:', error));
    });


    // 加载初始部门数据
    loadDeparts();
});