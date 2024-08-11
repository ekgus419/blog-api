package com.blogapi.service.implement;

import com.blogapi.dto.request.auth.LoginRequestDto;
import com.blogapi.dto.request.auth.JoinUpRequestDto;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.auth.LoginResponseDto;
import com.blogapi.dto.response.auth.JoinUpResponseDto;
import com.blogapi.entity.UserEntity;
import com.blogapi.exception.ExceptionHandler;
import com.blogapi.jwt.JwtProvider;
import com.blogapi.repository.UserRepository;
import com.blogapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private final ExceptionHandler exceptionHandler;

    @Override
    public ResponseEntity<? super JoinUpResponseDto> joinUp(JoinUpRequestDto dto) {
        try {
            if (userRepository.existsByEmail(dto.getEmail())) {
                return JoinUpResponseDto.duplicateEmail();
            }
            if (userRepository.existsByNickname(dto.getNickname())) {
                return JoinUpResponseDto.duplicateNickname();
            }
            if (userRepository.existsByTelNumber(dto.getTelNumber())) {
                return JoinUpResponseDto.duplicateTelNumber();
            }

            UserEntity userEntity = UserEntity.create(
                    dto.getEmail(),
                    passwordEncoder.encode(dto.getPassword()),
                    dto.getNickname(),
                    dto.getTelNumber(),
                    dto.getAddress(),
                    dto.getAddressDetail(),
                    dto.isAgreedPersonal()
            );

            userRepository.save(userEntity);
            return JoinUpResponseDto.success();

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super LoginResponseDto> login(LoginRequestDto dto) {
        try {
            UserEntity userEntity = userRepository.findByEmail(dto.getEmail());
            if (userEntity == null || !passwordEncoder.matches(dto.getPassword(), userEntity.getPassword())) {
                return LoginResponseDto.loginFail();
            }

            String token = jwtProvider.create(dto.getEmail());
            return LoginResponseDto.success(token);

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }
}
