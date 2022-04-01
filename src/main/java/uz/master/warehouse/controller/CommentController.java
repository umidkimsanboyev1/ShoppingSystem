package uz.master.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.master.warehouse.controller.base.AbstractController;
import uz.master.warehouse.dto.comment.CommentCreateDto;
import uz.master.warehouse.dto.comment.CommentDto;
import uz.master.warehouse.dto.comment.CommentUpdateDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.services.clientBar.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment/*")

public class CommentController extends AbstractController {
    private final CommentService commentService;

    @PostMapping(PATH + "/create")
    public ResponseEntity<DataDto<Long>> create(@Valid @RequestBody CommentCreateDto dto) {
        return new ResponseEntity<>( commentService.create( dto ), HttpStatus.OK );
    }
    @GetMapping(PATH + "/get-all")
    public ResponseEntity<DataDto<List<CommentDto>>> getAll() {
        return new ResponseEntity<>( commentService.getAll(), HttpStatus.OK );
    }

    @GetMapping(PATH + "/get/{id}")
    public ResponseEntity<DataDto<CommentDto>> get(@PathVariable Long id) {
        return new ResponseEntity<>( commentService.get( id ), HttpStatus.OK );

    }

    @DeleteMapping(PATH + "/delete/{id}")
    public ResponseEntity<DataDto<Void>> delete(@PathVariable Long id) {
        return new ResponseEntity<>( commentService.delete( id ),HttpStatus.OK );

    }
    @PutMapping(PATH+"/update")
    public ResponseEntity<DataDto<Long>>update(CommentUpdateDto dto){
        return new ResponseEntity<>( commentService.update( dto ),HttpStatus.OK );
    }
}
