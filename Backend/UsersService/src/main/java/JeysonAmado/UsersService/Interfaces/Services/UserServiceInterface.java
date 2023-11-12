package JeysonAmado.UsersService.Interfaces.Services;

import JeysonAmado.UsersService.Dto.Auth.RegisterDto;
import JeysonAmado.UsersService.Dto.User.UserDto;
import JeysonAmado.UsersService.Entities.Users.UserEntity;

import java.util.List;

public interface UserServiceInterface {

    UserEntity createUser(UserEntity User, Long userId);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers();

    UserEntity updateUser(UserEntity User, Long userId);

    UserEntity deleteUser(Long UserId, Long userId);

    UserEntity registerUser(RegisterDto registerDto) throws Exception;
}
