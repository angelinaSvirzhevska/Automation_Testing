package apiTests;

import api.AuthorDTO;
import api.PostDTO;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import  org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class ApiTests {
    final String USER_NAME = "svirzhevska";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .log().all()
        .when()
                .get(POST_BY_USER  , USER_NAME)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class)
                ;
        logger.info(responseBody.length);
        logger.info(responseBody[0].getTitle());
        logger.info(responseBody[0].getAuthor().getUsername());
        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username", "svirzhevska", responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("sddsda", "sddsfsefew", "All Users", new AuthorDTO(USER_NAME), false),
                new PostDTO("test", "test", "All Users", new AuthorDTO(USER_NAME), false)

        };

        Assert.assertEquals(responseBody.length, expectedPostDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(expectedPostDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i],"_id", "uniquePost", "createdDate", "author");
            softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");
        }

        softAssertions.assertAll();
    }



    @Test
    public void getAllPostByUserNegative(){
        String responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(POST_BY_USER, "notValidUser")
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response",  "\"Sorry, invalid user requested.undefined\"", responseBody);
    }


     @Test
    public void getAllPostsByUserPath(){
         Response responseBody =
                 given()
                         .contentType(ContentType.JSON)
                         .log().all()
                 .when()
                         .get(POST_BY_USER  , USER_NAME)
                 .then()
                         .statusCode(200)
                         .log().all()
                         .extract().response();
         List<String > titleList = responseBody.jsonPath().getList("title", String.class);
         List<Map> authorList = responseBody.jsonPath().getList("author", Map.class);

         SoftAssertions softAssertions = new SoftAssertions();

         for (int i = 0; i < titleList.size(); i++) {
             softAssertions.assertThat(titleList.get(i)).as("Item number " + i)
                     .contains("s"); //проверяю не  test потому что есть название рандомное у поста у моего пользователя
             softAssertions.assertThat(authorList.get(i).get("username")).as("Item number" + i)
                     .isEqualTo(USER_NAME);
         }

         softAssertions.assertAll();

     }


     @Test
    public void getAllPostsByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
     }
}
