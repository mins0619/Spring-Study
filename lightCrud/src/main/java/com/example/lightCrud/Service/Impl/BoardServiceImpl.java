
package com.example.lightCrud.Service.Impl;

import com.example.lightCrud.Dto.board.BoardRequestDto;
import com.example.lightCrud.Dto.board.BoardResponseDto;
import com.example.lightCrud.Entity.User;
import com.example.lightCrud.Repository.BoardRepository;
import com.example.lightCrud.Entity.Board;
import com.example.lightCrud.Repository.UserRepository;
import com.example.lightCrud.Service.Interface.BoardService;
import com.example.lightCrud.Service.Interface.UserService;
import com.example.lightCrud.enums.UserRole;
import com.example.lightCrud.error.ErrorCode;
import com.example.lightCrud.error.exception.NotFoundException;
import com.example.lightCrud.error.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boards) { // 강화 For ent를 만들었다는걸 알려줌
            BoardResponseDto responseDto = new BoardResponseDto(board);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }



    @Override
    public void getBoardCreate( BoardRequestDto requsetDto, HttpServletRequest request){
        User user = userService.findUserByToken(request);
        if(user.getUserRole() != UserRole.USER) {
            throw new UnAuthorizedException("401 권한 없음", ErrorCode.NOT_ALLOW_WRITE_EXCEPTION);
        }
        Board board = new Board(requsetDto,user);
        boardRepository.save(board);

    }


    @Override
    public BoardResponseDto getBoardById(Long boardId) {
        Board board = boardRepository.findByIdAndDeletedIsFalse(boardId);
        if(board == null) throw new NotFoundException("게시물 없음", ErrorCode.NOT_FOUND_EXCEPTION);
        BoardResponseDto dto = new BoardResponseDto(board);
        return dto;
    }






   /* public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        return new BoardResponseDto(board); // DTO 생성자를 통해 엔티티를 파라미터로 받아 만들어서
    }

    public void getUpdate(Long id, BoardRequestDto updateDto){
        Board board = boardRepository.findById(id)
                .orElseThrow(()-> {
                    throw new RuntimeException();
                });
        board.CUpdate(updateDto);
        boardRepository.save(board);

    }

    public void getDelete(Long id){
        boardRepository.deleteById(id);
    }*/


}
