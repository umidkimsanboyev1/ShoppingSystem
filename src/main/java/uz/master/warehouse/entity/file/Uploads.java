package uz.master.warehouse.entity.file;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Uploads extends Auditable {

    private String originalName;
    private String generatedName;
    private String contentType;
    private long size;
    private String path;

    public Uploads(String originalFilename, String generatedName, String contentType, String s, long size) {
        this.originalName=originalFilename;
        this.generatedName=generatedName;
        this.contentType=contentType;
        this.path=s;
        this.size=size;
    }
}
