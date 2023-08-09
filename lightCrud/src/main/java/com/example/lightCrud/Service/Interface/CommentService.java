package com.example.lightCrud.Service.Interface;

import com.example.lightCrud.Dto.Comment.CommentListResDto;
import com.example.lightCrud.Dto.Comment.CommentRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    List<CommentListResDto> getBoardComment(Long boardId);
    void addComment(Long boardId, CommentRequestDto requestDto, HttpServletRequest request);
    void deleteComment(Long commentId, HttpServletRequest request);
}
