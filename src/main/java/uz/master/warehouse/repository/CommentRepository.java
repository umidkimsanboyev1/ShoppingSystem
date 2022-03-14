package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
