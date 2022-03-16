package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long>{
}
