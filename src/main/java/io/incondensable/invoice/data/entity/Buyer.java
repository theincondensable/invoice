package io.incondensable.invoice.data.entity;

import io.incondensable.invoice.data.entity.vt.Address;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author abbas
 */
@Entity(name = "t_buyer")
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    @Embedded private Address address;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "buyer"
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
        invoice.setBuyer(this);
    }
}
