package com.example.lightCrud.Controller;

import com.example.lightCrud.Dto.request.BoardRequestDto;
import com.example.lightCrud.Dto.response.BoardResponseDto;
import com.example.lightCrud.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // final 쓰는 친구들은 Aw~ 제외하고 Required 사용
@RequestMapping("/boards")
public class BoardContorller {

    private final BoardService boardService;



    @PostMapping
    public void getCreate(@RequestBody BoardRequestDto requsetDto) {
        boardService.getCreate(requsetDto);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardList = boardService.getAllBoards();
        return ResponseEntity.ok(boardList);
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/{id}")
    public void getUpdate(@PathVariable Long id, @RequestBody BoardRequestDto updateDto){
        boardService.getUpdate(id,updateDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> getDelete(@PathVariable Long id){
        boardService.getDelete(id);
        return  ResponseEntity.ok("Board deleted.");
    }



}
