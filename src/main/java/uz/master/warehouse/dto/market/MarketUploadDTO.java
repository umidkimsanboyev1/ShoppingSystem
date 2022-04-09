package uz.master.warehouse.dto.market;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class MarketUploadDTO {
    private Long marketId;
    private List<MultipartFile> files;
}
