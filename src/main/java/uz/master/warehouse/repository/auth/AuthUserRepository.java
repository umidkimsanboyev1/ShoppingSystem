package uz.master.warehouse.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.auth.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);
}
