package JeysonAmado.UsersService.Integration.Services.Users;

import JeysonAmado.UsersService.Entities.Users.UserRoleEntity;
import JeysonAmado.UsersService.Repositories.Users.UserRoleRepository;
import JeysonAmado.UsersService.Services.Users.UserRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @Test
    public void testCreateUserRole() {
        Long userId = 1L;
        Long roleId = 2L;
        UserRoleEntity expectedUserRoleEntity = new UserRoleEntity();
        expectedUserRoleEntity.setUserId(userId);
        expectedUserRoleEntity.setRoleId(roleId);

        when(userRoleRepository.save(any())).thenReturn(expectedUserRoleEntity);

        userRoleService.createUserRole(userId, roleId);

        verify(userRoleRepository, times(1)).save(any());
    }
}
