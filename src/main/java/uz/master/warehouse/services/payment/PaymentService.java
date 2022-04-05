package uz.master.warehouse.services.payment;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.master.warehouse.dto.payment.PaymentCreateDto;
import uz.master.warehouse.dto.payment.PaymentDto;
import uz.master.warehouse.dto.payment.PaymentUpdateDto;
import uz.master.warehouse.dto.responce.AppErrorDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.payment.Payment;
import uz.master.warehouse.mapper.payment.PaymentMapper;
import uz.master.warehouse.repository.payment.PaymentRepository;
import uz.master.warehouse.services.AbstractService;
import uz.master.warehouse.services.GenericCrudService;
import uz.master.warehouse.validator.payment.PaymentValidator;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentService extends AbstractService<
        PaymentRepository,
        PaymentMapper> implements GenericCrudService<
        Payment,
        PaymentDto,
        PaymentCreateDto,
        PaymentUpdateDto,
        Long> {


    public PaymentService(PaymentRepository repository,
                          PaymentMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public DataDto<Long> create(@Valid PaymentCreateDto createDto) {

        Payment payment = mapper.fromCreateDto(createDto);
        payment.setOrganizationId(createDto.getOrganizationId());
        payment.setCompanyId(createDto.getCompanyId());
        payment.setSum(createDto.getSum());
        Payment save = repository.save(payment);
        return new DataDto<>(save.getId());
    }

    @Override
    public DataDto<Void> delete(Long id) {
        repository.deletePayment(id);
        return new DataDto<>();
    }

    @Override
    public DataDto<Long> update(@Valid PaymentUpdateDto updateDto) {
//        if (!validator.validForUpdate(updateDto)) {
//            return new DataDto<>(new AppErrorDto("Not Valid On Update", HttpStatus.CONFLICT));
//        }
        Payment payment = mapper.fromUpdateDto(updateDto);
        payment.setSum(updateDto.getSum());
        repository.updatePayment(payment.getId(), payment.getSum());
        return new DataDto<>(payment.getId());
    }

    @Override
    public DataDto<List<PaymentDto>> getAll() {
        List<Payment> list = repository.findAllByDeletedFalse();
        return new DataDto<>(mapper.toDto(list));
    }

    @Override
    public DataDto<PaymentDto> get(Long id) {
        Payment payment = repository.findByIdAndDeletedFalse(id);
        if (Objects.isNull(payment)) {
            return new DataDto<>(new AppErrorDto(HttpStatus.NOT_FOUND, "Payment not found", "payment/get"));

        }
        return new DataDto<>(mapper.toDto(payment));
    }

    public DataDto<List<PaymentDto>> getByTime(String fromDate, String toDate) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        List<Payment> allByDateTimeDateBetween = repository.findAllByDateTimeDateBetween(from, to);
        return new DataDto<>(mapper.toDto(allByDateTimeDateBetween));
    }

    public List<PaymentDto> getByTimeBetween(String fromDate, String toDate) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);
        List<Payment> allByDateTimeDateBetween = repository.findAllByDateTimeDateBetween(from, to);
        return mapper.toDto(allByDateTimeDateBetween);
    }
}
