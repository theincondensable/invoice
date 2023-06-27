package io.incondensable.invoice.data.service;

import io.incondensable.invoice.data.entity.Invoice;
import io.incondensable.invoice.data.entity.Product;
import io.incondensable.invoice.data.entity.Seller;
import io.incondensable.invoice.data.exceptions.InvoiceNotFoundException;
import io.incondensable.invoice.data.repository.InvoiceRepository;
import io.incondensable.invoice.web.InvoiceService;
import io.incondensable.invoice.web.dto.request.InvoiceCreateDto;
import io.incondensable.invoice.web.dto.response.InvoiceGetDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abbas
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceGetDto getInvoiceById(long invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(
                () -> {
                    throw new InvoiceNotFoundException(invoiceId);
                }
        );
        return InvoiceGetDto.createInstanceFrom(invoice);
    }

    /**
     * <ol>things that I did not consider for this task:
     * <blockquote>
     *     <li> There is no Mapper, so that it is the Service responsible to map the DTO to Entity.</li>
     *     <li>Intentionally, there is no InvoiceNumber check for being unique.</li>
     * </blockquote>
     * </ol>
     *
     * @param dto is the Data for creating a complete Invoice.
     */
    @Override
    @Transactional
    public void createInvoice(InvoiceCreateDto dto) {
        Invoice invoice = invoiceRepository.save(new Invoice());

        invoice.setBuyer(dto.createBuyerFromDto(invoice));
        invoice.setSeller(dto.createSellerFromDto(invoice));
        invoice.setProducts(dto.createProductsFromDto(invoice));
        invoice.setInvoiceNumber(invoice.createDummyInvoiceNumber());
        invoice.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        invoiceRepository.save(invoice);
    }

}
