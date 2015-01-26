import api.sandbox.order.OrderApi;
import api.sandbox.order.model.Order;
import api.sandbox.order.model.OrderId;
import api.sandbox.order.model.OrderState;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderEventSourceTests {
    private OrderApi orderApi = new OrderApi();

    String memberId = "user001";

    /**
     * 장바구니 생성
     *
     * @throws Exception
     */
    @Test
    public void createCart() throws Exception {
        OrderId id = orderApi.createCart(memberId);

        assertNotNull(id);
        assertTrue(id.getOrderNumber() > 0);
        assertEquals(memberId, id.getMemberId());
    }

    /**
     * 장바구니 생성 후 주문 객체 조회
     *
     * @throws Exception
     */
    @Test
    public void findOrder() throws Exception {
        OrderId id = orderApi.createCart(memberId);

        Order order = orderApi.getOrder(id);
        assertNotNull(order);
        assertEquals(OrderState.CREATE_CART, order.getState());
    }

    /**
     * 장바구니에 주문 아이템 추가
     *
     * @throws Exception
     */
    @Test
    public void addItem() throws Exception {
        int skuId = 100;
        OrderId id = orderApi.createCart(memberId);
        orderApi.addItem(id, skuId);


        Order order = orderApi.getOrder(id);
        assertNotNull(order);
        assertEquals(OrderState.IN_CART, order.getState());

        assertTrue(1 == order.getItemList().size());
        assertTrue(skuId == order.getItemList().get(0).getSkuId());
    }

    /**
     * 장바구니에 아이템 삭제
     *
     * @throws Exception
     */
    @Test
    public void removeItem() throws Exception {
        int skuId = 100;
        OrderId id = orderApi.createCart(memberId);
        orderApi.addItem(id, skuId);

        Order order = orderApi.getOrder(id);
        assertTrue(1 == order.getItemList().size());

        orderApi.removeItem(order.getId(), skuId);

        order = orderApi.getOrder(id);
        assertTrue(0 == order.getItemList().size());
        assertEquals(OrderState.IN_CART, order.getState());
    }

    @Test
    public void increaseOrderedItemCount() throws Exception {
        int skuId = 100;
        OrderId id = orderApi.createCart(memberId);
        orderApi.addItem(id, skuId);

        // 주문한 아이템 갯수 1을 증가: item을 추가하나 더하는 건지 or item의 갯수를 증가하는 이벤트를 추가하는 건지?
        orderApi.increaseItem(id, skuId);

    }

    //TODO order id가 없을 때
    //TODO 동일한 memberid로 만들어진 cart가 있는데 다시 cart 생성 요청이 왔을 때..
}
