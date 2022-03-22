package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.Company;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long>{

    @Transactional
    @Modifying
    @Query(value = "update Company c set c.deleted = true where c.id = :companyId")
    void deleteCompany(@Param("companyId") Long id);

    List<Company> findAllByDeletedFalse();

    Company findByIdAndDeletedFalse(Long id);
}
