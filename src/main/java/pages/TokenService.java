package pages;

import base.APIControlActions;
import groovy.lang.GString;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class TokenService extends APIControlActions {
    private static String csrf_token;
    private void login(){
       // RestAssured.baseURI = "https://shailmore-trials7161.orangehrmlive.com";
        System.out.println("=========Get CSRF_Token=========");
        Response response = RestAssured
                .given().baseUri(BASE_URI)
                    .accept(ContentType.HTML)
                .when()
                    .get("/auth/login")
                .then()
                    .extract().response();
        Document document = Jsoup.parse(response.asString());
        csrf_token= document.getElementById("login__csrf_token").attr("value");
        Assert.assertEquals(response.statusCode(),200);
        cookies=response.cookies();
}

     private void validateCreditials(String userName, String password){
        System.out.println("=========Validate Creditials=========");
        Map<String,String>formData= new HashMap<>();
        formData.put("login[_csrf_token]",csrf_token);
        formData.put("hdnUserTimeZoneOffset","5.5");
        formData.put("txtUsername",userName);
        formData.put("txtPassword",password);
        Response response =RestAssured
                    .given().baseUri(BASE_URI)
                        .contentType(ContentType.URLENC)
                        .formParams(formData)
                        .cookies(cookies)
                    .when()
                        .post("/auth/validateCredentials")
                    .then()
                        .extract().response();
            Assert.assertEquals(response.statusCode(),200);
            cookies=response.cookies();
    }

    private void getLoggedInAccountToken(){
        System.out.println("=========getLoggedInAccountToken =========");
        Response response=RestAssured
                .given().baseUri(BASE_URI)
                .accept(ContentType.JSON)
                .cookies(cookies)
                .when()
                .get("/core/getLoggedInAccountToken")
                .then()
                .extract().response();
        Assert.assertEquals(response.statusCode(),200);
        BearerToken= response.jsonPath().getString("token.access_token");
        //setToken(BearerToken);
    }

    public void generateAccessToken(String userName,String password){
        login();
        validateCreditials(userName,password);
        getLoggedInAccountToken();
    }
}
