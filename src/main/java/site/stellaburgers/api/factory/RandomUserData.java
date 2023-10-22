package site.stellaburgers.api.factory;

import net.datafaker.Faker;
import site.stellaburgers.api.dto.UserJson;


public class RandomUserData {
    private static final Faker faker = new Faker();
    public static UserJson getRandomUser() {
        return UserJson.builder()
                .email(getRandomEmail())
                .password(getRandomPassword())
                .name(getRandomName())
                .build();
    }

    public static String getRandomEmail() {
        return faker.name().firstName() +"@yandex.ru";
    }
    public static String getRandomName() {
        return faker.name().firstName();
    }

    public static String getRandomPassword() {
        return String.valueOf(faker.number().numberBetween(1000000, 100000000));
    }

}
