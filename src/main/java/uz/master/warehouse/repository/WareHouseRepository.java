package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

}
