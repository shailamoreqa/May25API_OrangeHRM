package pages;

import base.APIControlActions;
import io.restassured.response.Response;

public class EmployeeManagementBrandsPage extends APIControlActions {
    public int getNextBrandId(){
        Response response =executeGetRequest("/api/assets/brands");
        int getNextBrandId=response.jsonPath().getList("data").size();
        return getNextBrandId+1;
    }

    public Response createBrand(String payload){
        setPayload(payload);
        return executePostRequest("api/assets/brands");
    }
}
