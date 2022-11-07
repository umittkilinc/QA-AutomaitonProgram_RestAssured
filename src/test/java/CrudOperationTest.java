import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;

import org.testng.annotations.Test;
import pojo.CreateJson;

import static io.restassured.RestAssured.given;
import static util.JsonConverter.*;

public class CrudOperationTest {

    @Test(priority = 1)
    public void testCreate() throws JsonProcessingException {
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";
        CreateJson createJson= getCreateObject();
        String createBody = jsonValueString(createJson);
        String response = given().log().all().header("Content-Type", "application/json")
                .body(createBody)
                .when().post("/pet").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }


    @Test(priority = 2)
    public void testUpdate() throws JsonProcessingException {
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";
        CreateJson createJson= getCreateObject();
        createJson.setName("köpüş corgi");
        String createBody = jsonValueString(createJson);
        String response = given().log().all().header("Content-Type", "application/json")
                .body(createBody)
                .when().put("/pet").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }

    @Test(priority = 3)
    public void testList (){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";
        int id = 10;
        String response = given().log().all().header("Content-Type", "application/json")//.queryParam("id",10)
                .when().get("/pet/"+id)
                .then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

    }

    @Test(priority = 4)
    public void testDelete(){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";
        String response2 = given().log().all()//.queryParam("petId", "10")
                .header("Content-Type", "application/json")
                .when().delete("/pet/10").then().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response2);
    }

    @Test(priority = 5)
    public void testDeleteFirstTen(){
        RestAssured.baseURI = "https://petstore3.swagger.io/api/v3";

        for (int i=1;i<11;i++){
            String response2 = given().log().all()//.queryParam("petId", "10")
                    .header("Content-Type", "application/json")
                    .when().delete("/pet/"+i).then().assertThat().statusCode(200).extract().response().asString();
            System.out.println(response2);

        }

    }


}
