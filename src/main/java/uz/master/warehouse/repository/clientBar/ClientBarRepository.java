package uz.master.warehouse.repository.clientBar;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.clientBar.ClientBar;

public interface ClientBarRepository extends JpaRepository<ClientBar, Long> {

//    List<ClientBar> findAllByTakenFalse();
//    ClientBar findByIdAndTakenFalse(Long id);
}
