package io.incondensable.invoice.web;

import io.incondensable.invoice.data.exceptions.InvoiceNotFoundException;
import io.incondensable.invoice.web.dto.request.InvoiceCreateDto;
import io.incondensable.invoice.web.dto.response.InvoiceGetDto;

/**
 * @author abbas
 * <p><b>For the sake ofSimplicity, I decided not to include Mapper for this task hence the responsibilty
 * of InvoiceService increases.<b></p>
 */
public interface InvoiceService {

    /**
     * @param invoiceId the given Invoice ID
     * @return the Invoice
     * @throws InvoiceNotFoundException if Invoice is not found with the given ID.
     */
    InvoiceGetDto getInvoiceById(long invoiceId);

    /**
     * <p>This function is responsible for creating an Invoice by taking required fields from the Client.</p>
     * @param dto is the Data for creating a complete Invoice.
     */
    void createInvoice(InvoiceCreateDto dto);

}
