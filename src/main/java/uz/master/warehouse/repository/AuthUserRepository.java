package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

}
