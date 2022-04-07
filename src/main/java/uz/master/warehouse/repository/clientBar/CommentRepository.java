package uz.master.warehouse.repository.clientBar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.clientBar.Comment;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByDeletedFalse();

    @Query(value = "select count(*) from comment c where c.id=?1 and c.author_id=?2",nativeQuery = true)
    Optional<Comment> findByIdAndDeletedFalse(Long id, Long sessionId);

    @Transactional
    @Modifying
    @Query(value = "update Comment c set c.deleted =  true where c.id =?1")
    void deleteComment(Long id);
}
