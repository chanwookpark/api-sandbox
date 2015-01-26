package api.sandbox.order.model;

import api.sandbox.order.event.OrderEvent;
import api.sandbox.order.exception.InvalidOrderStateTransitionException;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public enum OrderState {
    NONE, CREATE_CART, IN_CART;

    public static void transition(Order order, OrderState requestState) {
        OrderState currentState = order.getState();

        //TODO 좀 더 나이스하게 처리할 방법은?
        // 아무런 상태가 없을 때는 요청 받은 상태를 바로 사용함
        if (currentState == null || NONE.equals(currentState)) {
            order.setState(requestState);
        } else if (CREATE_CART.equals(currentState) && IN_CART.equals(requestState)) {
            order.setState(requestState);
        }
    }

    public static void validation(OrderEvent currentEvent, OrderState requestState) {
        if (CREATE_CART.equals(currentEvent.getState()) && CREATE_CART.equals(requestState)) {
            throw new InvalidOrderStateTransitionException(currentEvent.getState() + "에서 " +
                    requestState + "로는 상태 변경이 불가능합니다.");
        }
    }
}
