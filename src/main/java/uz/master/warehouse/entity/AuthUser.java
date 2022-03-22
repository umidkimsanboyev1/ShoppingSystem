package uz.master.warehouse.entity;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;
import uz.master.warehouse.enums.Role;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AuthUser extends Auditable {

    private String fullName;

    private String username;

    private String phoneNumber;

    private String password;

    private String picturePath;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Organization.class)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    private Role role;
}
