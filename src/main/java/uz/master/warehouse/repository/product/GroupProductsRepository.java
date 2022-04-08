package uz.master.warehouse.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.product.GroupProducts;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Panjiyev Javohir, сб 16:58. 26.03.2022
 */
@Repository
public interface GroupProductsRepository extends JpaRepository<GroupProducts, Long> {
    @Transactional
    @Modifying
    @Query(value = "update GroupProducts o set o.deleted = true where o.id = :grPrId")
    void deleteGroupProducts(@Param("grPrId") Long id);

    GroupProducts findByIdAndDeletedFalse(Long id);


    List<GroupProducts> findAllByDeletedFalse();

    @Query(value = "select  count(*) from group_products where not deleted and id=?1", nativeQuery = true)
    int existsGroupProduct(Long productId);

    @Query(value = "SELECT e.* FROM group_products e WHERE DATE(e.date) >=?1 and DATE(e.date) <=?2", nativeQuery = true)
    List<GroupProducts> findAllByDateTimeDateBetween(LocalDate from, LocalDate to);

    @Query(value = "SELECT e.* FROM group_products e WHERE DATE(e.date) >=?1 and DATE(e.date) <=?2 and e.company_id=?3", nativeQuery = true)
    List<GroupProducts> findAllByDateTimeDateBetweenAndCompanyName(LocalDate from, LocalDate to, Long companyId);



//    @Query(
//            value = "SELECT e.date FROM group_products e " +
//            "where e.date between cast(:from as date) and cast(:to as date)",
//            nativeQuery = true)
//    List<Date> getSizeDate(@Param(value = "from") String from, @Param(value = "to") String to);




}
