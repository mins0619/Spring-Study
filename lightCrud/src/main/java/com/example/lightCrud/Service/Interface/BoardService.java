package com.example.lightCrud.Service.Interface;

import com.example.lightCrud.Dto.board.BoardRequestDto;
import com.example.lightCrud.Dto.board.BoardResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {
    BoardResponseDto getBoardById(Long boardId);
    void getBoardCreate( BoardRequestDto requestDto,
                     HttpServletRequest request);

    List<BoardResponseDto> getAllBoards();

    void updateBoard(Long boardId, BoardRequestDto updateDto,
                     HttpServletRequest request);
    void deleteBoard(Long boardId, HttpServletRequest request);
}
