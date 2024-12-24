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

    // 加载日程数据
    function loadSchedules() {
        fetch('http://localhost:8080/MedConnectAPI_war/ScheduleManageServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('scheduleTable').querySelector('tbody');
                const fragment = document.createDocumentFragment();
                tableBody.innerHTML = '';
                data.forEach(schedule => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(schedule.sid)}</td>
                        <td>${escapeHtml(schedule.did)}</td>
                        <td>${escapeHtml(schedule.time)}</td>
                        <td>${escapeHtml(schedule.price)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editSchedule('${escapeHtml(schedule.sid)}', '${escapeHtml(schedule.did)}', '${escapeHtml(schedule.time)}', '${escapeHtml(schedule.price)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteSchedule(${escapeHtml(schedule.sid)})">删除</button>
                        </td>
                    `;
                    fragment.appendChild(row);
                });
                tableBody.appendChild(fragment);
            })
            .catch(error => console.error('Error loading schedules:', error));
    }

    // 搜索过滤
    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#scheduleTable tbody tr');
        rows.forEach(row => {
            const cells = row.querySelectorAll('td');
            let match = false;
            cells.forEach(cell => {
                if (cell.textContent.toLowerCase().includes(input)) {
                    match = true;
                }
            });
            row.style.display = match ? '' : 'none';
        });
    });

    // 删除日程
    window.deleteSchedule = function (sid) {
        fetch(`http://localhost:8080/MedConnectAPI_war/ScheduleManageServlet?sid=${sid}&action=delete`, {
            method: 'POST', headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
        })
            .then(() => loadSchedules())
            .catch(error => console.error('Error deleting schedule:', error));
    };

    // 编辑日程
    window.editSchedule = function (sid, did, time, price) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editSid').value = sid;
        document.getElementById('editDid').value = did;
        document.getElementById('editTime').value = time;
        document.getElementById('editPrice').value = price;
        editModal.show();
    };

    // 提交编辑表单
    document.getElementById('editScheduleForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const sid = document.getElementById('editSid').value;
        const did = document.getElementById('editDid').value;
        const time = document.getElementById('editTime').value;
        const price = document.getElementById('editPrice').value;

        fetch('http://localhost:8080/MedConnectAPI_war/ScheduleManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=update&sid=${sid}&did=${did}&time=${encodeURIComponent(time)}&price=${price}`,
        })
            .then(() => {
                document.getElementById('editScheduleForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
                loadSchedules();
            })
            .catch(error => console.error('Error editing schedule:', error));
    });

    // 添加新日程按钮点击事件
    document.getElementById('addScheduleButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    // 提交添加日程表单
    document.getElementById('addScheduleForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const did = document.getElementById('addDid').value;
        const time = encodeURIComponent(document.getElementById('addTime').value);
        const price = encodeURIComponent(document.getElementById('addPrice').value);

        fetch('http://localhost:8080/MedConnectAPI_war/ScheduleManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=add&did=${did}&time=${time}&price=${price}`,
        })
            .then(() => {
                loadSchedules();
                document.getElementById('addScheduleForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
            })
            .catch(error => console.error('Error adding schedule:', error));
    });

    // 加载初始日程数据
    loadSchedules();
});