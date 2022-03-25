package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.ClientBar;

import java.util.List;

public interface ClientBarRepository extends JpaRepository<ClientBar, Long> {

//    List<ClientBar> findAllByTakenFalse();
//    ClientBar findByIdAndTakenFalse(Long id);
}
