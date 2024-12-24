package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.Schedule;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {
    private QueryRunner queryRunner = new QueryRunner();

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try {
            schedules = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM schedule", new BeanListHandler<>(Schedule.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public List<Schedule> selectSchedulesByDid(int did) {
        List<Schedule> schedules = null;
        try {
            schedules = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM schedule WHERE did = ?", new BeanListHandler<>(Schedule.class), did);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

   public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (did, time, price) VALUES (?, ?, ?)";
        try {
            queryRunner.update(DbUtil.getConnection(), sql, schedule.getDid(), schedule.getTime(), schedule.getPrice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchedule(int sid) {
        String sql = "DELETE FROM schedule WHERE sid = ?";
        try {
            queryRunner.update(DbUtil.getConnection(), sql, sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSchedule(Schedule schedule) {
        String sql = "UPDATE schedule SET did = ?, time = ?, price = ? WHERE sid = ?";
        try {
            queryRunner.update(DbUtil.getConnection(), sql, schedule.getDid(), schedule.getTime(), schedule.getPrice(), schedule.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}