<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>健康推荐信息管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">健康推荐信息管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索推荐信息 (按ID或内容)">
    </div>

    <!-- 推荐信息列表 -->
    <h2 class="mb-3">推荐信息列表</h2>
    <table class="table table-bordered table-striped" id="recommendationTable">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>推荐信息</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 推荐信息数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加推荐信息按钮 -->
    <button class="btn btn-success" id="addRecommendationButton">添加推荐信息</button>

    <!-- 添加推荐信息 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加推荐信息</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addRecommendationForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addAdvice" class="form-label">推荐信息</label>
                            <input type="text" id="addAdvice" name="advice" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-success">添加</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- 编辑推荐信息 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑推荐信息</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editRecommendationForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editId" class="form-label">ID (不可修改)</label>
                            <input type="text" id="editId" name="id" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editAdvice" class="form-label">推荐信息</label>
                            <input type="text" id="editAdvice" name="advice" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">保存更改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/health_recommendation_manage.js"></script>
</body>
</html>