package io.incondensable.invoice.web.dto.vo;

import io.incondensable.invoice.data.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abbas
 */
@Setter
@Getter
@AllArgsConstructor
public final class SellerDto {
    private String department;
    private String companyName;
    private String information;
    private String phoneNumber;

    public static SellerDto createInstanceFrom(Seller seller) {
        return new SellerDto(
                seller.getDepartment(),
                seller.getCompanyName(),
                seller.getInformation(),
                seller.getPhoneNumber()
        );
    }
}
