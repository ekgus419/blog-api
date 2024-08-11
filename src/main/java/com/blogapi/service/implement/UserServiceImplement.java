package com.blogapi.service.implement;

import com.blogapi.dto.request.user.PatchNicknameRequestDto;
import com.blogapi.dto.request.user.PatchProfileImageRequestDto;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.user.GetLoginUserResponseDto;
import com.blogapi.dto.response.user.GetUserProfileResponseDto;
import com.blogapi.dto.response.user.PatchNicknameResponseDto;
import com.blogapi.dto.response.user.PatchProfileImageResponseDto;
import com.blogapi.entity.UserEntity;
import com.blogapi.exception.ExceptionHandler;
import com.blogapi.repository.UserRepository;
import com.blogapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    private final ExceptionHandler exceptionHandler;

    @Override
    public ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String email) {
        UserEntity userEntity = null;
        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetLoginUserResponseDto.noExistUser();

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }

        return GetLoginUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(String email) {
        UserEntity userEntity = null;
        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetUserProfileResponseDto.noExistUser();

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
        return GetUserProfileResponseDto.success(userEntity);
    }


    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PatchNicknameResponseDto.noExistUser();

            String newNickname = dto.getNickname();
            if (newNickname == null) return PatchNicknameResponseDto.validationFailed();

            boolean exists = userRepository.existsByNickname(newNickname);
            if (exists) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.patchNickname(newNickname);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }

        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PatchProfileImageResponseDto.noExistUser();

            String newProfileImage = dto.getProfileImage();
            if (newProfileImage == null) return PatchProfileImageResponseDto.validationFailed();

            userEntity.patchProfileImage(newProfileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }

        return PatchProfileImageResponseDto.success();
    }

}

