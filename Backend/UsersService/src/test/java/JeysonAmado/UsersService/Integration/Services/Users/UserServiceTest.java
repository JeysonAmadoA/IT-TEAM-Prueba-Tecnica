package JeysonAmado.UsersService.Integration.Services.Users;

import JeysonAmado.UsersService.Dto.User.UserDto;
import JeysonAmado.UsersService.Entities.Users.UserEntity;
import JeysonAmado.UsersService.Mappers.Users.UserMap;
import JeysonAmado.UsersService.Repositories.Users.UserRepository;
import JeysonAmado.UsersService.Services.Users.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMap userMap;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserEntity user = new UserEntity();
        user.setName("Jeyson");
        user.setLastName("Amado");
        user.setAge(30);
        user.setDocumentTypeId(1L);
        user.setDocumentNumber("123456789");
        user.setPassword("password123");
        user.setEmail("jeyson.amado@example.com");

        Long userId = 1L;

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        UserEntity createdUser = userService.createUser(user, userId);
        verify(userRepository, times(1)).save(user);
        assertEquals(user, createdUser);
    }

    @Test
    public void testGetUser() {
        Long userId = 1L;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        UserDto userDto = new UserDto();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userMap.toDto(userEntity)).thenReturn(userDto);

        UserDto retrievedUser = userService.getUser(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userMap, times(1)).toDto(userEntity);
        assertEquals(userDto, retrievedUser);
    }

    @Test
    public void testGetUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        UserDto retrievedUser = userService.getUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userMap, never()).toDto(any());
        assertNull(retrievedUser);
    }

    @Test
    public void testGetAllUsers() {
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();

        UserDto userDto1 = new UserDto();
        UserDto userDto2 = new UserDto();

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        when(userMap.toDto(user1)).thenReturn(userDto1);
        when(userMap.toDto(user2)).thenReturn(userDto2);

        List<UserDto> allUsers = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        verify(userMap, times(1)).toDto(user1);
        verify(userMap, times(1)).toDto(user2);
        assertEquals(Arrays.asList(userDto1, userDto2), allUsers);
    }

    @Test
    public void testUpdateUser() {
        UserEntity user = new UserEntity();
        Long userId = 1L;

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity updatedUser = userService.updateUser(user, userId);

        verify(userRepository, times(1)).save(user);
        assertEquals(user, updatedUser);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        UserEntity user = new UserEntity();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserEntity deletedUser = userService.deleteUser(1L, userId);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(user);
        assertEquals(user, deletedUser);
    }

    @Test
    public void testDeleteUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.deleteUser(1L, userId));
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any());
    }

}
