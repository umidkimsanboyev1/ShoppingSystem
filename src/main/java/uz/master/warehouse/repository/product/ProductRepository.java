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
//    @Query(value = "update auth_user  set deleted=true , username = (username || ?2 )  where id = ?1 " ,nativeQuery = true)
    @Query(value = "update product o set o.deleted = true, o.model  = (o.model || ?3 ) where o.id = ?1 and o.org_id =?2", nativeQuery = true)
    void deleteProduct(@Param("id") Long id, @Param("orgId") Long orgId, @Param("str") String string);

    Optional<Product> findByIdAndDeletedFalse(Long id);

    List<Product> findAllByOrgIdAndDeletedFalse(Long orgId);

    List<Product> findAllByOrgIdAndFirmId(Long id, Long orgId);

    Optional<Product> findByIdAndOrgIdAndDeletedFalse(Long id, Long orgId);
}
