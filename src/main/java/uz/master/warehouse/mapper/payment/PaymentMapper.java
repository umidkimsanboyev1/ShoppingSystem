package uz.master.warehouse.mapper.payment;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.payment.PaymentCreateDto;
import uz.master.warehouse.dto.payment.PaymentDto;
import uz.master.warehouse.dto.payment.PaymentUpdateDto;
import uz.master.warehouse.entity.payment.Payment;
import uz.master.warehouse.mapper.BaseMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseMapper<
        Payment, PaymentDto, PaymentCreateDto, PaymentUpdateDto> {
    @Override
    PaymentDto toDto(Payment payment);

    @Override
    List<PaymentDto> toDto(List<Payment> e);

    @Override
    Payment fromCreateDto(PaymentCreateDto paymentCreateDto);

    @Override
    Payment fromUpdateDto(PaymentUpdateDto paymentUpdateDto);
}
