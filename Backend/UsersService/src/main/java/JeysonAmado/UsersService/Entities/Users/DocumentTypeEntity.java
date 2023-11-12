package JeysonAmado.UsersService.Entities.Users;

import JeysonAmado.UsersService.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "document_types")
public class DocumentTypeEntity extends BaseEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }
}
