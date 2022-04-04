package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.payment.PaymentCreateDto;
import uz.master.warehouse.dto.payment.PaymentDto;
import uz.master.warehouse.dto.payment.PaymentUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.payment.PaymentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentController extends AbstractController {

    private final PaymentService service;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody @Valid PaymentCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping(PATH + "/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping(PATH + "/update")
    public void update(@RequestBody PaymentUpdateDto dto) {
        service.update(dto);
    }

    @GetMapping(PATH + "/list")
    public ResponseEntity<DataDto<List<PaymentDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<PaymentDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }


    @GetMapping(PATH + "/getByTime")
    public ResponseEntity<DataDto<List<PaymentDto>>> getByDate(String fromDate, String toDate) {
        return new ResponseEntity<>(service.getByTime(fromDate, toDate), HttpStatus.OK);
    }

}
