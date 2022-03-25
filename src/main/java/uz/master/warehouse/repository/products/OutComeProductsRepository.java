package uz.master.warehouse.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.products.OutComeProducts;

@Repository
public interface OutComeProductsRepository extends JpaRepository<OutComeProducts, Long> {
}
