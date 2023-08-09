package com.example.lightCrud.Service.Impl;

import com.example.lightCrud.Dto.Comment.CommentListResDto;
import com.example.lightCrud.Dto.Comment.CommentRequestDto;
import com.example.lightCrud.Dto.board.BoardRequestDto;
import com.example.lightCrud.Entity.Board;
import com.example.lightCrud.Entity.BoardComment;
import com.example.lightCrud.Entity.User;
import com.example.lightCrud.Repository.BoardRepository;
import com.example.lightCrud.Repository.CommentRepository;
import com.example.lightCrud.Service.Interface.CommentService;
import com.example.lightCrud.Service.Interface.UserService;
import com.example.lightCrud.enums.UserRole;
import com.example.lightCrud.error.ErrorCode;
import com.example.lightCrud.error.exception.NotFoundException;
import com.example.lightCrud.error.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserService userService;

    @Override
    public List<CommentListResDto> getBoardComment(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        List<BoardComment> comments = commentRepository.findAllByBoard(board);
        List<CommentListResDto> commentListResDto = comments.stream()
                .map(CommentListResDto::new)
                .collect(Collectors.toList());
        return commentListResDto;
    }

    @Override
    public void addComment(Long boardId, CommentRequestDto requestDto,
                           HttpServletRequest request){
        Board board = boardRepository.findById(boardId).orElseThrow();
        User user = userService.findUserByToken(request);
        if(user.getUserRole() != UserRole.USER) {
            throw new UnAuthorizedException("401 권한 없음", ErrorCode.NOT_ALLOW_WRITE_EXCEPTION);
        }
        BoardComment comment = new BoardComment(requestDto, board, user);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId, HttpServletRequest request){
        vlidateComment(commentId, request);
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(Long commentId, CommentRequestDto updateDto, HttpServletRequest request){
        BoardComment comment = vlidateComment(commentId, request);
        comment.updateComment(updateDto);
    }


    public BoardComment vlidateComment(Long commentId, HttpServletRequest request){
        Optional<BoardComment> commentAuth = commentRepository.findById(commentId);
        User user = userService.findUserByToken(request);
        if(commentAuth.isEmpty()){
            throw new NotFoundException("게시물 없음", ErrorCode.NOT_FOUND_EXCEPTION);
        }
        BoardComment comment = commentAuth.get();
        if (!comment.getUser().equals(user)) {
            throw new UnAuthorizedException("401 권한 없음", ErrorCode.NOT_ALLOW_WRITE_EXCEPTION);
        }
        return comment;
    }


}
