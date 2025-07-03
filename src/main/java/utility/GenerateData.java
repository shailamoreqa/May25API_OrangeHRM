package utility;

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

    public  static String generateVendorName(){
        return faker.company().bs();
    }

    public  static String generateCode(){
        return faker.random().toString();
    }
    public  static String generateAddress(){
        return faker.country().capital();
    }

    public  static String generateWebSite(){
        return "https://"+faker.company().url();
    }
    public  static String generatePhoneNo(){
        return faker.phoneNumber().phoneNumber();
    }


}