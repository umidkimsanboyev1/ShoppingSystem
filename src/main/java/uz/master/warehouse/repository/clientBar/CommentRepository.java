package uz.master.warehouse.repository.clientBar;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.clientBar.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
