package uz.master.warehouse.dto.auth;

import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.enums.Role;

import javax.persistence.Column;

public class AuthCreateDto implements GenericDto {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Role role;

}
