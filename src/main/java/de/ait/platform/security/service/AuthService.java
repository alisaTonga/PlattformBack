package de.ait.platform.security.service;

import de.ait.platform.security.dto.TokenResponseDto;
import de.ait.platform.user.dto.UserLoginDto;
import de.ait.platform.user.dto.UserResponseDto;
import de.ait.platform.user.entity.User;
import de.ait.platform.user.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final Map<String, String> refreshTokenStorage = new HashMap<>();

    public TokenResponseDto login(UserLoginDto inboundUser) throws AuthException {
        if(inboundUser == null || inboundUser.getUsername() == null || inboundUser.getPassword() == null) {
            throw new AuthException("Username or password cannot null");
        }
        String username = inboundUser.getUsername();
        User foundUser = userService.loadUserByUsername(username);

       if(passwordEncoder.matches(inboundUser.getPassword(), foundUser.getPassword()))  {
           String accessToken = tokenService.generateAccessToken(foundUser);
           String refreshToken = tokenService.generateRefreshToken(foundUser);

           refreshTokenStorage.put(accessToken, refreshToken);
           return new TokenResponseDto(accessToken, refreshToken);


       } else {
           throw new AuthException("Password is not correct");
       }
    }

    public TokenResponseDto getNewAccessToken(String inboundRefreshToken) {
        Claims refreshClaims = tokenService.getRefreshClaims(inboundRefreshToken);
        String username = refreshClaims.getSubject();
        String savedRefreshToken = refreshTokenStorage.get(username);
        if(savedRefreshToken != null && savedRefreshToken.equals(inboundRefreshToken)) {
            User founUser = userService.loadUserByUsername(username);
            String accessToken = tokenService.generateAccessToken(founUser);
            return new TokenResponseDto(accessToken, null); //todo
        } else {
            return new TokenResponseDto(null , null); //todo
        }
    }

    public UserResponseDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = (String) authentication.getPrincipal();
        User user = userService.loadUserByUsername(userName);
        return mapper.map(user, UserResponseDto.class);
    }
}
