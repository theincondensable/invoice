package io.incondensable.invoice.data.repository;

import io.incondensable.invoice.data.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author abbas
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
