<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>日程安排管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">日程安排管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索日程 (按ID或时间)">
    </div>

    <!-- 部门列表 -->
    <h2 class="mb-3">日程列表</h2>
    <table class="table table-bordered table-striped" id="scheduleTable">
        <thead class="table-dark">
        <tr>
            <th>日程ID</th>
            <th>部门ID</th>
            <th>时间</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 部门数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加日程按钮 -->
    <button class="btn btn-success" id="addScheduleButton">添加新日程</button>

    <!-- 添加日程 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加日程</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addScheduleForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addDid" class="form-label">部门ID</label>
                            <input type="text" id="addDid" name="did" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addTime" class="form-label">时间</label>
                            <input type="text" id="addTime" name="time" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addPrice" class="form-label">价格</label>
                            <input type="text" id="addPrice" name="price" class="form-control" required>
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

    <!-- 编辑日程 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑日程</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editScheduleForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editSid" class="form-label">日程ID (不可修改)</label>
                            <input type="text" id="editSid" name="sid" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editDid" class="form-label">部门ID</label>
                            <input type="text" id="editDid" name="did" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editTime" class="form-label">时间</label>
                            <input type="text" id="editTime" name="time" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editPrice" class="form-label">价格</label>
                            <input type="text" id="editPrice" name="price" class="form-control" required>
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
<script src="js/schedule_manage.js"></script>
</body>
</html>