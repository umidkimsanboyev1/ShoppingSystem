package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.payment.PaymentCreateDto;
import uz.master.warehouse.dto.payment.PaymentDto;
import uz.master.warehouse.dto.payment.PaymentUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.download.BetweenDatePdfService;
import uz.master.warehouse.services.payment.PaymentService;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/payment/*")
@RequiredArgsConstructor
public class PaymentController extends AbstractController {

    private final PaymentService service;
    private final BetweenDatePdfService betweenDatePdfService;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody PaymentCreateDto dto) {
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

    @RequestMapping(value = "/betweenTimeDownload", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> paymentReport(String fromDate, String toDate) {
        ///for test
//        String fromDate="2022-03-10";
//        String toDate="2022-10-10";
        List<PaymentDto> byTimeBetween = service.getByTimeBetween(fromDate, toDate);
        ByteArrayInputStream bis = betweenDatePdfService.paymentReport(byTimeBetween, fromDate, toDate);
        var headers = new HttpHeaders();
        String now = LocalDateTime.now().toString();
        String filename = now + ".pdf";
        headers.add("Content-Disposition", "inline; filename=" + filename);


        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
