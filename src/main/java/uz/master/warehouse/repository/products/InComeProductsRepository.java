package uz.master.warehouse.repository.products;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.master.warehouse.entity.products.InComeProducts;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface InComeProductsRepository extends JpaRepository<InComeProducts, Long> {

    @Query(value = "select  count(*) from product where id=?1", nativeQuery = true)
    int existsByProduct(Long productId);


    @Query(value = "select  count(*) from group_products where id=?1", nativeQuery = true)
    int existsGroupProduct(Long productId);

    @Query(value = "SELECT e.* FROM in_come_products e WHERE DATE(e.created_at) >=?1 and DATE(e.created_at) <=?2", nativeQuery = true)
    List<InComeProducts> findAllByDateTimeDateBetween(LocalDate from, LocalDate to);


    List<InComeProducts> findAllByGroupProductsId(Long groupProductsId);

    @Transactional
    @Modifying
    @Query(value = "update InComeProducts c set c.deleted =  true  where c.id = ?1 ")
    void deleteIncome(Long id);

    @Query(value = "select count(*) from in_come_products where id=?1 and not deleted", nativeQuery = true)
    Long findbyIncome(Long id);

    @Query(value = "select  from in_come_products where id=?1 and not deleted", nativeQuery = true)
    Optional<InComeProducts> findByIncome(Long id);

    List<InComeProducts> findAllByDeletedFalse(PageRequest request);
}
