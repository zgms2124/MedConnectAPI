package com.zgms.MedConnectAPI.servlet;

import com.google.gson.Gson;
import com.zgms.MedConnectAPI.bean.Doctor;
import com.zgms.MedConnectAPI.dao.DoctorDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorManageServlet", value = "/DoctorManageServlet")
public class DoctorManageServlet extends HttpServlet {
    private DoctorDao doctorDao = new DoctorDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        List<Doctor> doctorList = doctorDao.getAllDoctors();
        String result = new Gson().toJson(doctorList);
        response.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String dname = request.getParameter("dname");
            String dlevel = request.getParameter("dlevel");
            String dinfo = request.getParameter("dinfo");
            int departid = Integer.parseInt(request.getParameter("departid"));
            int sex = Integer.parseInt(request.getParameter("sex"));
            String detail = request.getParameter("detail");
            doctorDao.addDoctor(new Doctor(0, dname, dlevel, dinfo, departid, sex, detail));
        } else if ("delete".equals(action)) {
            int did = Integer.parseInt(request.getParameter("did"));
            doctorDao.deleteDoctor(did);
        } else if ("update".equals(action)) {
            int did = Integer.parseInt(request.getParameter("did"));
            String dname = request.getParameter("dname");
            String dlevel = request.getParameter("dlevel");
            String dinfo = request.getParameter("dinfo");
            int departid = Integer.parseInt(request.getParameter("departid"));
            int sex = Integer.parseInt(request.getParameter("sex"));
            String detail = request.getParameter("detail");
            doctorDao.updateDoctor(new Doctor(did, dname, dlevel, dinfo, departid, sex, detail));
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }
}