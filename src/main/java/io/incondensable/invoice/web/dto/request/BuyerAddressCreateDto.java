package io.incondensable.invoice.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abbas
 */
@Getter
@Setter
@AllArgsConstructor
public class BuyerAddressCreateDto {
    private String country;
    private String state;
    private String city;
    private String mainStreet;
}
