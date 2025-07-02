package Entity.CreatePayloadTest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class VendorCreatePayload {
        public String email;
        public String address;
        public String website;
        public String contactNo;
        public String vendorCode;
        public String vendorName;
}
