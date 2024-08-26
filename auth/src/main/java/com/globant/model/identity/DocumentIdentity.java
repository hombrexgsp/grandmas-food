package com.globant.model.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class DocumentIdentity {

    @Column(name = "document_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private DocumentType documentType;

    @Column(name = "document_number", nullable = false)
    @NotNull
    @Digits(integer = 20, fraction = 0)
    private Long documentNumber;

    public DocumentIdentity(Long documentNumber, DocumentType documentType) {
        this.documentNumber = documentNumber;
        this.documentType = documentType;
    }

    public DocumentIdentity() {
    }

    public Long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return documentType + "-" + documentNumber;
    }
}
