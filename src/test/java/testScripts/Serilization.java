package testScripts;

import Entity.CreatePayloadTest.Book;
import Entity.CreatePayloadTest.BookStore;
import Entity.CreatePayloadTest.PracticeSamples.Address;
import Entity.CreatePayloadTest.PracticeSamples.ProductInformation;
import Entity.CreatePayloadTest.PracticeSamples.StudentInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utility.GenerateData;

import java.util.ArrayList;
import java.util.List;

public class Serilization {
    public static void main(String[] args) throws Exception {

        Book book1 = Book.builder()
                .title("B1")
                //  .yearPublished(1990)
                .author("abc")
                .genres(new ArrayList<>() {{
                    add("bbb");
                    add("cccc");
                }})
            //    .availableCopies(4)
                .build();

        Book book2 = Book.builder()
                .title("B2")
                .yearPublished(1990)
                .author("abc1")
                .genres(new ArrayList<>() {{
                    add("bbb1");
                    add("cccc1");
                }})
                .availableCopies(5)
                .build();

        BookStore bookStore = BookStore.builder()
                .libraryName("AlexLib")
                .location("Baramati")
                .books(new ArrayList<>() {{
                    add(book1);
                    add(book2);
                }})
                .build();
        /// JAVA to JSON --->Serilization Object mappoer method is used for writeValueAsString() method
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookStore));

        //Adding Null Values in specfic field ValueToTree
        ObjectNode jsonNode= mapper.valueToTree(bookStore);
        jsonNode.putNull("author");
        jsonNode.putNull("location");
        System.out.println(jsonNode);

        /// Sample Examples
        System.out.println("=======Sample2===========");
       StudentInformation studentInformation =StudentInformation.builder()
                .name(GenerateData.name()).married(true).age(GenerateData.generateRandomNumer()).city("Pune").address(Address.builder().city("Pune")
                        .postal_code("411028").street("HandewadiRoad")
                        .build())
                .build();
        ObjectMapper mapper1 = new ObjectMapper();

       String response =mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(studentInformation);
       System.out.println(response);

        System.out.println("=====Setting Null Values=====");
        ObjectNode node1 =mapper1.valueToTree(studentInformation);
        System.out.println(node1);

        //Address Node
        ObjectNode addressNodeaddressNode =node1.putObject("address");
        addressNodeaddressNode.putNull("street");
        addressNodeaddressNode.put("postal_code","121334");
        System.out.println(node1);

        // ArrayNode Hobbies
        ArrayNode hobbiesArray = mapper1.createArrayNode();
        hobbiesArray.add("Hobby1");
        hobbiesArray.add("Hobby2");
        hobbiesArray.add(1);
        node1.set("hobbies",hobbiesArray);
        System.out.println(node1);

        //Option1 :Using builder and mapper
        System.out.println("==========sample 3=========");
        List<ProductInformation>products= new ArrayList<>();
        for (int i=0;i<2;i++){
            ProductInformation product = ProductInformation.builder()
                    .id(GenerateData.generateRandomNumer())
                    .product(GenerateData.generateProductName())
                    .price(GenerateData.generateRandomNumer())
                    .build();
            products.add(product);
        }
            ObjectMapper map = new ObjectMapper();
            System.out.println(map.writerWithDefaultPrettyPrinter().writeValueAsString(products));

        //Option2 : Using Object Node
            ObjectMapper mapper2 =new ObjectMapper();
            ArrayNode productArray= mapper2.createArrayNode();

           /* for (int i=0;i<=2;i++)
                {
                    ObjectNode product =mapper2.createObjectNode();
                    product.put("id", GenerateData.generateRandomNumer());
                    product.put("product", GenerateData.generateProductName());
                    product.put("price", GenerateData.generateRandomNumer());
                    productArray.add(product);
                }
            String productInformation_str=mapper2.writerWithDefaultPrettyPrinter().writeValueAsString(productArray);
            System.out.println(productInformation_str);*/
        }

    }

