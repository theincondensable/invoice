package io.incondensable.invoice.web.dto.request;

import io.incondensable.invoice.data.entity.Buyer;
import io.incondensable.invoice.data.entity.Invoice;
import io.incondensable.invoice.data.entity.Product;
import io.incondensable.invoice.data.entity.Seller;
import io.incondensable.invoice.data.entity.vt.Address;
import io.incondensable.invoice.web.dto.vo.BuyerDto;
import io.incondensable.invoice.web.dto.vo.ProductDto;
import io.incondensable.invoice.web.dto.vo.SellerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author abbas
 */
@Getter
@Setter
@AllArgsConstructor
public final class InvoiceCreateDto {
    private SellerDto seller;
    private BuyerDto buyer;
    private BuyerAddressCreateDto address;
    private Set<ProductDto> products;

    public Seller createSellerFromDto(Invoice invoice) {
        Seller seller = new Seller();
        seller.setCompanyName(this.seller.getCompanyName());
        seller.setPhoneNumber(this.seller.getPhoneNumber());
        seller.setDepartment(this.seller.getDepartment());
        seller.setInformation(this.seller.getInformation());
        seller.addInvoice(invoice);
        return seller;
    }

    public Buyer createBuyerFromDto(Invoice invoice) {
        Address address = new Address(
                this.address.getCountry(),
                this.address.getState(),
                this.address.getCity(),
                this.address.getMainStreet()
        );
        Buyer buyer = new Buyer();
        buyer.setAddress(address);
        buyer.setName(this.buyer.getName());
        buyer.setPhoneNumber(this.buyer.getPhoneNumber());
        buyer.addInvoice(invoice);
        return buyer;
    }

    public Set<Product> createProductsFromDto(Invoice invoice) {
        Set<Product> products = new HashSet<>(this.products.size());
        int i = 0;
        for (ProductDto dto : this.products) {
            Product p = new Product();
            p.setName(dto.getName());
            p.setBasePrice(dto.getBasePrice());
            p.setTotalPrice(dto.getTotalPrice());
            products.add(p);
            invoice.addProduct(p);
            i++;
        }
        return products;
    }
}
