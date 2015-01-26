package api.sandbox.order.event;

import api.sandbox.order.model.Order;
import api.sandbox.order.model.OrderId;
import api.sandbox.order.model.OrderState;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderEvent {
    private final OrderId id;
    private final OrderState state;

    public OrderEvent(OrderId id, OrderState state) {
        this.id = id;
        this.state = state;
    }

    public OrderId getId() {
        return id;
    }

    public OrderState getState() {
        return state;
    }

    public void merge(Order order) {
        //TODO 구현...
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "id=" + id +
                ", state=" + state +
                '}';
    }
}
