package app.adapters.rest.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest extends PersonRequest{
    private String userName;
    private String password;
}
