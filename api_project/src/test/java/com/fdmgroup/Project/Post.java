package com.fdmgroup.Project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fdmgroup.pojos.Comments;
import com.fdmgroup.pojos.Foods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Post {
    private static String access_token = "";

    @BeforeClass
    public void authentication() {
        String credential = """
                {
                    "email": "techie@email.com",
                    "password": "techie"
                }
                """;

        access_token = given()
                .contentType(ContentType.JSON)
                .with()
                .body(credential)
                .when()
                .post("http://localhost:3000/auth/login")
                .then()
                .extract().path("access_token");

        System.out.println("Token" + access_token);
    }

    @Test
    public static void performGetAllFood() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .log().all()
                .when()
                .get("http://localhost:3000/foods")
                .then()
                .log().all()
                .assertThat()
                .header("Content-Type", is("application/json; charset=utf-8"))
                .statusCode(200)
                .extract().response();

    }

    @Test
    public static void performGetAllComments() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .log().all()
                .when()
                .get("http://localhost:3000/comments")
                .then()
                .log().all()
                .assertThat()
                .header("Content-Type", is("application/json; charset=utf-8"))
                .statusCode(200)
                .extract().response();
    }

    @Test
    public static void performGetAllManagers() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .log().all()
                .when()
                .get("http://localhost:3000/managers")
                .then()
                .log().all()
                .assertThat()
                .header("Content-Type", is("application/json; charset=utf-8"))
                .statusCode(200)
                .extract().response();
    }

    @Test
    public static void performAdd(String name, double price) {

        int latestId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("http://localhost:3000/foods/")
                .jsonPath()
                .getInt("id.max()");

        int newId = latestId + 1;

        Foods postContent = new Foods(newId, name, price);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .with()
                .body(postContent)
                .when()
                .post("http://localhost:3000/foods/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public static void performUpdateOnPrice(int id, double price) {

        String currentName = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("http://localhost:3000/foods/" + id)
                .jsonPath()
                .getString("name");

        Foods updatedContent = new Foods(id, currentName, price);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .with()
                .body(updatedContent)
                .when()
                .put("http://localhost:3000/foods/" + id)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public static void performAddCommentWithFoodId(int userid, String body, int foodId) {

        int latestId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("http://localhost:3000/comments/")
                .jsonPath()
                .getInt("id.max()");

        int newId = latestId + 1;

        Comments postContent = new Comments(newId, userid, body, foodId);

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .with()
                .body(postContent)
                .when()
                .post("http://localhost:3000/comments/")
                .then()
                .log().all()
                .assertThat()
                .statusCode(201);

    }

    @Test
    public static void performCommentDelete(int id) {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .delete("http://localhost:3000/comments/" + id)
                .then()

                .assertThat()
                .statusCode(200);

        performGetAllComments();
    }

    @Test
    public static void performGetCommentwithUserIdFoodId(int userid, int foodId) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .log().all()
                .when()
                .get("http://localhost:3000/comments?userid=" + userid + "&foodId=" + foodId)
                .then()
                .log().all()
                .assertThat()
                .header("Content-Type", is("application/json; charset=utf-8"))
                .statusCode(200)
                .extract().response();
    }

    @Test
    public static void performUpdateStaffUnderAManager(int managerId, int staffId, String newName, int newSalary) {

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("http://localhost:3000/managers/" + managerId)
                .then()
                .extract().response();

        List<Map<String, Object>> staffs = response.jsonPath().getList("staffs");

        for (Map<String, Object> staff : staffs) {
            if (staff.get("id").equals(staffId)) {
                staff.put("name", newName);
                staff.put("salary", newSalary);
                break;
            }
        }

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .body(Collections.singletonMap("staffs", staffs))
                .when()
                .patch("http://localhost:3000/managers/" + managerId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200); 
    }

    @Test
    public static void performDeleteStaffUnderAManager(int managerId, int staffId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .when()
                .get("http://localhost:3000/managers/" + managerId)
                .then()
                .extract().response();
    
        List<Map<String, Object>> staffs = response.jsonPath().getList("staffs");
    
        staffs.removeIf(staff -> staff.get("id").equals(staffId));
    
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .body(Collections.singletonMap("staffs", staffs))
                .when()
                .patch("http://localhost:3000/managers/" + managerId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    
            }
    
        }


