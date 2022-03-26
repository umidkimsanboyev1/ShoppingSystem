package uz.master.warehouse.mapper.auth;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.auth.AuthCreateDto;
import uz.master.warehouse.dto.auth.AuthDto;
import uz.master.warehouse.dto.auth.AuthUpdateDto;
import uz.master.warehouse.entity.auth.AuthUser;
import uz.master.warehouse.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser, AuthDto, AuthCreateDto, AuthUpdateDto> {
}
