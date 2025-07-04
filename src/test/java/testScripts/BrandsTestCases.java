package testScripts;

import Entity.CreatePayloadTest.CreateBrandsPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.FileConstants;
import constants.StatusCode;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeeManagementBrandsPage;
import utility.GenerateData;

public class BrandsTestCases extends BaseTest {

    //Brand Page Object
    @Test
    public void createBrands() throws Exception{
        EmployeeManagementBrandsPage employeeManagementBrandsPage = new EmployeeManagementBrandsPage();
        int brandId = employeeManagementBrandsPage.getNextBrandId();

        CreateBrandsPayload createBrandsPayload = CreateBrandsPayload.builder()
                .brandCode("0"+brandId)
                .brandName(GenerateData.generateBrandName())
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String payload =mapper.writerWithDefaultPrettyPrinter().writeValueAsString(createBrandsPayload);
        Response createBrandResponse = employeeManagementBrandsPage.createBrand(payload);
        System.out.println(payload);
        System.out.println(createBrandResponse.asPrettyString());

        //Validate Status Code
        Assert.assertEquals(createBrandResponse.statusCode(), StatusCode.CREATED);

        //Validate the Brand Schema
       Assert.assertTrue(employeeManagementBrandsPage.validateSchema
               (FileConstants.CREATE_BRAND_SCHEMA, createBrandResponse.asString()));
    }
}
