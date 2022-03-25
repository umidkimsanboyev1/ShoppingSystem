package uz.master.warehouse.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.OutComeProducts;
import uz.master.warehouse.services.products.OutComeProductsService;

@Repository
public interface OutComeProductsRepository extends JpaRepository<OutComeProducts, Long> {
}
