package JeysonAmado.UsersService.Services.Users;

import JeysonAmado.UsersService.Entities.Users.UserRoleEntity;
import JeysonAmado.UsersService.Interfaces.Services.UserRoleServiceInterface;
import JeysonAmado.UsersService.Repositories.Users.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements UserRoleServiceInterface {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRoleEntity createUserRole(Long userId, Long RoleId) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        userRoleEntity.setRoleId(RoleId);
        userRoleEntity.commitCreate(userId);
        return userRoleRepository.save(userRoleEntity);
    }
}
