package uz.master.warehouse.session;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.repository.auth.AuthUserRepository;
import uz.master.warehouse.services.auth.AuthUserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionUser {

    private final AuthUserRepository repository;


    public String getUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getId() {
        Optional<AuthUser> user = repository.findByUsernameAndDeletedFalse(this.getUsername());
        return user.get().getId();
    }

    public Long getOrgId() {
        Optional<AuthUser> user = repository.findByUsernameAndDeletedFalse(this.getUsername());
        return user.get().getOrganizationId();
    }
}
