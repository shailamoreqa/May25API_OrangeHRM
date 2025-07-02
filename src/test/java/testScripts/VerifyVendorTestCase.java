package testScripts;

import Entity.CreatePayloadTest.VendorCreatePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TokenService;
import pages.VendorPage;
import utils.GenerateData;

public class VerifyVendorTestCase {

@Test
        public void createVendor() throws Exception{
        TokenService tokenService = new TokenService();
        tokenService.generateAccessToken("admin", "p94LqPKLq@");

        VendorPage vendorPage = new VendorPage();
        int vendorCode= vendorPage.nextAvailableVendorId();

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
        Assert.assertEquals(response.statusCode(), 201);


        //Schema validation
        Assert.assertTrue(vendorPage.validateSchema("src/test/resources/SchemaFiles/vendor.json",response.asString()));
    }
}
