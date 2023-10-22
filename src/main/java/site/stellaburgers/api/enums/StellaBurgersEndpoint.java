package site.stellaburgers.api.enums;

import lombok.AllArgsConstructor;
import site.stellaburgers.api.utils.Endpoint;

@AllArgsConstructor
public enum StellaBurgersEndpoint implements Endpoint {
    LOGIN("auth/login"),
    USER("auth/user"),
    REGISTRATION("auth/register");
    private final String endpoint;

    @Override
    public String getValue() {
        return endpoint;
    }

}
