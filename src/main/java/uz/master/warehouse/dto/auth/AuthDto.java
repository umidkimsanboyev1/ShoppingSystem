package uz.master.warehouse.dto.auth;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.enums.Role;

import javax.persistence.Column;

public class AuthDto implements BaseDto {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    private String picturePath;

    @Column(nullable = false)
    private Long orgId;

    @Column(nullable = false)
    private Role role;

}
