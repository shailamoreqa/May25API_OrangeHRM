package Entity.CreatePayloadTest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBrandsPayload {
        private String brandCode;
        private String brandName;
}
