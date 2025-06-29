package pages;

import base.APIControlActions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DashBoardPage extends APIControlActions {

    public Response getAllWidget(){
        return executeGetRequest("/api/dashboard/widgets");
    }
}
