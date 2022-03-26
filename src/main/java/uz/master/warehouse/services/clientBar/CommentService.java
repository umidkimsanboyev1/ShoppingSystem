package uz.master.warehouse.services.clientBar;

import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.validator.clientBar.CommentValidator;
import uz.master.warehouse.dto.comment.CommentCreateDto;
import uz.master.warehouse.dto.comment.CommentDto;
import uz.master.warehouse.dto.comment.CommentUpdateDto;
import uz.master.warehouse.entity.clientBar.Comment;
import uz.master.warehouse.mapper.clientBar.CommentMapper;
import uz.master.warehouse.repository.clientBar.CommentRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;

import java.util.List;

public class CommentService extends AbstractService<CommentRepository, CommentMapper, CommentValidator> implements GenericCrudService<Comment, CommentDto, CommentCreateDto, CommentUpdateDto, Long> {
    public CommentService(CommentRepository repository, CommentMapper mapper, CommentValidator validator) {
        super(repository, mapper, validator);
    }


    @Override
    public DataDto<Long> create(CommentCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public DataDto<Long> update(CommentUpdateDto updateDto) {
        return null;
    }

    @Override
    public DataDto<List<?>> getAll() {
        return null;
    }

    @Override
    public DataDto<?> get(Long id) {
        return null;
    }
}
