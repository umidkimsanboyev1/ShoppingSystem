package uz.master.warehouse.repository.clientBar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.dto.clientBar.ClientBarDto;
import uz.master.warehouse.entity.clientBar.ClientBar;

import javax.transaction.Transactional;
import java.util.List;

public interface ClientBarRepository extends JpaRepository<ClientBar, Long> {

    @Transactional
    @Modifying
    @Query(value = "update ClientBar c set c.deleted =  true where c.id =:clientBarId")
    void deleteClientBar(@Param("clientBarId") Long id);

    List<ClientBar> findAllByDeletedFalse();

    ClientBar findByIdAndDeletedFalse(Long id);

    boolean existsByIdAndDeletedFalse(Long clientBarId);

//    List<ClientBar> findAllByTakenFalse();
//    ClientBar findByIdAndTakenFalse(Long id);
}
