package JeysonAmado.UsersService.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_who_created_id")
    private Long userWhoCreatedId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "user_who_updated_id")
    private Long userWhoUpdatedId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "user_who_deleted_id")
    private Long userWhoDeletedId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void commitDelete(Long userId) {
        this.setUserWhoDeletedId(userId);
        this.setDeletedAt(LocalDateTime.now()) ;
    }

    public void commitUpdate(Long userId) {
        this.setUpdatedAt(LocalDateTime.now());
        this.setUserWhoUpdatedId(userId);
    }

    public void commitCreate(Long userId) {
        this.setCreatedAt(LocalDateTime.now());
        this.setUserWhoCreatedId(userId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserWhoCreatedId() {
        return userWhoCreatedId;
    }

    public void setUserWhoCreatedId(Long userWhoCreatedId) {
        this.userWhoCreatedId = userWhoCreatedId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserWhoUpdatedId() {
        return userWhoUpdatedId;
    }

    public void setUserWhoUpdatedId(Long userWhoUpdatedId) {
        this.userWhoUpdatedId = userWhoUpdatedId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUserWhoDeletedId() {
        return userWhoDeletedId;
    }

    public void setUserWhoDeletedId(Long userWhoDeletedId) {
        this.userWhoDeletedId = userWhoDeletedId;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
