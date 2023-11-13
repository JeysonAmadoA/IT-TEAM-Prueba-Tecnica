package JeysonAmado.UsersService.Unit.Dto.Auth;

import JeysonAmado.UsersService.Dto.Auth.LoginDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginDtoTest {

    @Test
    public void testSetAndGetEmail() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        assertEquals("test@example.com", loginDto.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword("password123");
        assertEquals("password123", loginDto.getPassword());
    }
}
