package uz.master.warehouse.repository.wareHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.clientBar.Comment;
import uz.master.warehouse.entity.wareHouse.Sector;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByDeletedFalse();


    Sector findByIdAndDeletedFalse(Long id);

    @Transactional
    @Modifying
    @Query(value = "update Sector c set c.deleted =  true where c.id =?1")
    void deleteSector(Long id);
}
