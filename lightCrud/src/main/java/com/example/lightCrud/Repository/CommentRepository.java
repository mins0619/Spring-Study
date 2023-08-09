package com.example.lightCrud.Repository;

import com.example.lightCrud.Entity.Board;
import com.example.lightCrud.Entity.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findAllByBoard(Board board);
    Optional<BoardComment> findById(Long id);
}
