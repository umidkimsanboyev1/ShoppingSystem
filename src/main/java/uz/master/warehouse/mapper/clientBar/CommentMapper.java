package uz.master.warehouse.mapper.clientBar;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.comment.CommentCreateDto;
import uz.master.warehouse.dto.comment.CommentDto;
import uz.master.warehouse.dto.comment.CommentUpdateDto;
import uz.master.warehouse.entity.clientBar.Comment;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, CommentDto, CommentCreateDto, CommentUpdateDto> {
    @Override
    CommentDto toDto(Comment comment);

    @Override
    List<CommentDto> toDto(List<Comment> e);

    @Override
    Comment fromCreateDto(CommentCreateDto commentCreateDto);

    @Override
    Comment fromUpdateDto(CommentUpdateDto commentUpdateDto);
}
