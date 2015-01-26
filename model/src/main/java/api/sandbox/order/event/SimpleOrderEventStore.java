package api.sandbox.order.event;

import api.sandbox.order.model.Order;
import api.sandbox.order.model.OrderId;
import api.sandbox.order.model.OrderState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class SimpleOrderEventStore implements OrderEventStore {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<OrderId, List<OrderEvent>> mapStore = new HashMap<OrderId, List<OrderEvent>>();

    @Override
    public void save(OrderEvent event) {
        OrderId id = event.getId();
        if (mapStore.containsKey(id)) {
            // 가장 마지막 주문 상태를 바탕으로 유효한 상태인지 체크
            List<OrderEvent> list = mapStore.get(id);
            OrderState.validation(getLastEvent(list), event.getState());
            list.add(event);
        } else {
            LinkedList<OrderEvent> list = new LinkedList<OrderEvent>();
            list.add(event);
            mapStore.put(id, list);
        }
    }

    private OrderEvent getLastEvent(List<OrderEvent> list) {
        return list.get(list.size() - 1);
    }

    @Override
    public Order find(OrderId id) {
        List<OrderEvent> list = mapStore.get(id);
        if (list == null || list.size() < 1) {
            throw new RuntimeException(id + "에 해당하는 주문 정보가 없습니다!");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("주문 Event 조회[" + id + "]");
        }
        Order order = new Order(id);
        for (OrderEvent e : list) {
            if (logger.isDebugEnabled()) {
                logger.debug("Event 처리: " + e);
            }

            OrderState.transition(order, e.getState());
            e.merge(order);
        }
        return order;
    }

    @Override
    public OrderId findId(String memberId, OrderState state) {
        for (OrderId id : this.mapStore.keySet()) {
            if (memberId.equals(id.getMemberId())) {
                for (OrderEvent e : mapStore.get(id)) {
                    if (state.equals(e.getState())) {
                        return id;
                    }
                }
            }
        }
        return null;
    }
}
