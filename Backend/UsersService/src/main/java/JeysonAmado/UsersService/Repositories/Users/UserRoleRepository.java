package JeysonAmado.UsersService.Repositories.Users;

import JeysonAmado.UsersService.Entities.Users.UserRoleEntity;
import JeysonAmado.UsersService.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRoleEntity,Long> {
}
