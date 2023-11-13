package JeysonAmado.UsersService.Unit.Dto.Auth;

import JeysonAmado.UsersService.Dto.Auth.RegisterDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterDtoTest {

    @Test
    public void testSetAndGetName() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName("Jeyson");
        assertEquals("Jeyson", registerDto.getName());
    }

    @Test
    public void testSetAndGetLastName() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setLastName("Amado");
        assertEquals("Amado", registerDto.getLastName());
    }

    @Test
    public void testSetAndGetAge() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setAge(25);
        assertEquals(25, registerDto.getAge());
    }

    @Test
    public void testSetAndGetDocumentTypeId() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setDocumentTypeId(1L);
        assertEquals(1L, registerDto.getDocumentTypeId());
    }

    @Test
    public void testSetAndGetDocumentNumber() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setDocumentNumber("123456789");
        assertEquals("123456789", registerDto.getDocumentNumber());
    }

    @Test
    public void testSetAndGetPassword() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setPassword("password123");
        assertEquals("password123", registerDto.getPassword());
    }

    @Test
    public void testSetAndGetConfirmPassword() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setConfirmPassword("password123");
        assertEquals("password123", registerDto.getConfirmPassword());
    }

    @Test
    public void testSetAndGetEmail() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@example.com");
        assertEquals("test@example.com", registerDto.getEmail());
    }
}
