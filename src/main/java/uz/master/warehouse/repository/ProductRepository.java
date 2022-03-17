package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
