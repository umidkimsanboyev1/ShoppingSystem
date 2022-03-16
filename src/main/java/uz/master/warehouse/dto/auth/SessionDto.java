package uz.master.warehouse.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class SessionDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long refreshTokenExpire;
    private Long issuedAt;
    private Long expiresIn;
}
