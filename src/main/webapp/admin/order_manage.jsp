<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>订单管理后台</title>
    <link rel="stylesheet" href="css/styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mb-4">订单管理后台</h1>

    <!-- 搜索框 -->
    <div class="mb-3">
        <input type="text" id="searchInput" class="form-control" placeholder="搜索订单 (按ID或用户ID)">
    </div>

    <!-- 订单列表 -->
    <h2 class="mb-3">订单列表</h2>
    <table class="table table-bordered table-striped" id="orderTable">
        <thead class="table-dark">
        <tr>
            <th>订单ID</th>
            <th>服务ID</th>
            <th>用户ID</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <!-- 订单数据通过 JavaScript 动态加载 -->
        </tbody>
    </table>

    <!-- 添加订单按钮 -->
    <button class="btn btn-success" id="addOrderButton">添加新订单</button>

    <!-- 添加订单 Modal -->
    <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">添加订单</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="addOrderForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="addServiceId" class="form-label">服务ID</label>
                            <input type="text" id="addServiceId" name="serviceId" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="addUserId" class="form-label">用户ID</label>
                            <input type="text" id="addUserId" name="userId" class="form-control" required>
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

    <!-- 编辑订单 Modal -->
    <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editModalLabel">编辑订单</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="editOrderForm">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editOrderId" class="form-label">订单ID (不可修改)</label>
                            <input type="text" id="editOrderId" name="orderId" class="form-control" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editServiceId" class="form-label">服务ID</label>
                            <input type="text" id="editServiceId" name="serviceId" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="editUserId" class="form-label">用户ID</label>
                            <input type="text" id="editUserId" name="userId" class="form-control" required>
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
<script src="js/order_manage.js"></script>
</body>
</html>