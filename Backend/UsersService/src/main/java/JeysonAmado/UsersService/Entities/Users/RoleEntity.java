package JeysonAmado.UsersService.Entities.Users;

import JeysonAmado.UsersService.Entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<UserEntity> users;
}