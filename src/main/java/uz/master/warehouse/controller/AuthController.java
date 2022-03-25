package uz.master.warehouse.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.master.warehouse.dto.auth.AuthUserDto;
import uz.master.warehouse.dto.auth.SessionDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.auth.AuthUserService;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController{
    private final AuthUserService authUserService;

    @PostMapping(PATH + "/auth/login")
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody AuthUserDto loginDto) {
        return authUserService.login(loginDto);
    }
}
