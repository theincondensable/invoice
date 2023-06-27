package io.incondensable.invoice.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author abbas
 */
@Entity(name = "t_product")
@Table
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private Long basePrice;
    private Long totalPrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}
