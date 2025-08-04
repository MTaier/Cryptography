package com.springboot.cryptography.entity;

import com.springboot.cryptography.service.CryptoService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_transactions")
@Getter
@Setter
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    @Column(name = "transaction_value")
    private Long transactionValue;

    @Transient
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;

    @PrePersist
    public void prePersist() {
        this.encryptedUserDocument = CryptoService.encrypt(rawUserDocument);
        this.encryptedCreditCardToken = CryptoService.encrypt(rawCreditCardToken);
    }

    @PostLoad()
    public void postLoad() {
        this.rawUserDocument = CryptoService.decrypt(encryptedUserDocument);
        this.rawCreditCardToken = CryptoService.decrypt(encryptedCreditCardToken);
    }
}
