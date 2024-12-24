package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.HealthRecommendation;
import com.zgms.MedConnectAPI.dao.HealthRecommendationDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "HealthRecommendationServlet", value = "/healthRecommendationServlet")
public class HealthRecommendationServlet extends HttpServlet {
    private HealthRecommendationDao healthRecommendationDao = new HealthRecommendationDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<HealthRecommendation> recommendations = healthRecommendationDao.getHealthRecommendations();
        Gson gson = new Gson();
        String recommendationsJson = gson.toJson(recommendations);
        PrintWriter writer = response.getWriter();
        writer.write(recommendationsJson);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addRecommendation(request, response);
                break;
            case "delete":
                deleteRecommendation(request, response);
            case "update":
                updateRecommendation(request, response);
                break;
            default:
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Invalid action\"}");
        }
    }

    private void addRecommendation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String advice = request.getParameter("advice");
        HealthRecommendation recommendation = new HealthRecommendation(0, advice); // Assuming ID is auto-generated
        int result = healthRecommendationDao.addRecommendation(recommendation);
        if (result > 0) {
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Recommendation added successfully\"}");
        } else {
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Failed to add recommendation\"}");
        }
    }

    private void updateRecommendation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String advice = request.getParameter("advice");
        HealthRecommendation recommendation = new HealthRecommendation(id, advice);
        int result = healthRecommendationDao.updateRecommendation(recommendation);
        if (result > 0) {
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Recommendation updated successfully\"}");
        } else {
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Failed to update recommendation\"}");
        }
    }

    private void deleteRecommendation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = healthRecommendationDao.deleteRecommendation(id);
        if (result > 0) {
            response.getWriter().write("{\"status\":\"success\",\"message\":\"Recommendation deleted successfully\"}");
        } else {
            response.getWriter().write("{\"status\":\"error\",\"message\":\"Failed to delete recommendation\"}");
        }
    }
}