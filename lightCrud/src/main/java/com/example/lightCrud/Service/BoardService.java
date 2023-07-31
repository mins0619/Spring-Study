package com.example.lightCrud.Service;

import com.example.lightCrud.Dto.request.BoardRequestDto;
import com.example.lightCrud.Dto.response.BoardResponseDto;
import com.example.lightCrud.Repository.BoardRepository;
import com.example.lightCrud.Entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        List<BoardResponseDto> responseDtoList = new ArrayList<>();
        for (Board board : boards) { // 강화 For ent를 만들었다는걸 알려줌
            BoardResponseDto responseDto = new BoardResponseDto(board);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    public void getCreate(BoardRequestDto requsetDto){
        Board board = new Board(requsetDto.getTitle(), requsetDto.getContent());
        boardRepository.save(board);
    }

    public BoardResponseDto getBoard(Long id) {
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
    }







}
