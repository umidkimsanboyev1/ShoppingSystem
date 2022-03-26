package uz.master.warehouse.repository.products;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.product.Product;
import uz.master.warehouse.entity.products.WareHouseProducts;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface WareHouseProductsRepository extends JpaRepository<WareHouseProducts, Long> {

    @Transactional
    @Modifying
    @Query(value = "update WareHouseProducts o set o.deleted = true where o.id = :orgId")
    void deleteWareHouseProductsById(@Param("orgId") Long id);

    Optional<WareHouseProducts> findByIdAndDeletedFalse(Long id);

    List<WareHouseProducts> findAllByDeletedFalse();

}
