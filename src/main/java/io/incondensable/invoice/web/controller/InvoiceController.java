package io.incondensable.invoice.web.controller;

import io.incondensable.global.api.ApiResponse;
import io.incondensable.invoice.web.InvoiceService;
import io.incondensable.invoice.web.dto.request.InvoiceCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author abbas
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/{invoiceId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getInvoice(@PathVariable long invoiceId) {
        return ResponseEntity.of(Optional.of(new ApiResponse<>(
                invoiceService.getInvoiceById(invoiceId),
                "Invoice Successfully created.",
                "Invoice Successfully created.",
                HttpStatus.CREATED
        )));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiResponse<?>> createInvoice(@RequestBody InvoiceCreateDto dto) {
        invoiceService.createInvoice(dto);
        return ResponseEntity.of(Optional.of(new ApiResponse<>(
                null,
                "Invoice Successfully created.",
                "Invoice Successfully created.",
                HttpStatus.CREATED
        )));
    }

}
