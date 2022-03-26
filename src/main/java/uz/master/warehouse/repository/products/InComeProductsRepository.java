package uz.master.warehouse.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.products.InComeProducts;

@Repository
public interface InComeProductsRepository extends JpaRepository<InComeProducts, Long> {

    @Query(value = "select  count(*) from product where id=?1",nativeQuery = true)
    int existsByProduct(Long productId);


    @Query(value = "select  count(*) from group_products where id=?1",nativeQuery = true)
    int existsGroupProduct(Long productId);
}
