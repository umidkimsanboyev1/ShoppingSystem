package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.Items;
@Repository
public interface ItemsRepository  extends JpaRepository<Items,Long> {
}
