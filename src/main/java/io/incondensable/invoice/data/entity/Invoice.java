package io.incondensable.invoice.data.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author abbas
 */
@Entity(name = "t_invoice")
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "invoice"
    )
    private Set<Product> products = new HashSet<>();
    private String invoiceNumber;
    private Timestamp createdAt;

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Null Product can't be added.");
        products.add(product);
        product.setInvoice(this);
    }

    /**
     * @return creates a Dummy InvoiceNumber.
     */
    public String createDummyInvoiceNumber() {
        var rand = new Random();
        var s1 = rand.nextInt(100_000, 999_999);
        var s2 = rand.nextInt(10_000, 99_999);
        return s1 + "/" + s2;
    }
}
