package uz.master.warehouse.validator.payment;

import org.springframework.stereotype.Component;
import uz.master.warehouse.dto.payment.PaymentCreateDto;
import uz.master.warehouse.dto.payment.PaymentUpdateDto;
import uz.master.warehouse.validator.GenericValidator;

import java.util.Objects;

@Component
public class PaymentValidator implements GenericValidator<PaymentCreateDto, PaymentUpdateDto> {


    @Override
    public boolean validForCreate(PaymentCreateDto createDto) {
        return (Objects.nonNull(createDto.getOrganizationId())
                && Objects.nonNull(createDto.getCompanyId())
                && Objects.nonNull(createDto.getSum()));


    }

    @Override
    public boolean validForUpdate(PaymentUpdateDto updateDto) {
        return (Objects.nonNull(updateDto.getSum()));
    }
}
