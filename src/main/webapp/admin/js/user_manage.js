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

    // 加载用户数据
    function loadUsers() {
        fetch('http://localhost:8080/MedConnectAPI_war/UserManageServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('userTable').querySelector('tbody');
                const fragment = document.createDocumentFragment();
                tableBody.innerHTML = '';
                data.forEach(user => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(user.uid)}</td>
                        <td>${escapeHtml(user.uname)}</td>
                        <td>${escapeHtml(user.upsw)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editUser('${escapeHtml(user.uid)}', '${escapeHtml(user.uname)}', '${escapeHtml(user.upsw)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteUser('${escapeHtml(user.uid)}')">删除</button>
                        </td>
                    `;
                    fragment.appendChild(row);
                });
                tableBody.appendChild(fragment);
            })
            .catch(error => console.error('Error loading users:', error));
    }

    // 搜索过滤
    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#userTable tbody tr');
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    // 删除用户
    window.deleteUser = function (uid) {
        fetch(`http://localhost:8080/MedConnectAPI_war/UserManageServlet?uid=${encodeURIComponent(uid)}&action=delete`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' }
        })
        .then(() => loadUsers())
        .catch(error => console.error('Error deleting user:', error));
    };

    // 编辑用户
    window.editUser = function (uid, uname, upsw) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editUid').value = uid;
        document.getElementById('editUname').value = uname;
        document.getElementById('editUpsw').value = upsw;
        editModal.show();
    };

    // 提交编辑表单
    document.getElementById('editUserForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const uid = document.getElementById('editUid').value;
        const uname = encodeURIComponent(document.getElementById('editUname').value);
        const upsw = encodeURIComponent(document.getElementById('editUpsw').value);

        fetch('http://localhost:8080/MedConnectAPI_war/UserManageServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
            body: `action=edit&uid=${uid}&uname=${uname}&upsw=${upsw}`,
        })
        .then(() => {
            document.getElementById('editUserForm').reset();
            bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
            loadUsers();
        })
        .catch(error => console.error('Error editing user:', error));
    });

    // 添加新用户按钮点击事件
    document.getElementById('addUserButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    // 提交添加用户表单
    document.getElementById('addUserForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const uid = document.getElementById('addUid').value;
        const uname = encodeURIComponent(document.getElementById('addUname').value);
        const upsw = encodeURIComponent(document.getElementById('addUpsw').value);

        fetch('http://localhost:8080/MedConnectAPI_war/UserManageServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
            body: `action=add&uid=${uid}&uname=${uname}&upsw=${upsw}`,
        })
        .then(() => {
            loadUsers();
            document.getElementById('addUserForm').reset();
            bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
        })
        .catch(error => console.error('Error adding user:', error));
    });

    // 加载初始用户数据
    loadUsers();
});