package org.trendwa.eshop.aggregates.orderaggregate;

import org.trendwa.eshop.seedwork.AggregateRoot;

import java.time.LocalDateTime;
import java.util.*;

public class Order extends AggregateRoot<UUID> {

    private final LocalDateTime orderDate;
    private final Address shippingAddress;
    private final UUID buyerId;
    private final OrderStatus orderStatus;
    private final String description;
    private final boolean isDraft;
    private Collection<OrderItem> orderItems = Collections.emptyList();

    private Order(Builder builder) {
        super.setId(builder.id);
        this.buyerId = builder.buyerId;
        this.orderItems = builder.orderItems;
        this.orderStatus = builder.orderStatus;
        this.orderDate = builder.orderDate;
        this.isDraft = builder.isDraft;
        this.description = builder.description;
        this.shippingAddress = builder.shippingAddress;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder draftOrderBuilder() {
        return new Builder().isDraft(true);
    }

    public static final class Builder {
        private LocalDateTime orderDate;
        private Address shippingAddress;
        private UUID buyerId;
        private OrderStatus orderStatus;
        private String description;
        private boolean isDraft;
        private Collection<OrderItem> orderItems;
        private UUID id;

        private Builder() {
        }

        public Builder orderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }


        public Builder shippingAddress(Address shippingAddress) {
            this.shippingAddress = shippingAddress;
            return this;
        }

        public Builder buyerId(UUID buyerId) {
            this.buyerId = buyerId;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isDraft(boolean isDraft) {
            this.isDraft = isDraft;
            return this;
        }

        public Builder orderItems(Collection<OrderItem> orderItems) {
            // Defensive copy
            this.orderItems = new ArrayList<>(orderItems);
            return this;
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    // Getters
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public UUID getBuyerId() {
        return buyerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDraft() {
        return isDraft;
    }

    public Collection<OrderItem> getOrderItems() {
        return Collections.unmodifiableCollection(orderItems);
    }

    /**
     * Adds an OrderItem to the Order.
     * <p>
     * If the OrderItem is already in the Order, the units and discount of the existing OrderItem will be updated.
     * Otherwise, the OrderItem will be added to the Order.
     * </p>
     *
     * @param orderItem the OrderItem to be added to the order
     */
    public void addOrderItem(OrderItem orderItem) {
        Optional<OrderItem> existingItemOptional = orderItems.stream().filter(oi -> oi.getProductId().equals(orderItem.getProductId()))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            OrderItem existingItem = existingItemOptional.get();
            updateExistingOrderItem(orderItem, existingItem);
        } else {
            this.orderItems.add(orderItem);
        }
    }

    private static void updateExistingOrderItem(OrderItem orderItem, OrderItem existingItem) {

        if (orderItem.getDiscount() > existingItem.getDiscount()) {
            existingItem.setNewDiscount(orderItem.getDiscount());
        }
        existingItem.addUnits(orderItem.getUnits());
    }
}
