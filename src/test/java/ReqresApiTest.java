import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ReqresApiTest {
    private String baseUrl = "https://reqres.in/api";
    private int createdUserId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test(priority = 1)
    public void testCreateUser() {
        String requestBody = "{\"name\": \"John\", \"job\": \"QA Engineer\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("job", equalTo("QA Engineer"))
                .extract().response();

        createdUserId = response.jsonPath().getInt("id");
        Assert.assertTrue(createdUserId > 0, "User ID should be greater than 0");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        String updatedBody = "{\"name\": \"John Updated\", \"job\": \"Senior QA\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedBody)
                .log().all()
                .when()
                .put("/users/" + createdUserId)
                .then()
                .log().all()
                .statusCode(200)
                .body("name", equalTo("John Updated"))
                .body("job", equalTo("Senior QA"));
    }

    @Test(priority = 3, dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        given()
                .log().all()
                .when()
                .delete("/users/" + createdUserId)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test(priority = 4)
    public void testDelayedResponse() {
        given()
                .log().all()
                .when()
                .get("/users?delay=5") // Corrected endpoint
                .then()
                .log().all()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("data.id", hasItems(1, 2, 3, 4, 5, 6)); // Validate list of users
    }
}