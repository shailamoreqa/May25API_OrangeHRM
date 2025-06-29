package Entity.CreatePayloadTest.PracticeSamples;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentInformation {
        public String name;
        public Integer age;
        public String city;
        public Boolean married;
        public ArrayList<String> hobbies;
        public Address address;
    }
