package uz.master.warehouse.repository.products;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.products.WareHouseProducts;

@Repository
public interface WareHouseProductsRepository extends JpaRepository<WareHouseProducts, Long> {
}
