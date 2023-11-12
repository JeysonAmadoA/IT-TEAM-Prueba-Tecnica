package JeysonAmado.UsersService.Mappers.Users;

import JeysonAmado.UsersService.Dto.User.UserDto;
import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class UserMap extends BaseMap<UserDto, UserEntity> {
    public UserMap() {
        super(UserDto.class, UserEntity.class);
    }
}
