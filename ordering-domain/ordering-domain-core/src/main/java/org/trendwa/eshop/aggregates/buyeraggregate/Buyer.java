package org.trendwa.eshop.aggregates.buyeraggregate;

import org.trendwa.eshop.seedwork.AggregateRoot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Buyer extends AggregateRoot<UUID> {

    private final String name;
    private final String identityGuid;
    private final List<PaymentMethod> paymentMethods = new ArrayList<>();

    private Buyer(Builder builder) {
        super.setId(builder.id);
        this.name = builder.name;
        this.identityGuid = builder.identityGuid;
        this.paymentMethods.addAll(builder.paymentMethods);
    }

    public String getName() {
        return this.name;
    }

    public String getIdentityGuid() {
        return this.identityGuid;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return Collections.unmodifiableList(paymentMethods);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String identityGuid;
        private List<PaymentMethod> paymentMethods = new ArrayList<>();

        private Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder identityGuid(String identityGuid) {
            this.identityGuid = identityGuid;
            return this;
        }

        public Builder paymentMethods(List<PaymentMethod> paymentMethods) {
            if (paymentMethods != null) {
                // defensive copy
                this.paymentMethods = new ArrayList<>(paymentMethods);
            }
            return this;
        }

        public Buyer build() {
            return new Buyer(this);
        }
    }

    // TODO: payment domain functions (addPaymentMethod, removePaymentMethod, verifyPaymentMethod, etc.)

}