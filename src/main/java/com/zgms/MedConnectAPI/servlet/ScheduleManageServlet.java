package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.Schedule;
import com.zgms.MedConnectAPI.dao.ScheduleDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ScheduleManageServlet", value = "/ScheduleManageServlet")
public class ScheduleManageServlet extends HttpServlet {
    private ScheduleDao scheduleDao = new ScheduleDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Schedule> schedules = scheduleDao.getAllSchedules(); // Assuming 0 as default department ID or modify as needed
        String result = new Gson().toJson(schedules);
        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            int did = Integer.parseInt(request.getParameter("did"));
            String time = request.getParameter("time");
            float price = Float.parseFloat(request.getParameter("price"));
            Schedule schedule = new Schedule(did, time, price); // Assuming auto-generated ID
            scheduleDao.addSchedule(schedule);
        } else if ("delete".equals(action)) {
            int sid = Integer.parseInt(request.getParameter("sid"));
            scheduleDao.deleteSchedule(sid);
        } else if ("update".equals(action)) {
            int sid = Integer.parseInt(request.getParameter("sid"));
            int did = Integer.parseInt(request.getParameter("did"));
            String time = request.getParameter("time");
            float price = Float.parseFloat(request.getParameter("price"));
            Schedule schedule = new Schedule(sid, did, time, price);
            scheduleDao.updateSchedule(schedule);
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}