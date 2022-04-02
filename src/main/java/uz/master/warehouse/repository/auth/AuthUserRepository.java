package uz.master.warehouse.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.master.warehouse.entity.auth.AuthUser;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);

    @Transactional
    @Modifying
    @Query(value = "update auth_user  set deleted=true , username = (username || ?2 )  where id = ?1 " ,nativeQuery = true)
    void delete(Long id,String token);
}
