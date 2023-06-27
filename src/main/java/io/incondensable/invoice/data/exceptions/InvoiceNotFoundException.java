package io.incondensable.invoice.data.exceptions;

import io.incondensable.global.exception.model.BusinessException;
import io.incondensable.global.exception.model.ErrorDetails;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public class InvoiceNotFoundException extends BusinessException {

    public InvoiceNotFoundException(long invoiceId) {
        super(new ErrorDetails("invoice.not.found.with.given.id", HttpStatus.NOT_FOUND, invoiceId));
    }

}
