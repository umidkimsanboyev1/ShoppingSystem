package uz.master.warehouse.dto.auth;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.enums.Role;

import javax.persistence.Column;

public class AuthDto extends GenericDto {

    private String fullName;

    private String username;

    private String phoneNumber;

    private Long orgId;

    private Role role;

}
