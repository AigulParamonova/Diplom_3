package serializer;

import com.github.javafaker.Faker;

public class UserGenerator {
    public static User getRandomUser(){
        return new User(UserGenerator.getRandomName(),UserGenerator.getRandomEmail(),UserGenerator.getRandomPassword());
    }

    public static String getRandomEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static String getRandomPassword(){
        Faker faker = new Faker();
        return faker.internet().password(8,10,true,true,true);
    }

    public static String getRandomName(){
        Faker faker = new Faker();
        return faker.address().firstName();
    }
}
