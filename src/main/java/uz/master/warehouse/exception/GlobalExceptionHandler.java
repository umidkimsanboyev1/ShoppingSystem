package uz.master.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<DataDto<AppErrorDto>> http(RuntimeException e, WebRequest request) {
        return new ResponseEntity<>(new DataDto<>(new AppErrorDto(e.getMessage(), request, HttpStatus.OK)), HttpStatus.OK);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<DataDto<AppErrorDto>> notFound(NotFoundException e, WebRequest request) {
        return new ResponseEntity<>(new DataDto<>(new AppErrorDto(e.getMessage(),request, HttpStatus.NOT_FOUND)),HttpStatus.OK);
    }


}
