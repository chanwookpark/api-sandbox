package api.sandbox.order.model;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderId {
    private final String memberId;
    private final long orderNumber;

    public OrderId(String memberId) {
        this.memberId = memberId;
        this.orderNumber = OrderNumberUtil.create();
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderId)) return false;

        OrderId orderId = (OrderId) o;

        if (orderNumber != orderId.orderNumber) return false;
        if (!memberId.equals(orderId.memberId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + (int) (orderNumber ^ (orderNumber >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OrderId{" +
                "memberId='" + memberId + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
