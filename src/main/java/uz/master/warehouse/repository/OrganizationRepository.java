package uz.master.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
