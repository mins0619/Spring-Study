package com.example.lightCrud.Controller;


import com.example.lightCrud.Dto.Comment.CommentListResDto;
import com.example.lightCrud.Dto.Comment.CommentRequestDto;
import com.example.lightCrud.Service.Interface.BoardService;
import com.example.lightCrud.Service.Interface.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class BoardCommentController {
    private final CommentService commentService;

    @GetMapping("/boards/{boardId}")
    public ResponseEntity<List<CommentListResDto>> getBoardComment(@PathVariable(name = "boardId") Long boardId){
        List<CommentListResDto> commentList = commentService.getBoardComment(boardId);
        return ResponseEntity.ok(commentList);
    }


    @PostMapping("/boards/{boardId}")
    public ResponseEntity<String> addComment(@PathVariable(name = "boardId") Long boardId,
                                             @RequestBody CommentRequestDto requestDto,
                                             HttpServletRequest request){
        commentService.addComment(boardId, requestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("리뷰 등록 완료");
    }

}