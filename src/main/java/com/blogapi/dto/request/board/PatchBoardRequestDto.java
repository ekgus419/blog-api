package com.blogapi.dto.request.board;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
    private String title;
    private String content;
    private List<String> boardImageList;
}

