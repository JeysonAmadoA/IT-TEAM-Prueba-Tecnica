package JeysonAmado.UsersService.Integration.Repository.Users;

import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Repositories.Users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndGetUser() {

        UserEntity userEntity = new UserEntity();
        userEntity.setName("Jeyson");
        userEntity.setLastName("Amado");
        userEntity.setAge(25);
        userEntity.setDocumentTypeId(1L);
        userEntity.setDocumentNumber("123456789");
        userEntity.setPassword("password123");
        userEntity.setEmail("jeyson@example.com");

        userRepository.save(userEntity);
        UserEntity userFound = userRepository.findById(userEntity.getId()).orElse(null);

        assertNotNull(userFound);
        assertEquals("Jeyson", userFound.getName());
        assertEquals("Amado", userFound.getLastName());
        assertEquals(25, userFound.getAge());
        assertEquals(1L, userFound.getDocumentTypeId());
        assertEquals("123456789", userFound.getDocumentNumber());
        assertEquals("password123", userFound.getPassword());
        assertEquals("jeyson@example.com", userFound.getEmail());
    }

    @Test
    public void testUpdateUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Jeyson");
        userEntity.setLastName("Amado");
        userEntity.setAge(25);
        userEntity.setDocumentTypeId(1L);
        userEntity.setDocumentNumber("123456789");
        userEntity.setPassword("password123");
        userEntity.setEmail("jeyson@example.com");

        userRepository.save(userEntity);

        userEntity.setName("UpdatedName");
        userEntity.setAge(26);
        userEntity.setEmail("updated.email@example.com");

        userRepository.save(userEntity);

        UserEntity updatedUser = userRepository.findById(userEntity.getId()).orElse(null);

        assertNotNull(updatedUser);
        assertEquals("UpdatedName", updatedUser.getName());
        assertEquals("Amado", updatedUser.getLastName()); // El apellido no ha cambiado
        assertEquals(26, updatedUser.getAge());
        assertEquals(1L, updatedUser.getDocumentTypeId());
        assertEquals("123456789", updatedUser.getDocumentNumber());
        assertEquals("password123", updatedUser.getPassword());
        assertEquals("updated.email@example.com", updatedUser.getEmail());
    }
}
