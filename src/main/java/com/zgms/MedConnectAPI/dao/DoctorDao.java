package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.Doctor;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DoctorDao {
    private QueryRunner queryRunner = new QueryRunner();

    public List<Doctor> selectDoctorsByDepartid(int departid) {
        List<Doctor> doctors = null;
        try {
            doctors = queryRunner.query(DbUtil.getConnection(), "select * from doctor where departid=?;", new BeanListHandler<Doctor>(Doctor.class), departid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public Doctor selectDoctorById(int did) {
        Doctor doctor = null;
        try {
            doctor = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM doctor WHERE did = ?", new BeanHandler<>(Doctor.class), did);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public int addDoctor(Doctor doctor) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "INSERT INTO doctor (dname, dlevel, dinfo, departid, sex, detail) VALUES (?, ?, ?, ?, ?, ?)", doctor.getDname(), doctor.getDlevel(), doctor.getDinfo(), doctor.getDepartid(), doctor.getSex(), doctor.getDetail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = null;
        try {
            doctors = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM doctor", new BeanListHandler<>(Doctor.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public int updateDoctor(Doctor doctor) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "UPDATE doctor SET dname=?, dlevel=?, dinfo=?, departid=?, sex=?, detail=? WHERE did=?", doctor.getDname(), doctor.getDlevel(), doctor.getDinfo(), doctor.getDepartid(), doctor.getSex(), doctor.getDetail(), doctor.getDid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteDoctor(int did) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "DELETE FROM doctor WHERE did = ?", did);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
