package uz.master.warehouse.repository.organization;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.entity.organization.Firm;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Long> {

    @Transactional
    @Modifying
    @Query(value = "update Firm f set f.deleted = true where f.id =:firmId")
    void deleteFirm(@Param("firmId") Long id);

    List<Firm> findAllByDeletedFalse();

    List<Firm> findAllByDeletedFalse(PageRequest request);

    Firm findByIdAndDeletedFalse(Long id);


    List<Firm> findAllByDeletedFalseAndCompanyId(Long companyId);
}
