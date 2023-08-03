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


    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoardById(@PathVariable(name = "boardId") Long boardId) {
        BoardResponseDto dto = boardService.getBoardById(boardId);
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@PathVariable(name = "boardId") Long boardId,
                                              @RequestBody BoardRequestDto updateDto,
                                              HttpServletRequest request){
        boardService.updateBoard(boardId, updateDto, request);
        return ResponseEntity.ok("게시글 수정 완료");
    }


    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable(name = "boardId") Long boardId,
                                              HttpServletRequest request){
        boardService.deleteBoard(boardId, request);
        return  ResponseEntity.ok("Board deleted.");
    }



}
