package api.sandbox.order.model;

import api.sandbox.order.event.OrderEvent;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderItemEvent extends OrderEvent {
    private final Operation operation;
    private final int skuId;

    public OrderItemEvent(OrderId id, OrderState state, Operation operation, int skuId) {
        super(id, state);
        this.operation = operation;
        this.skuId = skuId;
    }

    @Override
    public void merge(Order order) {
        super.merge(order);

        // TODO 좀더 자연스럽게 처리할 수 있는 방법이 있을까?
        if (Operation.ADD.equals(getOperation())) {
            order.addItem(new OrderItem(skuId));
        } else if (Operation.REMOVE.equals(getOperation())) {
            order.removeItem(getSkuId());
        }

    }

    public static enum Operation {ADD, REMOVE}

    public int getSkuId() {
        return skuId;
    }

    public Operation getOperation() {
        return operation;
    }
}
