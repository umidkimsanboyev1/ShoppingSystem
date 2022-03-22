package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.Market;

import javax.transaction.Transactional;
import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {

    List<Market> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "update Market m set m.deleted = true where m.id = :marketId")
    void deleteMarket(@Param("marketId") Long id);

    Market findByIdAndDeletedFalse(Long id);
}
