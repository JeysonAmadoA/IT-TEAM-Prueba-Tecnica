package JeysonAmado.UsersService.Feature.Controllers.Auth;


import JeysonAmado.UsersService.Dto.Auth.LoginDto;
import JeysonAmado.UsersService.Dto.Auth.RegisterDto;
import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Entities.Users.UserRoleEntity;
import JeysonAmado.UsersService.Http.Config.CustomUserDetails;
import JeysonAmado.UsersService.Http.Config.JWTUtilities;
import JeysonAmado.UsersService.Http.Controllers.Auth.AuthController;
import JeysonAmado.UsersService.Interfaces.Services.UserRoleServiceInterface;
import JeysonAmado.UsersService.Interfaces.Services.UserServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static JeysonAmado.UsersService.Interfaces.Services.UserRoleServiceInterface.CUSTOMER_ID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTUtilities jwtUtilities;

    @Mock
    private UserServiceInterface userService;

    @Mock
    private UserRoleServiceInterface userRoleService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testLoginSuccess() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication());

        when(jwtUtilities.create(anyString(), anyLong())).thenReturn("mocked-jwt");

        authController.login(loginDto);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtilities, times(1)).create(anyString(), anyLong());
        verifyNoMoreInteractions(authenticationManager, jwtUtilities);
    }

    private Authentication mockAuthentication() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");

        CustomUserDetails userDetails = new CustomUserDetails(userEntity, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return new UsernamePasswordAuthenticationToken(userDetails, "password", userDetails.getAuthorities());
    }

    @Test
    public void testLoginFail() {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("invalid-password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

       authController.login(loginDto);

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verifyNoMoreInteractions(authenticationManager, jwtUtilities);
    }

    @Test
    public void testRegister() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");

        UserEntity user = new UserEntity();
        user.setId(1L);

        when(userService.registerUser(registerDto)).thenReturn(user);
        when(userRoleService.createUserRole(anyLong(),anyLong())).thenReturn(new UserRoleEntity());

        ResponseEntity<String> response = authController.register(registerDto);

        verify(userService, times(1)).registerUser(registerDto);
        verify(userRoleService, times(1)).createUserRole(anyLong(), anyLong());
    }


    @Test
    public void testRegister_PasswordMismatch() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("mismatched-password");

        authController.register(registerDto);
        verifyNoInteractions(userService, userRoleService);
    }

    @Test
    public void testRegisterFail() throws Exception {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");
        registerDto.setConfirmPassword("password");

        when(userService.registerUser(any(RegisterDto.class))).thenThrow(new RuntimeException("Registration failed"));

        ResponseEntity<String> response = authController.register(registerDto);

        verify(userService, times(1)).registerUser(any(RegisterDto.class));
        verifyNoMoreInteractions(userService, userRoleService);
    }

}
