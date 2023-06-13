package site.stellaburgers.api.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.dto.UserJson;
import site.stellaburgers.api.spec.StellaBurgersSpecification;

import static site.stellaburgers.api.enums.StellaBurgersEndpoint.*;
import static site.stellaburgers.api.utils.CoreApiUtils.sendRequest;
import static site.stellaburgers.api.utils.RequestType.DELETE;
import static site.stellaburgers.api.utils.RequestType.POST;


public class ApiSteps {


    @Step("Удаляем пользователя")
    public static void sendDelete(String token) {
        sendRequest(StellaBurgersSpecification.delete(token), DELETE, USER);
    }

    @Step("Получаем токен")
    public static String takeToken(Response response) {
        return response.then().extract().path("accessToken");
    }

    @Step("Отправялем запрос авторизации пользователя")
    public static Response sendUserLogin(LoginJson loginJson) {
        return sendRequest(StellaBurgersSpecification.forLogin(loginJson), POST, LOGIN);
    }

    @Step("Отправялем запрос регистрации пользователя")
    public Response sendUserRegistration(UserJson userJson) {
        return sendRequest(StellaBurgersSpecification.forStellaBurgers(userJson), POST, REGISTRATION);
    }


}
