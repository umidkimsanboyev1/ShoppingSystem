package uz.master.warehouse.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
