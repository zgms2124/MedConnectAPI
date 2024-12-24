document.addEventListener('DOMContentLoaded', function () {
    function escapeHtml(unsafe) {
        return String(unsafe)
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }

    function loadDoctors() {
        fetch('http://localhost:8080/MedConnectAPI_war/DoctorManageServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('doctorTable').querySelector('tbody');
                tableBody.innerHTML = '';
                data.forEach(doctor => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(doctor.did)}</td>
                        <td>${escapeHtml(doctor.dname)}</td>
                        <td>${escapeHtml(doctor.dlevel)}</td>
                        <td>${escapeHtml(doctor.dinfo)}</td>
                        <td>${escapeHtml(doctor.departid)}</td>
                        <td>${escapeHtml(doctor.sex === 1 ? '男' : '女')}</td>
                        <td>${escapeHtml(doctor.detail)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editDoctor(${doctor.did}, '${escapeHtml(doctor.dname)}', '${escapeHtml(doctor.dlevel)}', '${escapeHtml(doctor.dinfo)}', ${doctor.departid}, ${doctor.sex}, '${escapeHtml(doctor.detail)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteDoctor(${doctor.did})">删除</button>
                        </td>
                    `;
                    tableBody.appendChild(row);
                });
            })
            .catch(error => console.error('Error loading doctors:', error));
    }

    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#doctorTable tbody tr');
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    window.deleteDoctor = function (did) {
        fetch(`http://localhost:8080/MedConnectAPI_war/DoctorManageServlet?did=${did}&action=delete`, {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'}
        })
            .then(() => loadDoctors())
            .catch(error => console.error('Error deleting doctor:', error));
    };

    window.editDoctor = function (did, dname, dlevel, dinfo, departid, sex, detail) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editDid').value = did;
        document.getElementById('editDname').value = dname;
        document.getElementById('editDlevel').value = dlevel;
        document.getElementById('editDinfo').value = dinfo;
        document.getElementById('editDepartid').value = departid;
        document.getElementById('editSex').value = sex;
        document.getElementById('editDetail').value = detail;
        editModal.show();
    };

    document.getElementById('editDoctorForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const did = document.getElementById('editDid').value;
        const dname = encodeURIComponent(document.getElementById('editDname').value);
        const dlevel = encodeURIComponent(document.getElementById('editDlevel').value);
        const dinfo = encodeURIComponent(document.getElementById('editDinfo').value);
        const departid = encodeURIComponent(document.getElementById('editDepartid').value);
        const sex = encodeURIComponent(document.getElementById('editSex').value);
        const detail = encodeURIComponent(document.getElementById('editDetail').value);

        fetch('http://localhost:8080/MedConnectAPI_war/DoctorManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=update&did=${did}&dname=${dname}&dlevel=${dlevel}&dinfo=${dinfo}&departid=${departid}&sex=${sex}&detail=${detail}`,
        })
            .then(() => {
                document.getElementById('editDoctorForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
                loadDoctors();
            })
            .catch(error => console.error('Error editing doctor:', error));
    });

    document.getElementById('addDoctorButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    document.getElementById('addDoctorForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const dname = encodeURIComponent(document.getElementById('addDname').value);
        const dlevel = encodeURIComponent(document.getElementById('addDlevel').value);
        const dinfo = encodeURIComponent(document.getElementById('addDinfo').value);
        const departid = encodeURIComponent(document.getElementById('addDepartid').value);
        const sex = encodeURIComponent(document.getElementById('addSex').value);
        const detail = encodeURIComponent(document.getElementById('addDetail').value);

        fetch('http://localhost:8080/MedConnectAPI_war/DoctorManageServlet', {
            method: 'POST',
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
            body: `action=add&dname=${dname}&dlevel=${dlevel}&dinfo=${dinfo}&departid=${departid}&sex=${sex}&detail=${detail}`,
        })
            .then(() => {
                loadDoctors();
                document.getElementById('addDoctorForm').reset();
                bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
            })
            .catch(error => console.error('Error adding doctor:', error));
    });

    loadDoctors();
});