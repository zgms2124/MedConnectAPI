package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.Order;
import com.zgms.MedConnectAPI.bean.OrderDetail;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private QueryRunner queryRunner = new QueryRunner();

    public int addOrder(int sid, String uid) {
        String oid = sid + "#" + uid;
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "INSERT INTO myOrder (oid, sid, uid) VALUES (?, ?, ?)", oid, sid, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM myOrder";
            orders = queryRunner.query(DbUtil.getConnection(), sql, new BeanListHandler<>(Order.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public int updateOrder(Order order) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "UPDATE myorder SET oid=?, sid=?, uid=? WHERE oid=?", order.getSid() + "#" + order.getUid(), order.getSid(), order.getUid(), order.getOid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteOrder(String oid) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "DELETE FROM myOrder WHERE oid=?", oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<OrderDetail> getOrdersByUid(String uid) {
        String sql = "SELECT o.oid, s.time, d.dname, de.departname " +
                "FROM myOrder o " +
                "JOIN schedule s ON o.sid = s.sid " +
                "JOIN doctor d ON s.did = d.did " +
                "JOIN department de ON d.departid = de.departid " +
                "WHERE o.uid = ?";
        try {
            return queryRunner.query(DbUtil.getConnection(), sql, new BeanListHandler<>(OrderDetail.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

