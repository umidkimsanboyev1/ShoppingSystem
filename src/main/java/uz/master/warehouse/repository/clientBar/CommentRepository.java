package uz.master.warehouse.repository.clientBar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.clientBar.Comment;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDeletedFalse();


    Optional<Comment> findByIdAndDeletedFalse(Long id);
}
