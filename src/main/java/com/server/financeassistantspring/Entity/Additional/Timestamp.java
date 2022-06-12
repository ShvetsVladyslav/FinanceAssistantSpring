package com.server.financeassistantspring.Entity.Additional;

public class Timestamp {
    private String timestamp1;
    private String timestamp2;
    private String timestamp3;
    private String timestamp4;

    public String getTimestamp1() {
        return timestamp1 + " 00:00:00";
    }

    public void setTimestamp1(String timestamp1) {
        this.timestamp1 = timestamp1;
    }

    public String getTimestamp2() {
        return timestamp2 + " 00:00:00";
    }

    public void setTimestamp2(String timestamp2) {
        this.timestamp2 = timestamp2;
    }

    public String getTimestamp3() {
        return timestamp3 + " 00:00:00";
    }

    public void setTimestamp3(String timestamp3) {
        this.timestamp3 = timestamp3;
    }

    public String getTimestamp4() {
        return timestamp4 + " 00:00:00";
    }

    public void setTimestamp4(String timestamp4) {
        this.timestamp4 = timestamp4;
    }
}
