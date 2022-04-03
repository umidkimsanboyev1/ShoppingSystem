package uz.master.warehouse.repository.file;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.file.Uploads;

import java.util.Optional;

public interface UploadsRepository extends JpaRepository<Uploads,Long> {
    Optional<Uploads> findByGeneratedName(String filename);
}
