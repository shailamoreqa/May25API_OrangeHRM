package testScripts;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.QualificationSkillPage;
import pages.TokenService;

import java.util.*;
public class VerifyWidgetTest {
    @Test
    public void VerifyWidget() {
        TokenService tokenService = new TokenService();
        tokenService.generateAccessToken("Admin","p94LqPKLq@");

        /// TestCase Execution
        System.out.println("====Get the Widget List=====");
        DashBoardPage dashBoardPage = new DashBoardPage();
        Response response=dashBoardPage.getAllWidget();
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
        Assert.assertEquals(response.statusCode(),200);
        System.out.println(expectedWidgetList);
        List<String>actualWidgetList=response.jsonPath().getList("data.title");
        System.out.println(actualWidgetList);
        Assert.assertEquals(actualWidgetList,expectedWidgetList);

    }
    @Test
    public void verifySkillsAscending(){
        System.out.println("========Validate Skill=======");
        QualificationSkillPage skillPage = new QualificationSkillPage();
        String limit="11";
        String description="name";
       // System.out.println("=============QueryPArameter=========");
        Response response= skillPage.getAllSkill(limit,description,"ASC");
        List<String>actuaNameList=response.jsonPath().getList("data.name");
        //System.out.println(response.asPrettyString());
        //System.out.println("=============WithouQueryPArameter=========");
        //response =skillPage.getAllSkill();
        //System.out.println(response.asPrettyString());
        //Oprtin1
        //List<String>limit=response.jsonPath().getList("data").size();
        //Oprtin2
        //   String limit2=response.jsonPath().get("data.size()").toString();
        //  System.out.println(limit2);
        Assert.assertEquals(response.jsonPath().getList("data").size(),Integer.parseInt(limit),"Query param Functional" +
                " Breaking");
        response=skillPage.getAllSkill();
        List<String>expectedNameList=response.jsonPath().getList("data.name");
      //  Collections.sort(expectedNameList);
        System.out.println(expectedNameList);
        System.out.println(actuaNameList);
        Assert.assertEquals(actuaNameList,expectedNameList,"Name is not same");
    }
}

