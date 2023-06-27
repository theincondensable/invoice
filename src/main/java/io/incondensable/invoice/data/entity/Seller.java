package io.incondensable.invoice.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abbas
 */
@Entity(name = "t_seller")
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String department;
    private String companyName;
    private String information;
    private String phoneNumber;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "seller"
    )
    private Set<Invoice> invoices = new HashSet<>();

    public Set<Invoice> getInvoices() {
        return Collections.unmodifiableSet(invoices);
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void addInvoice(Invoice invoice) {
        if (invoice == null)
            throw new NullPointerException("Null Invoice can't be added.");
        invoices.add(invoice);
        invoice.setSeller(this);
    }
}
