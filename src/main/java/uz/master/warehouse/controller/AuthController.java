package uz.master.warehouse.controller;


import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.auth.*;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.auth.AuthUserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController extends AbstractController {
    private final AuthUserService authUserService;

    @PostMapping(PATH+"/auth/upload")
    public ResponseEntity<DataDto<String>> uploadPicture(@RequestParam("picture") MultipartFile picture, WebRequest request){
        try {
            authUserService.savePicture(picture);
            return new ResponseEntity<>(new DataDto<>("successfully uploaded "),HttpStatus.OK);
        }catch (Exception e){
            AppErrorDto dto=new AppErrorDto();
            dto.setCode("417");
            dto.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            dto.setMessage("Could not upload the file: " + picture.getOriginalFilename() + "!");
            dto.setPath(request.getContextPath());
            dto.setTimestamp(new Timestamp(new Date().getTime()));
            return new ResponseEntity<>(new DataDto<>(dto),HttpStatus.OK);
        }

    }

    @GetMapping(PATH+"/auth/profile")
    public ResponseEntity<DataDto<AuthDto>>profile(){
        return new ResponseEntity<>(authUserService.get(),HttpStatus.OK);
    }

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
