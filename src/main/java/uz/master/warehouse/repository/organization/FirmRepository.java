package uz.master.warehouse.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.organization.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Long> {
}
