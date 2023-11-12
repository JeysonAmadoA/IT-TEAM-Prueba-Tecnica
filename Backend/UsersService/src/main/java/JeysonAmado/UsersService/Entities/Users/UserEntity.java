package JeysonAmado.UsersService.Entities.Users;

import JeysonAmado.UsersService.Dto.Auth.RegisterDto;
import JeysonAmado.UsersService.Entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int age;

    @Column(name = "document_type_id")
    private Long documentTypeId;

    @ManyToOne
    @JoinColumn(name = "document_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DocumentTypeEntity documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column
    private String password;

    @Column
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    public void setRegisterUser(RegisterDto registerDto){
        this.setName(registerDto.getName());
        this.setLastName(registerDto.getLastName());
        this.setAge(registerDto.getAge());
        this.setDocumentTypeId(registerDto.getDocumentTypeId());
        this.setDocumentNumber(registerDto.getDocumentNumber());
        this.setEmail(registerDto.getEmail());
        this.setPassword(registerDto.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public DocumentTypeEntity getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeEntity documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", documentTypeId=" + documentTypeId +
                ", documentType=" + documentType +
                ", documentNumber='" + documentNumber + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
