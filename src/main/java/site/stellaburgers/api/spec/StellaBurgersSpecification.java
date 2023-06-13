package site.stellaburgers.api.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import site.stellaburgers.api.dto.LoginJson;
import site.stellaburgers.api.dto.UserJson;

import java.util.Arrays;

public class StellaBurgersSpecification {
    public static RequestSpecification forStellaBurgers(UserJson userJson) {
        return createDefaultSpec().setBody(userJson).build();
    }

    public static RequestSpecification delete(String accessToken) {
        return createDefaultSpec().addHeader("Authorization", accessToken).build();
    }

    public static RequestSpecification forLogin(LoginJson loginJson) {
        return createDefaultSpec().setBody(loginJson).build();
    }


    private static RequestSpecBuilder createDefaultSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .setBasePath("/api/")
                .setContentType(ContentType.JSON)
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured()));
    }
}
