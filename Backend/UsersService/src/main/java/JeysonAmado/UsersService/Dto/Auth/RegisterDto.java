package JeysonAmado.UsersService.Dto.Auth;

import lombok.Data;

@Data
public class RegisterDto {

    private String name;

    private String lastName;

    private int age;

    private long documentTypeId;

    private String documentNumber;

    private String password;

    private String confirmPassword;

    private String email;
}
