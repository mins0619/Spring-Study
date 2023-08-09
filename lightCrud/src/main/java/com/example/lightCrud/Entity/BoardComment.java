package com.example.lightCrud.Entity;

import com.example.lightCrud.Dto.Comment.CommentRequestDto;
import com.example.lightCrud.Dto.board.BoardRequestDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본생성자 생성
public class BoardComment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BoardComment(CommentRequestDto requestDto, Board board, User user){
        this.content = requestDto.getContent();
        this.board = board;
        this.user = user;
    }
    public void updateComment(CommentRequestDto updateDto){
        this.content = updateDto.getContent();
    }

}
