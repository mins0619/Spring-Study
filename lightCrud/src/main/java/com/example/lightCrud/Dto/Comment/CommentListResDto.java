package com.example.lightCrud.Dto.Comment;

import com.example.lightCrud.Entity.BoardComment;

import java.time.format.DateTimeFormatter;

public class CommentListResDto {
    private String userName;
    private String content;
    private String createDate;

    public  CommentListResDto(BoardComment comment){
        this.userName = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createDate = comment.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
