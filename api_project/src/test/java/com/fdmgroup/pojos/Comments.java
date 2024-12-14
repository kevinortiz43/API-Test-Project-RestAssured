package com.fdmgroup.pojos;

public class Comments {

    private int id;
    private int userid;
    private String body;
    private int foodId;

    public Comments() {
        super();
    }

    public Comments(int id, int userid, String body, int foodId) {
        this.id = id;
        this.userid = userid;
        this.body = body;
        this.foodId = foodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

}
