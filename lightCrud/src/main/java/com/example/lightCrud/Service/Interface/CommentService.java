package com.example.lightCrud.Service.Interface;

import com.example.lightCrud.Dto.Comment.CommentListResDto;
import com.example.lightCrud.Dto.Comment.CommentRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    List<CommentListResDto> getBoardComment(Long boardId);
    
    // 댓글 추가
    void addComment(Long boardId, CommentRequestDto requestDto, HttpServletRequest request);
    // 댓글 삭제
    void deleteComment(Long commentId, HttpServletRequest request);
    // 댓글 갱신
    void updateComment(Long commentId,CommentRequestDto updateDto,HttpServletRequest request);
}
