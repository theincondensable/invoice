package io.incondensable.invoice.web.dto.vo;

import io.incondensable.invoice.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author abbas
 */
@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private Long basePrice;
    private Long totalPrice;

    public static ProductDto createInstanceFrom(Product product) {
        return new ProductDto(
                product.getName(),
                product.getBasePrice(),
                product.getTotalPrice()
        );
    }
}
