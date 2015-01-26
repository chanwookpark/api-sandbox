package api.sandbox.order;

import api.sandbox.order.event.OrderEvent;
import api.sandbox.order.event.OrderEventStore;
import api.sandbox.order.event.SimpleOrderEventStore;
import api.sandbox.order.model.Order;
import api.sandbox.order.model.OrderId;
import api.sandbox.order.model.OrderItemEvent;

import static api.sandbox.order.model.OrderItemEvent.Operation.ADD;
import static api.sandbox.order.model.OrderItemEvent.Operation.REMOVE;
import static api.sandbox.order.model.OrderState.CREATE_CART;
import static api.sandbox.order.model.OrderState.IN_CART;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderApi {
    private OrderEventStore eventStore = new SimpleOrderEventStore();

    public OrderId createCart(String memberId) {
        OrderId id = new OrderId(memberId);
        OrderEvent event = new OrderEvent(id, CREATE_CART); //TODO event와 state를 구분해야 함

        //TODO 현재 고객이 장바구니를 이미 가지고 있다면 다시 생성 안해줘야지..

        eventStore.save(event);
        return id;
    }

    public Order getOrder(OrderId id) {
        Order order = eventStore.find(id);
        return order;
    }

    public void addItem(OrderId id, int skuId) {
        // 한 회원의 주문 상태가 여러 개 있을텐데.. item을 어디에 추가해야 하나?
        // 현재 마지막 상태가? 또는 포함되어 있는 상태가 장바구니 상태가 있는? 걸로? 이상하다..
        // 주문의 상태와 item 추가와 같은 액션을 구분해야 하는거 아닌가?
        // 상태란 무엇인지...
        // 상태와 이벤트는 다른 말이다.. 이벤트를 추가한다고 상태가 변경되는건 아니다!!!! 아!! 그렇구나!!

        // 현재 상태가 장바구니인 주문ID(=주문 이벤트 SET)을 조합
        // 여기에 ITEM 추가 이벤트를 추가한다. 하지만 그렇다고 주문의 상태가 변하는 건 아니다!!
//        OrderId id = eventStore.findId(id, CREATE_CART);

        // TODO item 부가 정보 추가 필요
        OrderItemEvent event = new OrderItemEvent(id, IN_CART, ADD, skuId);
        eventStore.save(event);
    }

    public void removeItem(OrderId id, int skuId) {
        OrderItemEvent event = new OrderItemEvent(id, IN_CART, REMOVE, skuId);
        eventStore.save(event);
    }

    public void increaseItem(OrderId id, int skuId) {
        // 상태는 그대로, item op 유형을 increase로 잡고 이 때 if-else에서 처리하도록  예
    }
}
