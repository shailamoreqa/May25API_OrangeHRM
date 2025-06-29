package Entity.CreatePayloadTest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
        public String title;
        public String author;
        public Integer yearPublished;
        public ArrayList<String> genres;
        public Integer availableCopies;
    }
