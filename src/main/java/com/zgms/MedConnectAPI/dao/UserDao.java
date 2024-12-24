package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.User;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private QueryRunner queryRunner = new QueryRunner();

    public User select(String uid) {
        User user = new User();
        try {
            user = queryRunner.query(DbUtil.getConnection(), "select * from user where uid=?", new BeanHandler<User>(User.class), uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int addUser(User user) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "INSERT INTO user (uid, uname, upsw) VALUES (?, ?, ?)", user.getUid(), user.getUname(), user.getUpsw());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public User login(String uid, String upsw) {
        User user = select(uid);
        if (user != null && user.getUpsw().equals(upsw)) {
            return user;
        } else {
            return null;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = queryRunner.query(DbUtil.getConnection(), "SELECT * FROM user", new BeanListHandler<>(User.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int updateUser(User user) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "UPDATE user SET uname=?, upsw=? WHERE uid=?", user.getUname(), user.getUpsw(), user.getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteUser(String uid) {
        int res = 0;
        try {
            res = queryRunner.update(DbUtil.getConnection(), "DELETE FROM user WHERE uid=?", uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
