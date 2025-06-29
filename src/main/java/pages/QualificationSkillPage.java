package pages;

import base.APIControlActions;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class QualificationSkillPage extends APIControlActions {
    public Response getAllSkill(String limit, String sortingFeildName, String sortingOrder) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("limit", limit);
        queryParams.put("sortingFeild", sortingFeildName);
        queryParams.put("sortingOrder", sortingOrder);
        setQueryParam(queryParams);
        return executeGetRequest("/api/skills");
    }

    public Response getAllSkill() {
        return executeGetRequest("/api/skills");
    }
}
