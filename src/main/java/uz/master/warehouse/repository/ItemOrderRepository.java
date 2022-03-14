package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.ItemOrder;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

}
