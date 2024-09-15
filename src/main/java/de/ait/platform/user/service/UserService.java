package de.ait.platform.user.service;
import de.ait.platform.article.dto.RequestArticle;
import de.ait.platform.article.dto.ResponseArticle;
import de.ait.platform.user.dto.UserLoginDto;
import de.ait.platform.user.dto.UserRequestDto;
import de.ait.platform.user.dto.UserResponseDto;
import de.ait.platform.user.reposittory.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{
    UserResponseDto createUser(UserLoginDto dto);
    UserResponseDto updateUser(Long id, UserRequestDto dto);
    UserResponseDto deleteUser(Long id);
    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Long id);
    List<UserResponseDto> getUserByEmail(String email);
    UserResponseDto setAdminRole(String email, boolean admin);


}
