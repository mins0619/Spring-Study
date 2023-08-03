package com.example.lightCrud.Dto.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;
}
