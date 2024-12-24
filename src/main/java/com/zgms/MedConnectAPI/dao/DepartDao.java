package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.Depart;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartDao {

    private QueryRunner queryRunner = new QueryRunner();

    public Depart selectById(int departId) {
        Depart depart = new Depart();
        try {
            depart = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM department WHERE departId=?", new BeanHandler<Depart>(Depart.class), departId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depart;
    }

    public int addDepart(Depart depart) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "INSERT INTO department (departName) VALUES (?)", depart.getDepartName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Depart> getAllDeparts() {
        List<Depart> departs = new ArrayList<>();
        try {
            departs = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM department", new BeanListHandler<>(Depart.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departs;
    }

    public int updateDepart(Depart depart) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "UPDATE department SET departName=? WHERE departId=?", depart.getDepartName(), depart.getDepartId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteDepart(int departId) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "DELETE FROM department WHERE departId=?", departId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}