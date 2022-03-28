package uz.master.warehouse.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.product.GroupProducts;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Panjiyev Javohir, сб 16:58. 26.03.2022
 */
public interface GroupProductsRepository extends JpaRepository<GroupProducts, Long> {
    @Transactional
    @Modifying
    @Query(value = "update GroupProducts o set o.deleted = true where o.id = :grPrId")
    void deleteGroupProducts(@Param("grPrId") Long id);

    GroupProducts findByIdAndDeletedFalse(Long id);


    List<GroupProducts> findAllByDeletedFalse();


}
