package org.trendwa.eshop.aggregates.buyeraggregate;

import org.trendwa.eshop.seedwork.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentMethod extends Entity<UUID> {

    private final String alias;
    private final String cardNumber;
    private final String securityCode;
    private final String cardHolderName;
    private final CardType cardType;
    private final LocalDateTime expirationDate;

    private PaymentMethod(Builder builder) {
        super.setId(builder.id);
        this.alias = builder.alias;
        this.cardNumber = builder.cardNumber;
        this.securityCode = builder.securityCode;
        this.cardHolderName = builder.cardHolderName;
        this.cardType = builder.cardType;
        this.expirationDate = builder.expirationDate;
    }

    public String getAlias() {
        return alias;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String alias;
        private String cardNumber;
        private String securityCode;
        private String cardHolderName;
        private CardType cardType;
        private LocalDateTime expirationDate;
        private UUID id;

        private Builder() {
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder securityCode(String securityCode) {
            this.securityCode = securityCode;
            return this;
        }

        public Builder cardHolderName(String cardHolderName) {
            this.cardHolderName = cardHolderName;
            return this;
        }

        public Builder cardType(CardType cardType) {
            this.cardType = cardType;
            return this;
        }

        public Builder expirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }

    @Override
    public boolean equals(Object other) {
        // TODO: if card number and security code and card holder name are the same, then they are the same card. implement this.
        return super.equals(other);
    }
}
