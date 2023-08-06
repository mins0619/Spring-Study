package com.example.lightCrud.Dto.Comment;

import com.example.lightCrud.Entity.BoardComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private String userName;
    private String content;
    private String createDate;

    public  CommentResponseDto(BoardComment comment){
        this.userName = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
