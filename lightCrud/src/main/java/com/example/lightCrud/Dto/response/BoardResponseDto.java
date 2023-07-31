package com.example.lightCrud.Dto.response;

import com.example.lightCrud.Entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;

    public BoardResponseDto() {

    }

    public BoardResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
