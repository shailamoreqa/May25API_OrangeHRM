package pages;

import base.APIControlActions;
import io.restassured.response.Response;

public class VendorPage extends APIControlActions {
    public int nextAvailableVendorId() {
        Response response = executeGetRequest("/api/assets/vendors");
        int nextVendorId = response.jsonPath().getList("data").size() + 1;
        return nextVendorId;
    }

    public Response createVendor(String vendorCreatePayload) {
        setPayload(vendorCreatePayload);
        return executePostRequest("/api/assets/vendors");
    }
}
