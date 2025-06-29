package Entity.CreatePayloadTest.PracticeSamples;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInformation {
    public Integer id;
    public String product;
    public Integer price;
    public Boolean inStock;
}
