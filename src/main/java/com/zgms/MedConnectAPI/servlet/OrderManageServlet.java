package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.Order;
import com.zgms.MedConnectAPI.dao.OrderDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderManageServlet", value = "/OrderManageServlet")
public class OrderManageServlet extends HttpServlet {
    private OrderDao orderDao = new OrderDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Order> orderList = orderDao.getAllOrders();
        String result = new Gson().toJson(orderList);
        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            int serviceId = Integer.parseInt(request.getParameter("sid"));
            String userId = request.getParameter("uid");
            orderDao.addOrder(serviceId, userId);
        } else if ("delete".equals(action)) {
            String orderId = request.getParameter("oid");
            orderDao.deleteOrder(orderId);
        } else if ("update".equals(action)) {
            String orderId = request.getParameter("oid");
            int serviceId = Integer.parseInt(request.getParameter("sid"));
            String userId = request.getParameter("uid");
            Order order = new Order(orderId, serviceId, userId);
            orderDao.updateOrder(order);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}