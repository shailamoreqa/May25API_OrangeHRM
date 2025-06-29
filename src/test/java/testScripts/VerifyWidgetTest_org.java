package testScripts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerifyWidgetTest_org {
    @Test
    public void VerifyWidget() {

        System.out.println("=========Get CSRF_Token=========");
        RestAssured.baseURI = "https://shailmore-trials7161.orangehrmlive.com";
        Response response = RestAssured
                .given()
                    .accept(ContentType.HTML)
                .when()
                    .get("/auth/login")
                .then()
                    .extract().response();
        //System.out.println(response.statusCode());
        //Option1:1 parse the htmal and find out elemnet by id
        //        2 parse the string and css selector
        Document document = Jsoup.parse(response.asString());
        String csrf_token= document.getElementById("login__csrf_token").attr("value");
        Assert.assertEquals(response.statusCode(),200);
        Map<String,String>cookies=response.cookies();
        //Option:2a
        //String csrf_token_CSS=document.select("input[name='login[_csrf_token]']").attr("value");
        //System.out.println(csrf_token_CSS);

        System.out.println("=========Validate Creditials=========");
        Map<String,String>formData= new HashMap<>();
        formData.put("login[_csrf_token]",csrf_token);
        formData.put("hdnUserTimeZoneOffset","5.5");
        formData.put("txtUsername","Admin");
        formData.put("txtPassword","ftMHF23yP@");
        response =RestAssured
                .given()
                    .contentType(ContentType.URLENC)
                    .formParams(formData)
                    .cookies(cookies)
                .when()
                    .post("/auth/validateCredentials")
                .then()
                    .extract().response();
        Assert.assertEquals(response.statusCode(),200);
        cookies=response.cookies();
        //

       System.out.println("=========getLoggedInAccountToken =========");
       response=RestAssured
                .given()
                    .accept(ContentType.JSON)
                   // .contentType(ContentType.HTML)
                    .cookies(cookies)
                .when()
                    .get("/core/getLoggedInAccountToken")
                .then()
                    .extract().response();
        Assert.assertEquals(response.statusCode(),200);
        String access_token= response.jsonPath().getString("token.access_token");

        /// TestCase Execution
        System.out.println("====Get the Widget List=====");
        List<String>expectedWidgetList= Arrays.asList("My Actions","Quick Access",
                "Time At Work",
                "Employees on Leave Today",
                "Latest News",
                "Latest Documents",
                "Performance Quick Feedback",
                "Current Year's Leave Taken by Department",
                "Buzz Latest Posts",
                "Leave Taken on Each Day of the Week Over Time",
                "Leave Scheduled in Each Month",
                "Leave Taken on Each Calendar Month Over the Years",
                "Headcount by Location",
                "Annual Basic Payment by Location");
        response=RestAssured
                .given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.URLENC)
                    .cookies(cookies)
                    .header("Authorization","Bearer "+access_token)
                    .queryParam("typeId",1)
                .when()
                    .get("/api/dashboard/widgets")
                .then()
                    .extract().response();
        Assert.assertEquals(response.statusCode(),200);
        List<String>actualWidgetList=response.jsonPath().getList("data.title");
        Assert.assertEquals(actualWidgetList,expectedWidgetList);
    }
}

