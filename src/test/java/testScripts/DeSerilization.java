package testScripts;

import Entity.CreatePayloadTest.Book;
import Entity.CreatePayloadTest.BookStore;
import Entity.CreatePayloadTest.PracticeSamples.StudentInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;

import java.util.ArrayList;

public class DeSerilization {
    public static void main(String[] args) throws Exception {

        String response= "{\n" +
                "  \"libraryName\" : \"AlexLib\",\n" +
                "  \"location\" : \"Baramati\",\n" +
                "  \"books\" : [ {\n" +
                "    \"title\" : \"B1\",\n" +
                "    \"author\" : \"abc\",\n" +
                "    \"genres\" : [ \"bbb\", \"cccc\" ]\n" +
                "  }, {\n" +
                "    \"title\" : \"B2\",\n" +
                "    \"yearPublished\" : 1990,\n" +
                "    \"genres\" : [ \"cccc1\" ],\n" +
                "    \"availableCopies\" : 5\n" +
                "  } ]\n" +
                "}";
        // Deserialiaztion JSON --->JAVA

         ObjectMapper mapper = new ObjectMapper();
         BookStore bookStore= mapper.readValue(response,BookStore.class);
         System.out.println(bookStore.getLibraryName());


         System.out.println("==========Sample2=============");
          response ="{\"name\":\"Shaila\",\"age\":40,\"city\":\"Pune\",\"married\":true," +
                 "\"hobbies\":[\"h1\",\"h2\"],\"address\":{\"street\":\"HandewadiRoad\"," +
                 "\"city\":\"Pune\",\"postal_code\":\"411028\"}}";
         System.out.println(response);
         mapper = new ObjectMapper();
         StudentInformation studentInformation=mapper.readValue(response, StudentInformation.class);
         System.out.println(studentInformation.getAge());
         System.out.println(studentInformation.getName());


    }
}
