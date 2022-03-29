package uz.master.warehouse.repository.wareHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.wareHouse.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
