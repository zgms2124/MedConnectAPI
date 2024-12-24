<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">部门管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索部门 (按ID或名称)">
    </div>

    <!-- 部门列表 -->
    <h2 class="mb-3">部门列表</h2>
    <table class="table table-bordered table-striped" id="departTable">
        <thead class="table-dark">
        <tr>
            <th>部门ID</th>
            <th>部门名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 部门数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加部门按钮 -->
    <button class="btn btn-success" id="addDepartButton">添加新部门</button>

    <!-- 添加部门 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加部门</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addDepartForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addDepartName" class="form-label">部门名称</label>
                            <input type="text" id="addDepartName" name="departName" class="form-control" required>
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

    <!-- 编辑部门 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑部门</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editDepartForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editDepartId" class="form-label">部门ID (不可修改)</label>
                            <input type="text" id="editDepartId" name="departId" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editDepartName" class="form-label">部门名称</label>
                            <input type="text" id="editDepartName" name="departName" class="form-control" required>
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
<script src="js/depart_manage.js"></script>
</body>
</html>