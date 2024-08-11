package com.blogapi.service.implement;

import com.blogapi.dto.object.BoardListItem;
import com.blogapi.dto.object.CommentListItem;
import com.blogapi.dto.object.FavoriteListItem;
import com.blogapi.dto.request.board.PatchBoardRequestDto;
import com.blogapi.dto.request.board.PostBoardRequestDto;
import com.blogapi.dto.request.board.PostCommentRequestDto;
import com.blogapi.dto.response.board.*;
import com.blogapi.entity.*;
import com.blogapi.exception.ExceptionHandler;
import com.blogapi.repository.*;
import com.blogapi.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;
    private final SearchLogRepository searchLogRepository;
    private final BoardListViewRepository boardListViewRepository;

    private final ExceptionHandler exceptionHandler;

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {
        if (!boardRepository.existsByBoardNumber(boardNumber)) {
            return GetFavoriteListResponseDto.noExistBoard();
        }

        try {
            List<FavoriteListItem> favoriteListItems = favoriteRepository.getFavoriteList(boardNumber);
            return GetFavoriteListResponseDto.success(favoriteListItems);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {
        if (!boardRepository.existsByBoardNumber(boardNumber)) {
            return GetCommentListResponseDto.noExistBoard();
        }

        try {
            List<CommentListItem> commentListItems = commentRepository.getCommentList(boardNumber);
            return GetCommentListResponseDto.success(commentListItems);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        try {
            List<BoardListViewEntity> boardListViewEntities = boardListViewRepository.findByOrderByWriteDatetimeDesc();
            return GetLatestBoardListResponseDto.success(boardListViewEntities);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        try {
            List<BoardListViewEntity> boardListViewEntities = boardListViewRepository.getTop3BoardList(LocalDateTime.now());
            return GetTop3BoardListResponseDto.success(boardListViewEntities);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {
        try {
            List<BoardListViewEntity> boardListViewEntities = boardListViewRepository.getSearchBoardList(searchWord);
            logSearch(searchWord, preSearchWord);
            return GetSearchBoardListResponseDto.success(boardListViewEntities);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
        Optional<BoardListItem> boardOptional = Optional.ofNullable(boardRepository.getBoard(boardNumber));
        if (boardOptional.isEmpty()) {
            return GetBoardResponseDto.noExistBoard();
        }

        try {
            List<ImageEntity> imageEntities = imageRepository.findByBoardNumber_BoardNumber(boardNumber);
            return GetBoardResponseDto.success(boardOptional.get(), imageEntities);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {
        if (!userRepository.existsByEmail(email)) {
            return PostCommentResponseDto.noExistUser();
        }

        try {
            List<BoardListViewEntity> boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByWriteDatetimeDesc(email);
            return GetUserBoardListResponseDto.success(boardListViewEntities);
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        if (!userRepository.existsByEmail(email)) {
            return PostBoardResponseDto.noExistUser();
        }

        try {
            UserEntity writer = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            BoardEntity boardEntity = BoardEntity.create(dto.getTitle(), dto.getContent(), writer);
            boardRepository.save(boardEntity);

            saveBoardImages(dto.getBoardImageList(), boardEntity);

            return PostBoardResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email) {
        if (!boardRepository.existsByBoardNumber(boardNumber)) {
            return PostCommentResponseDto.noExistBoard();
        }
        if (!userRepository.existsByEmail(email)) {
            return PostCommentResponseDto.noExistUser();
        }

        try {
            UserEntity userEntity = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            CommentEntity commentEntity = CommentEntity.create(boardEntity, dto.getContent(), userEntity);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

            return PostCommentResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email) {
        if (!userRepository.existsByEmail(email)) {
            return PutFavoriteResponseDto.noExistUser();
        }
        if (!boardRepository.existsByBoardNumber(boardNumber)) {
            return PutFavoriteResponseDto.noExistBoard();
        }

        try {
            UserEntity userEntity = userRepository.findById(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            FavoriteEntity existFavoriteEntity = favoriteRepository.findByBoardNumberAndUserEmail(boardEntity, userEntity);

            if (existFavoriteEntity == null) {
                FavoriteEntity favoriteEntity = FavoriteEntity.create(userEntity, boardEntity);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            } else {
                favoriteRepository.delete(existFavoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }

            boardRepository.save(boardEntity);
            return PutFavoriteResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email) {
        Optional<BoardEntity> boardOptional = Optional.ofNullable(boardRepository.findByBoardNumber(boardNumber));
        if (boardOptional.isEmpty()) {
            return PatchBoardResponseDto.noExistBoard();
        }
        if (!userRepository.existsByEmail(email)) {
            return PatchBoardResponseDto.noExistUser();
        }

        try {
            BoardEntity boardEntity = boardOptional.get();
            if (!boardEntity.getWriterEmail().getEmail().equals(email)) {
                return PatchBoardResponseDto.noPermission();
            }

            boardEntity.patchBoard(dto);
            boardRepository.save(boardEntity);

            imageRepository.deleteByBoardNumber_BoardNumber(boardNumber);
            saveBoardImages(dto.getBoardImageList(), boardEntity);

            return PatchBoardResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {
        Optional<BoardEntity> boardOptional = Optional.ofNullable(boardRepository.findByBoardNumber(boardNumber));
        if (boardOptional.isEmpty()) {
            return IncreaseViewCountResponseDto.noExistBoard();
        }

        try {
            BoardEntity boardEntity = boardOptional.get();
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);
            return IncreaseViewCountResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
        if (!userRepository.existsByEmail(email)) {
            return DeleteBoardResponseDto.noExistUser();
        }

        Optional<BoardEntity> boardOptional = Optional.ofNullable(boardRepository.findByBoardNumber(boardNumber));
        if (boardOptional.isEmpty()) {
            return DeleteBoardResponseDto.noExistBoard();
        }

        try {
            BoardEntity boardEntity = boardOptional.get();
            if (!boardEntity.getWriterEmail().getEmail().equals(email)) {
                return DeleteBoardResponseDto.noPermission();
            }

            imageRepository.deleteByBoardNumber_BoardNumber(boardNumber);
            commentRepository.deleteByBoardNumber_BoardNumber(boardNumber);
            favoriteRepository.deleteByBoardNumber_BoardNumber(boardNumber);
            boardRepository.delete(boardEntity);

            return DeleteBoardResponseDto.success();
        } catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }
    }

    private void saveBoardImages(List<String> imageUrls, BoardEntity boardEntity) {
        List<ImageEntity> imageEntities = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ImageEntity imageEntity = ImageEntity.create(boardEntity, imageUrl);
            imageEntities.add(imageEntity);
        }
        imageRepository.saveAll(imageEntities);
    }

    private void logSearch(String searchWord, String preSearchWord) {
        SearchLogEntity searchLogEntity = SearchLogEntity.create(searchWord, preSearchWord, false);
        searchLogRepository.save(searchLogEntity);
        if (preSearchWord != null) {
            searchLogEntity = SearchLogEntity.create(preSearchWord, searchWord, true);
            searchLogRepository.save(searchLogEntity);
        }
    }
}
