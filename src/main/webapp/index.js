document.addEventListener('DOMContentLoaded', function () {
    // 获取存储当前激活Tab的隐藏输入元素
    var activeTab = document.getElementById('activeTab');
    // 尝试从localStorage获取激活的Tab值
    var activeTabValue = localStorage.getItem('activeTab');

    // 如果存在激活的Tab值，则激活对应的Tab和内容区域
    if (activeTabValue) {
        var activeTabElement = document.querySelector('#' + activeTabValue + 'Tab');
        if (activeTabElement) {
            activeTabElement.classList.add('active');
            var correspondingContent = document.getElementById(activeTabValue);
            if (correspondingContent) {
                correspondingContent.classList.add('show', 'active');
            }
        }
    } else {
        // 如果没有存储的值，则默认激活第一个Tab
        document.querySelector('.nav-link').classList.add('active');
        document.getElementById('userManage').classList.add('show', 'active');
    }

    // 为所有的导航链接添加点击事件监听器
    document.querySelectorAll('.nav-link').forEach(function (tab) {
        tab.addEventListener('click', function (event) {
            // 阻止默认的锚点跳转行为
            event.preventDefault();
            // 获取点击的Tab的href属性值，并去掉'#'符号
            var targetHref = event.target.getAttribute('href').substring(1);
            // 更新localStorage的值为新激活的Tab
            localStorage.setItem('activeTab', targetHref);
            // 移除其他Tab的激活状态
            document.querySelectorAll('.nav-link').forEach(function (tab) {
                tab.classList.remove('active');
            });
            // 移除其他内容区域的激活状态
            document.querySelectorAll('.tab-pane').forEach(function (pane) {
                pane.classList.remove('show', 'active');
            });
            // 激活被点击的Tab
            event.target.classList.add('active');
            // 激活对应的内容区域
            document.getElementById(targetHref).classList.add('show', 'active');
        });
    });
});