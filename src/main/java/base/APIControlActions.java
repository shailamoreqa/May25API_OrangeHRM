package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;

import java.sql.Statement;
import java.util.*;
import io.qameta.allure.*;

public class APIControlActions {
    protected static Map<String, String> cookies;
    protected static RequestSpecification requestSpecification ;
    protected static String BASE_URI = "https://shailam-trials7161.orangehrmlive.com";
    protected  static String BearerToken;

    protected void setHeaders(Map<String, String> headers) {
        buildRequestspecBuilder();
        requestSpecification.headers(headers);
    }

    protected void setQueryParam(Map<String, String> queryParam) {
        buildRequestspecBuilder();
        requestSpecification.queryParams(queryParam);
    }

    protected void setToken(String access_token) {
        buildRequestspecBuilder();
        requestSpecification.header("Authorization", "Bearer " + BearerToken);

    }

    protected void setFormData(Map<String, String> formData) {
        buildRequestspecBuilder();
        requestSpecification.formParams(formData);
    }

    protected void buildRequestspecBuilder() {

        if (requestSpecification == null)
            requestSpecification = RestAssured.given();
    }

    protected void setPayload(String payloadBody) {
        buildRequestspecBuilder();
        requestSpecification.body(payloadBody);
    }

    protected Response executeGetRequest(String endPoint) {
        buildRequestspecBuilder();
        Response response= RestAssured
                .given()
                .filter(new AllureRestAssured())
                .spec(requestSpecification)
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.URLENC)
                .cookies(cookies)
                .header("Authorization", "Bearer " + BearerToken)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
        requestSpecification=null;
        return response;
}

    protected Response executePostRequest(String endPoint) {
        buildRequestspecBuilder();
        Response response= RestAssured
                .given()
                .filter(new AllureRestAssured())
                .spec(requestSpecification)
                .baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .cookies(cookies)
                .header("Authorization", "Bearer " + BearerToken)
                .when()
                .post(endPoint)
                .then()
                .extract().response();
        requestSpecification=null;
        return response;
    }


    public boolean validateSchema(String schemaFilePath,String responsePayload){
       return JsonSchemaValidator.matchesJsonSchema(new File(schemaFilePath)).matches(responsePayload);
    }


}
