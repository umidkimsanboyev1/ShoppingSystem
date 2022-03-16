package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
