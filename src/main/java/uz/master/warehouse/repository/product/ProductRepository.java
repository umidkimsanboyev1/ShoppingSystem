package uz.master.warehouse.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.product.Product;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Product o set o.deleted = true where o.id = :id and o.orgId =:orgId")
    void deleteProduct(@Param("id") Long id, @Param("orgId") Long orgId);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findAllByOrgIdAndDeletedFalse(Long orgId);

    List<Product> findAllByOrgIdAndFirmId(Long id, Long orgId);

    Optional<Product> findByIdAndOrgIdAndDeletedFalse(Long id, Long orgId);
}
