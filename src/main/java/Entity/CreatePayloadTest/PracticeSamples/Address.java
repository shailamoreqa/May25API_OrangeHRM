package Entity.CreatePayloadTest.PracticeSamples;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
        public String street;
        public String city;
        public String postal_code;
}
