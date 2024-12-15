package org.trendwa.eshop.aggregates.orderaggregate;

public enum OrderStatus
{
    SUBMITTED,
    AWAITING_VALIDATION,
    STOCK_CONFIRMED,
    PAID,
    SHIPPED,
    CANCELLED
}