package testScripts;

import Entity.CreatePayloadTest.VendorCreatePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.FileConstants;
import constants.StatusCode;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.VendorPage;
import utility.GenerateData;

public class EmployeeManagementVendorTest extends BaseTest {

@Test
        public void createVendor() throws Exception{
        VendorPage vendorPage = new VendorPage();
        int vendorCode= vendorPage.nextAvailableVendorId();
//
        VendorCreatePayload vendorCreatePayload = VendorCreatePayload.builder() .vendorName(GenerateData.generateVendorName())
                .address(GenerateData.generateAddress())
                .email(GenerateData.name() + "@gmail.com")
                .website(GenerateData.generateWebSite())
                .contactNo(GenerateData.generatePhoneNo())
                .vendorCode("0" + vendorCode)
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String vendor_Payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vendorCreatePayload);
        Response response = vendorPage.createVendor(vendor_Payload);

        //Status Code Validation
        Assert.assertEquals(response.statusCode(), StatusCode.CREATED);

         //Schema validation
        Assert.assertTrue(vendorPage.validateSchema(FileConstants.CREATE_VENDOR_SCHEMA,
                response.asString()));
    }
}
