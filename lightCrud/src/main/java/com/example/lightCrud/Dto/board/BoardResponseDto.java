package com.example.lightCrud.Dto.board;

import com.example.lightCrud.Entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;

    public BoardResponseDto(Board board){
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
