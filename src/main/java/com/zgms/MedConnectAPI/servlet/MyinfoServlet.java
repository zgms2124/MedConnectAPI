package com.zgms.MedConnectAPI.servlet;

import com.zgms.MedConnectAPI.bean.OrderDetail;
import com.zgms.MedConnectAPI.dao.OrderDao;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MyinfoServlet", value = "/myinfoServlet")
public class MyinfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 接收参数
        String uid = request.getParameter("uid");
        System.out.println("MyinfoServlet: Received request for user ID: " + uid);

        OrderDao orderDao = new OrderDao();
        List<OrderDetail> orders = orderDao.getOrdersByUid(uid);

        PrintWriter writer = response.getWriter();
        String result = "";

        if (orders == null || orders.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            result = "{\"message\":\"No orders found for user.\"}";
            System.out.println("MyinfoServlet: No orders found for user ID: " + uid);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            result = new Gson().toJson(orders);
            System.out.println("MyinfoServlet: Found " + orders.size() + " orders for user ID: " + uid);
        }

        writer.write(result);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}