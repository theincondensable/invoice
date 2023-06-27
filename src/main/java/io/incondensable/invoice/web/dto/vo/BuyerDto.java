package io.incondensable.invoice.web.dto.vo;

import io.incondensable.invoice.data.entity.Buyer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abbas
 */
@Getter
@Setter
@AllArgsConstructor
public class BuyerDto {
    private String name;
    private String phoneNumber;

    public static BuyerDto createInstanceFrom(Buyer buyer) {
        return new BuyerDto(
                buyer.getName(),
                buyer.getPhoneNumber()
        );
    }
}
