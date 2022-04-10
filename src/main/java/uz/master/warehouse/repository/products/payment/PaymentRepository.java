package uz.master.warehouse.repository.products.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.master.warehouse.entity.payment.Payment;

import javax.transaction.Transactional;
import java.time.LocalDate;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Transactional
    @Modifying
    @Query(value = "update Payment  set deleted=true where id=:payId")
    void deletePayment(@Param("payId") Long payId);


    @Transactional
    @Modifying
    @Query(value = "update Payment p set p.sum=:sum   where p.id=:payId")
    void updatePayment(@Param(value = "payId") Long id, @Param(value = "sum") Long sum);

    Payment findByIdAndDeletedFalse(Long id);


    List<Payment> findAllByDeletedFalse();

    @Query(value = "SELECT e.* FROM payment e WHERE DATE(e.date_time) >=?1 and DATE(e.date_time) <=?2", nativeQuery = true)
    List<Payment> findAllByDateTimeDateBetween(LocalDate from, LocalDate to);

//    @Query(value = "SELECT e FROM payment e WHERE DATE(e.date_time) >=?1 and DATE(e.date_time) <=?2")
//    List<Payment> findAllByDateTimeDateBetween(LocalDate from, LocalDate to);

}
