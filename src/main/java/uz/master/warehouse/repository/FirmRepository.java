package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Long> {
}
