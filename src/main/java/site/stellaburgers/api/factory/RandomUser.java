package site.stellaburgers.api.factory;

import org.apache.commons.lang3.RandomStringUtils;
import site.stellaburgers.api.dto.UserJson;


public class RandomUser extends UserJson {
    public static UserJson getRandomUser() {
        UserJson randomUser = new UserJson();
        randomUser.setEmail((RandomStringUtils.randomAlphabetic(8) + "@yandex.ru").toLowerCase());
        randomUser.setPassword(RandomStringUtils.randomAlphabetic(8));
        randomUser.setName(RandomStringUtils.randomAlphabetic(8));
        return randomUser;
    }
}
