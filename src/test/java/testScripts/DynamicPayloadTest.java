package testScripts;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DynamicPayloadTest {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("en_IND"));
        for (int i = 0; i < 5; i++) {
            System.out.println(faker.name().firstName());
            System.out.println(faker.number().digit());
            System.out.println(faker.date().birthday());
        }

    }
}

