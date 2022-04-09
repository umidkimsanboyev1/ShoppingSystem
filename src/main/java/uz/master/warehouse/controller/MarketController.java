package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.market.MarketCreateDto;
import uz.master.warehouse.dto.market.MarketDto;
import uz.master.warehouse.dto.market.MarketUpdateDto;
import uz.master.warehouse.dto.market.MarketUploadDTO;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.organization.MarketService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/market/*")
@RequiredArgsConstructor
public class MarketController extends AbstractController {

    private final MarketService service;


    @PostMapping(PATH+"/create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody MarketCreateDto dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @PostMapping(value = PATH+"/auth/upload/{marketId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DataDto<String>> uploadPicture( @PathVariable(name = "marketId") Long marketId, @RequestBody MultipartFile[] multipartFile){
        try {
            service.savePicture(multipartFile,marketId);
            return new ResponseEntity<>(new DataDto<>("successfully uploaded "),HttpStatus.OK);
        }
        catch (Exception e){
            AppErrorDto dto=new AppErrorDto();
            dto.setCode("417");
            dto.setStatus(HttpStatus.EXPECTATION_FAILED.value());
            dto.setMessage("Could not upload the file: " + multipartFile[0].getOriginalFilename() + "!");
            dto.setTimestamp(new Timestamp(new Date().getTime()));
            return new ResponseEntity<>(new DataDto<>(dto),HttpStatus.OK);
        }

    }






    @DeleteMapping(PATH+"/delete/{id}")
    public ResponseEntity<DataDto> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping(PATH+"/update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody MarketUpdateDto dto) {
        return new ResponseEntity<>(service.update(dto), HttpStatus.OK);
    }


    @GetMapping(PATH+"/list")
    public ResponseEntity<DataDto<List<MarketDto>>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);

    }


    @GetMapping(PATH+"/get/{id}")
    public ResponseEntity<DataDto<MarketDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK);
    }


}
