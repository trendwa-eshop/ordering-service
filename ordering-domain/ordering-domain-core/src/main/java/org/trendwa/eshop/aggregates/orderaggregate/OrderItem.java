package org.trendwa.eshop.aggregates.orderaggregate;

import org.trendwa.eshop.seedwork.Entity;
import org.trendwa.eshop.valueobject.Money;

import java.util.UUID;

public class OrderItem extends Entity<UUID> {

    private UUID productId;
    private String productName;
    private String pictureUrl;
    private Money unitPrice;
    private int units;
    private double discount;

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public int getUnits() {
        return units;
    }

    public double getDiscount() {
        return discount;
    }

    public static Builder builder() {
        return new Builder();
    }

    private OrderItem(Builder builder) {
        if (builder.units <= 0) {
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Invalid number of units");
        }

        if(isDiscountGreaterThanTotalPrice(builder.discount, builder.units, builder.unitPrice)){
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Discount is greater than the total price");
        }

        super.setId(builder.id);
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.pictureUrl = builder.pictureUrl;
        this.unitPrice = builder.unitPrice;
        this.units = builder.units;
        this.discount = builder.discount;
    }

    public void setNewDiscount(double discount) {
        if (discount < 0 ) {
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Discount cannot be negative");
        }

        if(isDiscountGreaterThanTotalPrice(discount, units, unitPrice)){
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Discount is greater than the total price");
        }

        this.discount = discount;
    }

    public void addUnits(int units) {
        if (units <= 0) {
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Invalid number of units");
        }

        if(isDiscountGreaterThanTotalPrice(discount, this.units + units, unitPrice)){
            // TODO: throw a domain exception
            throw new IllegalArgumentException("Discount is greater than the total price");
        }

        this.units += units;
    }

    private static boolean isDiscountGreaterThanTotalPrice(double discount, int units, Money unitPrice) {
        return discount > unitPrice.multiply(units).getAmount().doubleValue();
    }

    public static final class Builder {
        private UUID productId;
        private String productName;
        private String pictureUrl;
        private Money unitPrice;
        private int units;
        private double discount;
        private UUID id;

        private Builder() {
        }

        public Builder productId(UUID productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder pictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder unitPrice(Money unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder units(int units) {
            this.units = units;
            return this;
        }

        public Builder discount(double discount) {
            this.discount = discount;
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
