package uz.master.warehouse.repository.wareHouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.master.warehouse.entity.wareHouse.WareHouse;

import javax.transaction.Transactional;
import java.util.List;

public interface WareHouseRepository extends JpaRepository<WareHouse, Long> {

    @Transactional
    @Modifying
    @Query("update WareHouse set deleted=true where id = ?1 ")
    void deleteId(Long id);


    @Transactional
    @Modifying
    @Query("update WareHouse  set name =?1 ,location = ?2 where id=?3")
    void update(String name,String location,Long id);

    List<WareHouse>findAllByDeletedFalse();
}
