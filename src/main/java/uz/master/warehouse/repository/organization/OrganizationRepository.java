package uz.master.warehouse.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.master.warehouse.entity.organization.Organization;

import javax.transaction.Transactional;
import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Transactional
    @Modifying
    @Query(value = "update Organization o set o.deleted = true where o.id = :orgId")
    void deleteOrganization(@Param("orgId") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Organization set name=:name   where id=:id")
    void updateOrg(@Param(value = "id") Long id, @Param(value = "name") String name);


    Organization findByIdAndDeletedFalse(Long id);


    List<Organization> findAllByDeletedFalse();

    @Transactional
    @Modifying
    @Query(value = "update Organization set logoPath=:path   where id=:id")
    void updateLogo(@Param(value = "path") String path,@Param(value = "id") Long id);

}
