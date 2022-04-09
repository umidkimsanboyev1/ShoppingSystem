package uz.master.warehouse.entity.auth;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.enums.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AuthUser extends Auditable {

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;

    private String picturePath;


    @Column(nullable = false)
    private Long organizationId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Long marketId;
}
