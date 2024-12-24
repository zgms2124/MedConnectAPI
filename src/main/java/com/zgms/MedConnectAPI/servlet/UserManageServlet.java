package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.User;
import com.zgms.MedConnectAPI.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserManageServlet", value = "/UserManageServlet")
public class UserManageServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addUser(request, response);
                    break;
                case "edit":
                    editUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    response.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid action\"}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
        response.setContentType("application/json");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(new Gson().toJson(users));
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String upsw = request.getParameter("upsw");

        User user = new User(uid, uname, upsw);
        int result = userDao.addUser(user);
        response.getWriter().write("{\"status\":\"success\",\"message\":\"User added successfully\"}");
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String upsw = request.getParameter("upsw");

        User user = new User(uid, uname, upsw);
        int result = userDao.updateUser(user);
        response.getWriter().write("{\"status\":\"success\",\"message\":\"User updated successfully\"}");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        int result = userDao.deleteUser(uid);
        response.getWriter().write("{\"status\":\"success\",\"message\":\"User deleted successfully\"}");
    }
}