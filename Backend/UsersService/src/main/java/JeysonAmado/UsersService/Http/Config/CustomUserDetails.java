package JeysonAmado.UsersService.Http.Config;

import JeysonAmado.UsersService.Entities.Users.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final Long id;
    public CustomUserDetails(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getEmail(), userEntity.getPassword(), authorities);
        this.id = userEntity.getId();
    }

    public Long getId() {
        return id;
    }
}
