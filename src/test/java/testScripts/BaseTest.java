package testScripts;

import constants.FileConstants;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.TokenService;
import utility.Propertiesutils;

public class BaseTest {
    Propertiesutils propertiesutils = new Propertiesutils(FileConstants.CONFIG_FILE_PATH);
    @BeforeSuite
    public void setup() {
        TokenService tokenService = new TokenService();
        tokenService.generateAccessToken(propertiesutils.getValue("userName"),propertiesutils.getValue("password"));
    }

    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        if (iTestResult.isSuccess()) {
            System.out.println("My TestCase Passed"+ iTestResult.getMethod().getMethodName());
        } else
            System.out.println("My TestCase Failed"+ iTestResult.getMethod().getMethodName());
    }
}
