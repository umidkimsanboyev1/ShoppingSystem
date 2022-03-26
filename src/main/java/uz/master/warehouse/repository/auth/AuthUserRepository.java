package uz.master.warehouse.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.master.warehouse.entity.auth.AuthUser;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsernameDAndDeletedFalse(String username);

    @Transactional
    @Modifying
    @Query("update AuthUser  set deleted=true where id = ?1 ")
    void delete(Long id);
}
