package com.blogapi.service;

import com.blogapi.dto.request.board.PatchBoardRequestDto;
import com.blogapi.dto.request.board.PostBoardRequestDto;
import com.blogapi.dto.request.board.PostCommentRequestDto;
import com.blogapi.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    // 특정 게시물 가져오기
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    // 좋아요 목록 가져오기
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    // 최신 게시물 가져오기
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();

    // 주간 상위
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();

    // 검색 게시물 목록 가져오기
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

    // 특정 유저 게시물 리스트
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);

    // 댓글 목록 가져오기
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

    // 게시물 등록
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    // 댓글 등록
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);

    // 좋아요 설정
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    // 게시물 수정
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);

    // 조회수 증가
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

    // 게시물 삭제
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);

}

