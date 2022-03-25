package uz.master.warehouse.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.InComeProducts;

@Repository
public interface InComeProductsRepository extends JpaRepository<InComeProducts, Long> {
}
