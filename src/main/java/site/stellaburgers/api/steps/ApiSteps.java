package site.stellaburgers.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.Pair;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.dto.UserJson;
import site.stellaburgers.api.factory.RandomUserData;
import site.stellaburgers.api.spec.StellaBurgersSpecification;

import static site.stellaburgers.api.enums.StellaBurgersEndpoint.*;
import static site.stellaburgers.api.utils.CoreApiUtils.sendRequest;
import static site.stellaburgers.api.utils.RequestType.DELETE;
import static site.stellaburgers.api.utils.RequestType.POST;


public class ApiSteps {


    @Step("Удалить пользователя")
    public static void sendDelete(String token) {
        sendRequest(StellaBurgersSpecification.delete(token), DELETE, USER);
    }

    @Step("Получить токен авторизации")
    public static String takeToken(Response response) {
        return response.then().extract().path("accessToken");
    }

    @Step("Отприть запрос авторизации пользователя")
    public static Response sendUserLogin(UserJson userJson) {
        return sendRequest(StellaBurgersSpecification.forLogin(userJson), POST, LOGIN);
    }

    @Step("Отправить запрос регистрации пользователя")
    public Response sendUserRegistration(UserJson userJson) {
        return sendRequest(StellaBurgersSpecification.forStellaBurgers(userJson), POST, REGISTRATION);
    }
    @Step("Сгенировать данные пользователя для авторизации")
    public  Pair<String, LoginJson> generateLoginUser() {
        UserJson userJson = RandomUserData.getRandomUser();
        Response response = sendUserRegistration(userJson);
        LoginJson loginJson = LoginJson.builder()
                .email(userJson.getEmail())
                .password(userJson.getPassword())
                .build();
        return Pair.of(takeToken(response), loginJson);
    }

}
