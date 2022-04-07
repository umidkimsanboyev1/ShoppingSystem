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
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.criteria.GroupProductsCriteria;
import uz.master.warehouse.dto.InComeProducts.InComeProductsDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsCreateDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsDto;
import uz.master.warehouse.dto.groupProducts.GroupProductsUpdateDto;
import uz.master.warehouse.dto.organization.OrganizationDto;
import uz.master.warehouse.dto.payment.PaymentDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.product.GroupProducts;
import uz.master.warehouse.services.download.GroupProductBetweenDatePdfService;
import uz.master.warehouse.services.product.GroupProductsService;
import uz.master.warehouse.services.product.ProductService;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Panjiyev Javohir, сб 17:14. 26.03.2022
 */

@RestController
@RequestMapping("/groupProducts/*")
@RequiredArgsConstructor
public class GroupProductsController extends AbstractController {

    private final GroupProductsService service;
    private final GroupProductBetweenDatePdfService groupProductBetweenDatePdfService;

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PostMapping("/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody GroupProductsCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @PutMapping("/update")
    public ResponseEntity<DataDto<Long>> update(@Valid @RequestBody GroupProductsUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping(PATH + "/listCriteria")
    public ResponseEntity<DataDto<List<GroupProductsDto>>> getWithCriteria(GroupProductsCriteria criteria) {
        return new ResponseEntity<>(service.getWithCriteria(criteria), HttpStatus.OK);
    }



    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/list")
    public ResponseEntity<DataDto<List<GroupProductsDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DataDto<GroupProductsDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMIN','WAREHOUSEMAN')")
    @RequestMapping(value = "/betweenTimeDownload", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> paymentReport() {
        ///for test
//        String fromDate, String toDate
        String fromDate="2022-03-10";
        String toDate="2022-10-11";
        List<GroupProductsDto> byTimeBetween = service.getByTimeBetween(fromDate, toDate);
        ByteArrayInputStream bis = groupProductBetweenDatePdfService.groupProductReport(byTimeBetween, fromDate, toDate);
        HttpHeaders headers = new HttpHeaders();
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
