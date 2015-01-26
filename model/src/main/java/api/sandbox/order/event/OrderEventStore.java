package api.sandbox.order.event;

import api.sandbox.order.model.Order;
import api.sandbox.order.model.OrderId;
import api.sandbox.order.model.OrderState;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public interface OrderEventStore {

    void save(OrderEvent event);

    Order find(OrderId id);

    OrderId findId(String memberId, OrderState state);
}
