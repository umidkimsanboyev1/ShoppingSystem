package uz.master.warehouse.entity.clientBar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.master.warehouse.entity.base.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ClientBar extends Auditable {

    private String clientName;

    private Double overAllPrice;


    private boolean paid;

    @Override
    public boolean equals(Object obj) {
        return this.equals(obj);
    }
}
