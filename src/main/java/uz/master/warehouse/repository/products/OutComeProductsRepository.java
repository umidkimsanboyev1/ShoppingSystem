package uz.master.warehouse.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.dto.outComeProducts.OutComeProductsCreateDto;
import uz.master.warehouse.entity.products.OutComeProducts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutComeProductsRepository extends JpaRepository<OutComeProducts, Long> {
    @Query(value = "insert into out_come_products(client_bar_id,count,product_id,product_price) values( ?1 , ?2 , ?3 , ?4)  returning id ",nativeQuery = true)
//    @Query(value = "select alld()")
    Long saveOutCome(Long barId,Integer count,Long productId, Double productPrice);

    List<OutComeProducts> findAllByClientBarId(Long clientId);

    void deleteAllByClientBarId(Long id);

    @Query(value = "SELECT e.* FROM out_come_products e WHERE DATE(e.created_at) =:createdAt_date", nativeQuery = true)
    List<OutComeProducts> findAllByCreatedAt_Date(LocalDate createdAt_date);
}
