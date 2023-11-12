package JeysonAmado.UsersService.Repositories.Users;

import JeysonAmado.UsersService.Entities.Users.RoleEntity;
import JeysonAmado.UsersService.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<RoleEntity,Long> {
}
