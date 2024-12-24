<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>医生管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">医生管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索医生 (按ID或名称)">
    </div>

    <!-- 医生列表 -->
    <h2 class="mb-3">医生列表</h2>
    <table class="table table-bordered table-striped" id="doctorTable">
        <thead class="table-dark">
        <tr>
            <th>医生ID</th>
            <th>医生名称</th>
            <th>级别</th>
            <th>信息</th>
            <th>部门ID</th>
            <th>性别</th>
            <th>详细信息</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 医生数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加医生按钮 -->
    <button class="btn btn-success" id="addDoctorButton">添加新医生</button>

    <!-- 添加医生 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加医生</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addDoctorForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addDname" class="form-label">医生名称</label>
                            <input type="text" id="addDname" name="dname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addDlevel" class="form-label">级别</label>
                            <input type="text" id="addDlevel" name="dlevel" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addDinfo" class="form-label">信息</label>
                            <textarea id="addDinfo" name="dinfo" class="form-control" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="addDepartid" class="form-label">部门ID</label>
                            <input type="text" id="addDepartid" name="departid" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addSex" class="form-label">性别</label>
                            <select id="addSex" name="sex" class="form-select" required>
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="addDetail" class="form-label">详细信息</label>
                            <input type="text" id="addDetail" name="detail" class="form-control" required>
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

    <!-- 编辑医生 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑医生</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editDoctorForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editDid" class="form-label">医生ID (不可修改)</label>
                            <input type="text" id="editDid" name="did" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editDname" class="form-label">医生名称</label>
                            <input type="text" id="editDname" name="dname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editDlevel" class="form-label">级别</label>
                            <input type="text" id="editDlevel" name="dlevel" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editDinfo" class="form-label">信息</label>
                            <textarea id="editDinfo" name="dinfo" class="form-control" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="editDepartid" class="form-label">部门ID</label>
                            <input type="text" id="editDepartid" name="departid" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editSex" class="form-label">性别</label>
                            <select id="editSex" name="sex" class="form-select" required>
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="editDetail" class="form-label">详细信息</label>
                            <input type="text" id="editDetail" name="detail" class="form-control" required>
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
<script src="js/doctor_manage.js"></script>
</body>
</html>