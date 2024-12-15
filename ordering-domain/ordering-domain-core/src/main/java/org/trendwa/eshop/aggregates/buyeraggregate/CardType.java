package org.trendwa.eshop.aggregates.buyeraggregate;

import org.trendwa.eshop.seedwork.Entity;

import java.util.UUID;

public class CardType extends Entity<UUID> {
    private String name;

    private CardType(Builder builder) {
        this.name = builder.name;
        this.setId(builder.id);
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private UUID id;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public CardType build() {
            return new CardType(this);
        }
    }
}
