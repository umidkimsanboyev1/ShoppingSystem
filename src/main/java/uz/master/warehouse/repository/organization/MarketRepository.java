package uz.master.warehouse.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.organization.Market;
import uz.master.warehouse.entity.organization.Organization;

import javax.transaction.Transactional;
import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {

    List<Market> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "update Market m set m.deleted = true where m.id = :marketId")
    void deleteMarket(@Param("marketId") Long id);

    Market findByIdAndDeletedFalse(Long id);

    @Query(value = "select org from Organization org where org.id=:id")
    Organization findByOrgId(Long id);

    @Transactional
    @Modifying
    @Query(value = "update Market set name=:name, location=:location, description=:description where id=:id")
    void update(@Param(value = "id") Long id, @Param(value = "name")String name, @Param(value = "location")String location,@Param(value = "description") String description);

    @Transactional
    @Modifying
    @Query(value ="insert into market_picture(market_id, picture_path) values (?2,?1)" ,nativeQuery = true)
    void savePicture( String store,  Long marketId);
}
