package com.blogapi.controller;

import com.blogapi.dto.request.board.PatchBoardRequestDto;
import com.blogapi.dto.request.board.PostBoardRequestDto;
import com.blogapi.dto.request.board.PostCommentRequestDto;
import com.blogapi.dto.response.board.*;
import com.blogapi.service.BoardService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 특정 게시글 가져오기
    @GetMapping("/{boardNumber}")
    public ResponseEntity<?super GetBoardResponseDto> getBoard(
            @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getBoard(boardNumber);
    }

    // 좋아요 목록 가져오기
    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<?super GetFavoriteListResponseDto> getFavoriteList(
            @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getFavoriteList(boardNumber);
    }

    // 댓글 목록 가져오기
    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<?super GetCommentListResponseDto> getCommentList(
            @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getCommentList(boardNumber);
    }

    // 조회수 증가
    @GetMapping("/{boardNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
            @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.increaseViewCount(boardNumber);
    }

    // 최신 게시물 리스트
    @GetMapping("/latest-list")
    public ResponseEntity<?super GetLatestBoardListResponseDto> getLatestBoardList() {
        return boardService.getLatestBoardList();
    }

    // 주간 상위 3 게시물 리스트
    @GetMapping("/top-3")
    public ResponseEntity<?super GetTop3BoardListResponseDto> getTop3BoardList() {
        return boardService.getTop3BoardList();
    }


    // 검색 게시물 목록 가져오기
    @GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
    public ResponseEntity<?super GetSearchBoardListResponseDto> getSearchBoardList(
            @PathVariable("searchWord") String searchWord,
            @PathVariable(value = "preSearchWord", required = false) String preSearchWord
    ) {
        return boardService.getSearchBoardList(searchWord, preSearchWord);
    }

    // 특정 유저 게시물 리스트
    @GetMapping("/user-board-list/{email}")
    public ResponseEntity<?super GetUserBoardListResponseDto> getUserBoardList(
            @PathVariable("email") String email
    ) {
        return boardService.getUserBoardList(email);
    }

    // 게시글 등록
    @PostMapping("")
    public ResponseEntity<?super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        return boardService.postBoard(requestBody, email);
    }

    // 댓글 등록
    @PostMapping("/{boardNumber}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
            @RequestBody @Valid PostCommentRequestDto requestBody,
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String email
    ) {
        return boardService.postComment(requestBody, boardNumber, email);
    }

    // 좋아요 기능 설정
    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String email
    ) {
        return boardService.putFavorite(boardNumber, email);
    }

    // 게시물 수정
    @PatchMapping("/{boardNumber}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
            @RequestBody @Valid PatchBoardRequestDto requestBody,
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String email
    ) {
        return boardService.patchBoard(requestBody, boardNumber, email);
    }

    // 게시물 삭제
    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String email
    ) {
        return boardService.deleteBoard(boardNumber, email);
    }


}
