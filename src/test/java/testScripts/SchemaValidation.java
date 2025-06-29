package testScripts;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.io.File;

public class SchemaValidation {
/*
 <!-- https://mvnrepository.com/artifact/io.rest-assured/json-schema-validator -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-schema-validator</artifactId>
            <version>5.3.1</version>
        </dependency>
* */
    public static void main(String[]args){
       Response response= RestAssured
                .given()
                    .get("https://mocki.io/v1/24591511-c33b-403c-a991-74b65b8fd335")
               .then()
                  .body(JsonSchemaValidator.matchesJsonSchema(new File("productSchema.json")))
                   .extract().response();
       System.out.println(response.asPrettyString());
    }
}
