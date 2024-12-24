package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.Depart;
import com.zgms.MedConnectAPI.bean.User;
import com.zgms.MedConnectAPI.dao.DepartDao;
import com.zgms.MedConnectAPI.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DepartManageServlet", value = "/DepartManageServlet")
public class DepartManageServlet extends HttpServlet {
    private DepartDao departDao = new DepartDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Depart> departList = departDao.getAllDeparts();
        String result = new Gson().toJson(departList);
        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String departName = request.getParameter("departName");
            departDao.addDepart(new Depart(departName));
        } else if ("delete".equals(action)) {
            int departId = Integer.parseInt(request.getParameter("departId"));
            departDao.deleteDepart(departId);
        } else if ("update".equals(action)) {
            int departId = Integer.parseInt(request.getParameter("departId"));
            String departName = request.getParameter("departName");
            departDao.updateDepart(new Depart(departId, departName));
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}