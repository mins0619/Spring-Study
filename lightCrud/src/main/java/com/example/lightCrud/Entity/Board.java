package com.example.lightCrud.Entity;

import com.example.lightCrud.Dto.request.BoardRequestDto;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Data
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // 긴 글을 대비한 로직
    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void CUpdate(BoardRequestDto updateDto){
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
    }

}