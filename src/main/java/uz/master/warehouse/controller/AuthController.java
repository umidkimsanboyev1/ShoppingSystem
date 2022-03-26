package uz.master.warehouse.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.auth.AuthCreateDto;
import uz.master.warehouse.dto.auth.AuthUpdateDto;
import uz.master.warehouse.dto.auth.AuthUserDto;
import uz.master.warehouse.dto.auth.SessionDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.auth.AuthUserService;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AuthUserService authUserService;

    @PostMapping(PATH + "/auth/login")
    public ResponseEntity<DataDto<SessionDto>> login(@RequestBody AuthUserDto loginDto) {
        return new ResponseEntity<>(authUserService.login(loginDto),HttpStatus.OK);
    }

    @PostMapping(PATH+"/auth/create")
    public ResponseEntity<DataDto<Long>>create(@RequestBody AuthCreateDto dto){
        return new ResponseEntity<>(authUserService.createUser(dto), HttpStatus.OK);
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
