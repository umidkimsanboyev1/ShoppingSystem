package uz.master.warehouse.repository.clientBar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.clientBar.ClientBar;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClientBarRepository extends JpaRepository<ClientBar, Long> {

    @Transactional
    @Modifying
    @Query(value = "update ClientBar c set c.deleted =  true where c.id =:clientBarId")
    void deleteClientBar(@Param("clientBarId") Long id);

   Page<ClientBar> findAllByDeletedFalse(PageRequest pageRequest);
    List<ClientBar> findAllByDeletedFalse();

    List<ClientBar> findAllByOrgIdAndDeletedFalseAndTakenFalseOrderByCreatedAtDesc(Long orgId);

    ClientBar findByIdAndDeletedFalse(Long id);

    @Query(value = "SELECT e.* FROM client_bar e WHERE e.org_id = ?1 and e.taken = true and e.deleted = false and DATE(e.created_at) =?2", nativeQuery = true)
    List<ClientBar> findAllByOrgIdAndTakenTrueAndCreatedAtAndDeletedFalse(Long orgId);
//
//    @Query(value = "from ClientBar c where c.deleted = false and c.taken = true and c.orgId = ?1 and c.createdAt::date = current_date")
//    List<ClientBar> findAllByOrgIdAndTakenTrueAndCreatedAtAndDeletedFalse2(Long orgId);


    boolean existsByIdAndDeletedFalse(Long clientBarId);



//    List<ClientBar> findAllByTakenFalse();
//    ClientBar findByIdAndTakenFalse(Long id);
}
