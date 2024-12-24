<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理后台主入口</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="admin/css/styles.css">
</head>
<body>
<div class="container mt-5">
    <!-- 导航栏 -->
    <ul class="nav nav-tabs nav-justified mb-3">
        <li class="nav-item">
            <a class="nav-link" id="userManageTab" data-bs-toggle="tab" href="#userManage">用户管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="departManageTab" data-bs-toggle="tab" href="#departManage">部门管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="doctorManageTab" data-bs-toggle="tab" href="#doctorManage">医生管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="orderManageTab" data-bs-toggle="tab" href="#orderManage">订单管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="scheduleManageTab" data-bs-toggle="tab" href="#scheduleManage">日程安排管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="healthRecommendationManageTab" data-bs-toggle="tab" href="#healthRecommendationManage">健康推荐信息管理</a>
        </li>
    </ul>

    <div class="tab-content">
        <div id="userManage" class="tab-pane fade">
            <iframe src="admin/user_manage.jsp" width="100%" height="600px"></iframe>
        </div>
        <div id="departManage" class="tab-pane fade">
            <iframe src="admin/depart_manage.jsp" width="100%" height="600px"></iframe>
        </div>
        <div id="doctorManage" class="tab-pane fade">
            <iframe src="admin/doctor_manage.jsp" width="100%" height="600px"></iframe>
        </div>
        <div id="orderManage" class="tab-pane fade">
            <iframe src="admin/order_manage.jsp" width="100%" height="600px"></iframe>
        </div>
        <div id="scheduleManage" class="tab-pane fade">
            <iframe src="admin/schedule_manage.jsp" width="100%" height="600px"></iframe>
        </div>
        <div id="healthRecommendationManage" class="tab-pane fade">
            <iframe src="admin/health_recommendation_manage.jsp" width="100%" height="600px"></iframe>
        </div>
    </div>

</div>
<!-- Hidden input to store the active tab -->
<input type="hidden" id="activeTab">
<!-- JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="index.js"></script> <!-- Link to the JS file -->
</body>
</html>