package uz.master.warehouse.repository.products;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.products.WareHouseProducts;

import java.util.Optional;

@Repository
public interface WareHouseProductsRepository extends JpaRepository<WareHouseProducts, Long> {
    @Query(value = "from WareHouseProducts w where w.productId= ?1 and w.count >= ?2 ")
        Optional<WareHouseProducts> get(Long productId, int count);


//    bugun organadigan narsa
//    @Override
//    Page<WareHouseProducts> findAll(Pageable pageable);
}
