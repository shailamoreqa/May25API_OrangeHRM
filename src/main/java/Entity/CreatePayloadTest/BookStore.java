package Entity.CreatePayloadTest;

import lombok.*;

import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookStore {
        public String libraryName;
        public String location;
        public ArrayList<Book> books;
  }

