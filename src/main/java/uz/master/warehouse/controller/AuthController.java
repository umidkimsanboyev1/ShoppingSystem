package uz.master.warehouse.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.auth.*;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.auth.AuthUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AuthUserService authUserService;

    @PostMapping(PATH + "/auth/login")
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody AuthUserDto loginDto) {
        return new ResponseEntity<>(authUserService.login(loginDto),HttpStatus.OK);
    }

    @Secured(value = "ADMIN")
    @PostMapping(PATH+"/auth/create")
    public ResponseEntity<DataDto<Long>>create(@RequestBody AuthCreateDto dto){
        return new ResponseEntity<>(authUserService.createUser(dto), HttpStatus.OK);
    }

    @PostMapping(PATH+"/auth/refresh")
    public ResponseEntity<DataDto<SessionDto>>refreshToken(@RequestBody AuthRefreshToken token, HttpServletRequest request){
        return new ResponseEntity<>(authUserService.refreshToken(token.getToken(),request),HttpStatus.OK);
    }

    @PatchMapping(PATH+"/auth/update")
    public  ResponseEntity<DataDto<Long>>update(@RequestBody AuthUpdateDto dto){
        return new ResponseEntity<>(authUserService.update(dto),HttpStatus.OK);
    }

    @DeleteMapping(PATH+"/auth/delete")
    public void delete(@RequestParam Long id,@RequestParam Long adminId){
        authUserService.delete(id,adminId);
    }
}
