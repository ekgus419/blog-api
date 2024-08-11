package com.blogapi.dto.response.user;

import com.blogapi.common.ResponseResult;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.board.DeleteBoardResponseDto;
import com.blogapi.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.Getter;

@Getter
public class GetLoginUserResponseDto extends ResponseDto {

    private final String email;
    private final String nickname;
    private final String profileImage;

    public GetLoginUserResponseDto(UserEntity userEntity) {
        super(ResponseResult.SUCCESS.getCode(), ResponseResult.SUCCESS.getMessage());
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetLoginUserResponseDto> success(UserEntity userEntity) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetLoginUserResponseDto(userEntity));
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.response(HttpStatus.UNAUTHORIZED, ResponseResult.NOT_EXISTED_USER);
    }
}
