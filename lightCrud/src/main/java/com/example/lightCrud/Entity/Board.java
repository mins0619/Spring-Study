package com.example.lightCrud.Entity;

import com.example.lightCrud.Dto.board.BoardRequestDto;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Table(name = "board")
public class Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // 긴 글을 대비한 로직
    private String content;

    // Many-to-One relationship with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Board(BoardRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }


    public void updateBoard(BoardRequestDto updateDto){
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
    }


}