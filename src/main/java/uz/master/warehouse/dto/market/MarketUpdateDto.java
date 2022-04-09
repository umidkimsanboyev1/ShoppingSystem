package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.master.warehouse.dto.GenericDto;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class MarketUpdateDto extends GenericDto {

    @NotBlank
    private String name;
    private String location;
    private String description;
    private List<MultipartFile> file;

}
