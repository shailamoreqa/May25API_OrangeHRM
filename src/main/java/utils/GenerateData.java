package utils;

import com.github.javafaker.Faker;

import java.util.Locale;
public class GenerateData {

    public static Faker faker = new Faker(new Locale("en_IND"));
    public static String name()
    {
        return faker.name().firstName();
    }
    public static int generateRandomNumer(){
        return faker.number().numberBetween(20,40);
    }


    public static String generateProductName(){
        return faker.commerce().productName();
    }

}