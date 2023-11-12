package JeysonAmado.UsersService.Interfaces.Services;

import JeysonAmado.UsersService.Entities.Users.UserRoleEntity;

public interface UserRoleServiceInterface {

    public static final Long CUSTOMER_ID = 2L;

    UserRoleEntity createUserRole(Long userId, Long RoleId);
}
