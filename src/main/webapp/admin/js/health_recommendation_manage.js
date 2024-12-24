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

    // 加载推荐信息数据
    function loadRecommendations() {
        fetch('http://localhost:8080/MedConnectAPI_war/healthRecommendationServlet')
            .then(response => response.json())
            .then(data => {
                const tableBody = document.getElementById('recommendationTable').querySelector('tbody');
                const fragment = document.createDocumentFragment();
                tableBody.innerHTML = '';
                data.forEach(recommendation => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${escapeHtml(recommendation.id)}</td>
                        <td>${escapeHtml(recommendation.advice)}</td>
                        <td>
                            <button class="btn btn-primary btn-sm" onclick="editRecommendation(${escapeHtml(recommendation.id)}, '${escapeHtml(recommendation.advice)}')">编辑</button>
                            <button class="btn btn-danger btn-sm" onclick="deleteRecommendation(${escapeHtml(recommendation.id)})">删除</button>
                        </td>
                    `;
                    fragment.appendChild(row);
                });
                tableBody.appendChild(fragment);
            })
            .catch(error => console.error('Error loading recommendations:', error));
    }

    // 搜索过滤
    document.getElementById('searchInput').addEventListener('keyup', function () {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#recommendationTable tbody tr');
        rows.forEach(row => {
            const text = row.innerText.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    // 删除推荐信息
    window.deleteRecommendation = function (id) {
        fetch(`http://localhost:8080/MedConnectAPI_war/healthRecommendationServlet?id=${encodeURIComponent(id)}&action=delete`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' }
        })
        .then(() => loadRecommendations())
        .catch(error => console.error('Error deleting recommendation:', error));
    };

    // 编辑推荐信息
    window.editRecommendation = function (id, advice) {
        const editModal = new bootstrap.Modal(document.getElementById('editModal'));
        document.getElementById('editId').value = id;
        document.getElementById('editAdvice').value = advice;
        editModal.show();
    };

    // 提交编辑表单
    document.getElementById('editRecommendationForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const id = document.getElementById('editId').value;
        const advice = encodeURIComponent(document.getElementById('editAdvice').value);

        fetch('http://localhost:8080/MedConnectAPI_war/healthRecommendationServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
            body: `action=update&id=${id}&advice=${advice}`,
        })
        .then(() => {
            document.getElementById('editRecommendationForm').reset();
            bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
            loadRecommendations();
        })
        .catch(error => console.error('Error editing recommendation:', error));
    });

    // 添加新推荐信息按钮点击事件
    document.getElementById('addRecommendationButton').addEventListener('click', function () {
        const addModal = new bootstrap.Modal(document.getElementById('addModal'));
        addModal.show();
    });

    // 提交添加推荐信息表单
    document.getElementById('addRecommendationForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const advice = encodeURIComponent(document.getElementById('addAdvice').value);

        fetch('http://localhost:8080/MedConnectAPI_war/healthRecommendationServlet', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
            body: `action=add&advice=${advice}`,
        })
        .then(() => {
            loadRecommendations();
            document.getElementById('addRecommendationForm').reset();
            bootstrap.Modal.getInstance(document.getElementById('addModal')).hide();
        })
        .catch(error => console.error('Error adding recommendation:', error));
    });

    // 加载初始推荐信息数据
    loadRecommendations();
});