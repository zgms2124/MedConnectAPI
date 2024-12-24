package com.zgms.MedConnectAPI.dao;

import com.zgms.MedConnectAPI.bean.HealthRecommendation;
import com.zgms.MedConnectAPI.utils.DbUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class HealthRecommendationDao {
    private QueryRunner queryRunner = new QueryRunner();

    public List<HealthRecommendation> getHealthRecommendations() {
        List<HealthRecommendation> recommendations = null;
        try {
            String sql = "SELECT * FROM health_recommendations ORDER BY RAND() LIMIT 4";
            recommendations = queryRunner.query(DbUtil.getConnection(), sql, new BeanListHandler<>(HealthRecommendation.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recommendations;
    }

    public int addRecommendation(HealthRecommendation recommendation) {
        String sql = "INSERT INTO health_recommendations (advice) VALUES (?)";
        try {
            return queryRunner.update(DbUtil.getConnection(), sql, recommendation.getAdvice());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateRecommendation(HealthRecommendation recommendation) {
        String sql = "UPDATE health_recommendations SET advice = ? WHERE id = ?";
        try {
            return queryRunner.update(DbUtil.getConnection(), sql, recommendation.getAdvice(), recommendation.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteRecommendation(int id) {
        String sql = "DELETE FROM health_recommendations WHERE id = ?";
        try {
            return queryRunner.update(DbUtil.getConnection(), sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}