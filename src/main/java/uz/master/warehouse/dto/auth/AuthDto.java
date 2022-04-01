package uz.master.warehouse.dto.auth;

import lombok.Getter;
import lombok.Setter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.enums.Role;

import javax.persistence.Column;

@Getter
@Setter
public class AuthDto extends GenericDto {

    private String fullName;

    private String username;

    private String phoneNumber;

    private Long organizationId;

    private Role role;

}
