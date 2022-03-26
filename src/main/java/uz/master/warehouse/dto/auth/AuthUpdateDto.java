package uz.master.warehouse.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Getter
@Setter

public class AuthUpdateDto extends GenericDto {

    @NotBlank
    private String fullName;

    @NotBlank
    private String username;

    @NotBlank
    private String phoneNumber;


    private Role role;
}
