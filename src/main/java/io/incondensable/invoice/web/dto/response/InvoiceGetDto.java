package io.incondensable.invoice.web.dto.response;

import io.incondensable.invoice.data.entity.Invoice;
import io.incondensable.invoice.web.dto.vo.BuyerDto;
import io.incondensable.invoice.web.dto.vo.ProductDto;
import io.incondensable.invoice.web.dto.vo.SellerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abbas
 */
@Getter
@Setter
@AllArgsConstructor
public final class InvoiceGetDto {
    private SellerDto seller;
    private BuyerDto buyer;
    private String address;
    private List<ProductDto> products;
    private LocalDate issueDate;
    private String invoiceNumber;

    public static InvoiceGetDto createInstanceFrom(Invoice invoice) {
        List<ProductDto> products = new ArrayList<>(invoice.getProducts().size());
        invoice.getProducts().forEach(
                product -> products.add(ProductDto.createInstanceFrom(product))
        );

        return new InvoiceGetDto(
                SellerDto.createInstanceFrom(invoice.getSeller()),
                BuyerDto.createInstanceFrom(invoice.getBuyer()),
                invoice.getBuyer().getAddress().getCompleteAddress(),
                products,
                invoice.getCreatedAt().toLocalDateTime().toLocalDate(),
                invoice.getInvoiceNumber()
        );
    }
}
