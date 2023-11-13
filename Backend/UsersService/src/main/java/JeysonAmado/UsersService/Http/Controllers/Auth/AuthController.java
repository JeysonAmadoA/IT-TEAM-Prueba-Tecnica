package JeysonAmado.UsersService.Http.Controllers.Auth;


import JeysonAmado.UsersService.Dto.Auth.LoginDto;
import JeysonAmado.UsersService.Dto.Auth.RegisterDto;
import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Http.Config.CustomUserDetails;
import JeysonAmado.UsersService.Http.Config.JWTUtilities;
import JeysonAmado.UsersService.Interfaces.Services.UserRoleServiceInterface;
import JeysonAmado.UsersService.Interfaces.Services.UserServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static JeysonAmado.UsersService.Interfaces.Services.UserRoleServiceInterface.CUSTOMER_ID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtilities jwtUtilities;

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserRoleServiceInterface userRoleService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTUtilities jwtUtilities, UserServiceInterface userService, UserRoleServiceInterface userRoleService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtilities = jwtUtilities;
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            Long userId = userDetails.getId();
            String jwt = this.jwtUtilities.create(loginDto.getEmail(), userId);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body("Inicio de sesión exitoso");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrecta");
        }

    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            try {
                UserEntity user = this.userService.registerUser(registerDto);
                this.userRoleService.createUserRole(user.getId(),CUSTOMER_ID);
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario Registrado");
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al registrar usuario: " + e.getMessage());
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Las contraseñas no coinciden");
        }
    }
}
