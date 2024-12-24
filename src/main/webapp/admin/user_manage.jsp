<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">用户管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索用户 (按ID或用户名)">
    </div>

    <!-- 用户列表 -->
    <h2 class="mb-3">用户列表</h2>
    <table class="table table-bordered table-striped" id="userTable">
        <thead class="table-dark">
        <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 用户数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加用户按钮 -->
    <button class="btn btn-success" id="addUserButton">添加新用户</button>

    <!-- 添加用户 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加用户</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addUserForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addUid" class="form-label">用户ID</label>
                            <input type="text" id="addUid" name="uid" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addUname" class="form-label">用户名</label>
                            <input type="text" id="addUname" name="uname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addUpsw" class="form-label">密码</label>
                            <input type="password" id="addUpsw" name="upsw" class="form-control" required>
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


    <!-- 编辑用户 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑用户</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editUserForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editUid" class="form-label">用户ID (不可修改)</label>
                            <input type="text" id="editUid" name="uid" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editUname" class="form-label">用户名</label>
                            <input type="text" id="editUname" name="uname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editUpsw" class="form-label">密码</label>
                            <input type="password" id="editUpsw" name="upsw" class="form-control" required>
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
<script src="js/user_manage.js"></script>
</body>
</html>
