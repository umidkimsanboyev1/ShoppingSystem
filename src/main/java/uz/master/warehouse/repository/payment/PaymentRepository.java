package uz.master.warehouse.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.payment.Payment;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Payment  set deleted=true where id=:payId")
    void deletePayment(@Param("payId") Long payId);

//    @Transactional
//    @Modifying
//    @Query(value = "update Payment set sum=:sum  where id=:id")
//    void updatePayment(@Param(value = "id") Long id, @Param(value = "sum") Long sum);


    Payment findByIdAndDeletedFalse(Long id);


    List<Payment> findAllByDeletedFalse();
}
