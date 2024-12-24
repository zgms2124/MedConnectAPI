package com.zgms.MedConnectAPI.bean;

public class Order {
    private String oid;
    private int sid;
    private String uid;

    // 无参构造函数
    public Order() {}

    // 全参构造函数
    public Order(String oid, int sid, String uid) {
        this.oid = oid;
        this.sid = sid;
        this.uid = uid;
    }

    // Getter 和 Setter 方法
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", sid=" + sid +
                ", uid='" + uid + '\'' +
                '}';
    }
}