package JeysonAmado.UsersService.Http.Config;

import JeysonAmado.UsersService.Entities.Users.RoleEntity;
import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Repositories.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {


    private final UserRepository userRepository;


    @Autowired
    public UserSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        String[] roles = userEntity.getRoles().stream().map(RoleEntity::getName).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = this.getGrantedAuthorities(roles);
        return new CustomUserDetails(userEntity, authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role:roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role));
        }
        return authorities;
    }

}
