package JeysonAmado.UsersService.Repositories.Users;

import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity,Long> {

    UserEntity findByEmail(String email);
}
