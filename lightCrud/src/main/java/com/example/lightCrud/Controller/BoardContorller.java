package com.example.lightCrud.Controller;

import com.example.lightCrud.Dto.board.BoardRequestDto;
import com.example.lightCrud.Dto.board.BoardResponseDto;
import com.example.lightCrud.Service.Interface.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor // final 쓰는 친구들은 Aw~ 제외하고 Required 사용
@RequestMapping("/boards")
public class BoardContorller {

    private final BoardService boardService;


    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> boardList = boardService.getAllBoards();
        return ResponseEntity.ok(boardList);
    }

    @PostMapping
    public  ResponseEntity<String> getBoardCreate(@RequestBody BoardRequestDto boardDto,
                                             HttpServletRequest request){
        boardService.getBoardCreate(boardDto, request);
        return ResponseEntity.ok("게시글이 생성되었습니다.");
    }


    /*@GetMapping("/{id}")
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
*/


}
