# 블로그 API 서비스

이 블로그 프로젝트는 사용자가 게시물을 등록, 수정, 삭제하고, 좋아요 및 댓글을 남길 수 있는 기능을 제공합니다. 
또한, 마이페이지에서 닉네임과 프로필을 수정할 수 있습니다. 
디자인 패턴을 고려한 결과, 팩토리 패턴을 적용하여 객체 생성 로직을 중앙에서 관리하고 코드의 유연성과 유지 보수성을 높였습니다.

## 개발 환경

* Intellij IDEA Ultimate
* Java 17
* Gradle 8.8
* Spring Boot 3.3.2

## 기술 세부 스택

Spring Boot

* Spring Data JPA
  * JPA 및 Hibernate 지원
* Spring Security
  * 보안 기능
* Spring Validation
  * 데이터 검증
* Jwt
  * 인증을 위한 JSON Web Tokens (jjwt-api, jjwt-impl, jjwt-jackson)
* MySQL Driver
  * MySQL을 위한 JDBC 드라이버
* Lombok
  *  보일러플레이트 코드를 줄이기 위한 라이브러리
* QueryDSL
  * 타입 안전한 쿼리 작성을 위한 프레임워크

## 유즈케이스 다이어그램
* ![usecase](https://github.com/user-attachments/assets/d46e8e54-db2a-4130-802a-dfe359bee48f)

## 팩토리 메소드
빌더 패턴도 고려했으나, 객체의 복잡한 생성 과정이 아닌 특정 객체의 생성과 초기화 일관성을 유지하는데 더 적합하다고 판단하여 
팩토리 패턴을 선택하였습니다.

```java
@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    //... 필드 및 메소드 ...

    // 팩토리 메소드 - BoardEntity 생성 로직을 캡슐화
    public static BoardEntity create(String title, String content, UserEntity writerEmail) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.title = title;
        boardEntity.content = content;
        boardEntity.writeDatetime = LocalDateTime.now();
        boardEntity.favoriteCount = 0;
        boardEntity.commentCount = 0;
        boardEntity.viewCount = 0;
        boardEntity.writerEmail = writerEmail;
        return boardEntity;
    }
}

@Override
public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
  // ... 코드 생략 ...

  try {
    UserEntity writer = userRepository.findById(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    BoardEntity boardEntity = BoardEntity.create(dto.getTitle(), dto.getContent(), writer);
    boardRepository.save(boardEntity);

    // ... 코드 생략 ...

    return PostBoardResponseDto.success();
  } catch (Exception exception) {
    return exceptionHandler.handleException(exception);
  }
}
```

위와 같이 객체를 생성할 때 직접 생성자를 호출하지 않고, 팩토리 메소드를 사용하여 객체를 생성하여
성 로직을 일관되게 관리하고, 객체 생성의 복잡성을 줄이려 하였습니다.