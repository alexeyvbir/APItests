package in.reqres;

import data.people.CreatedPeople;
import data.people.People;
import data.people.ResourceDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specification.Specification.*;

public class APITests {

    @Test
    public void firstTest() {
        given()
                .header("x-api-key","reqres-free-v1")
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .body("page", notNullValue())
                .body("data.id", not(hasItem(nullValue())))
                .body("data.first_name", hasItem("Lindsay"))
                .body("data[1].first_name", equalToIgnoringCase("Lindsay"))
                .statusCode(200);
    }

    @Test
    public void secondTest() {
        Map<String,String> requestData = new HashMap<>();
        requestData.put("name","Alexey");
        requestData.put("job","QA-engineer");
        Response response = given()
                .contentType("application/json")
                .header("x-api-key","reqres-free-v1")
                .body(requestData)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(requestData.get("name"), jsonResponse.get("name"), "Ожидали создание пользователя " +
                "с именем: " + requestData.get("name") + ", а создался пользователь с именем: " +
                jsonResponse.get("name"));
    }

    @Test
    public void secondTest1() {
        People people = new People("Denis","programmer");
        CreatedPeople createdPeople = given()
                .contentType("application/json")
                .header("x-api-key","reqres-free-v1")
                .body(people)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().body().as(CreatedPeople.class);
        System.out.println("-----------");
        System.out.println(createdPeople.getCreatedAt());
    }

    @Test
    public void prettyFirstTest() {
        ResourceDto resourse = given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .extract().body().as(ResourceDto.class);

        resourse.getData().forEach(x-> System.out.println(x.getEmail()));
    }

    @Test
    public void specTest() {
        People people = new People("Denis","programmer");
        CreatedPeople createdPeople = given()
                .spec(requestSpec())
                .body(people)
                .when()
                .post("/api/users")
                .then()
                .log().body()
                .spec(responseSpec201())
                .extract().body().as(CreatedPeople.class);
        System.out.println("-----------");
        System.out.println(createdPeople.getCreatedAt());
    }

    @Test
    public void specTest1() {
        installSpec(requestSpec(),responseSpec201());
        People people = new People("Denis","programmer");
        CreatedPeople createdPeople = given()
                .body(people)
                .when()
                .post("/api/users")
                .then()
                .log().body()
                .extract().body().as(CreatedPeople.class);
        System.out.println("-----------");
        System.out.println(createdPeople.getCreatedAt());
        deleteSpec();
    }
}
