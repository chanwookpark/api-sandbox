package api.sandbox.order.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class Order {
    private final OrderId id;
    private OrderState state;
    private List<OrderItem> itemList = new ArrayList<OrderItem>();

    public Order(OrderId id) {
        this.id = id;
        this.state = OrderState.NONE;
    }

    public OrderId getId() {
        return id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void addItem(OrderItem item) {
        this.itemList.add(item);
    }

    public void removeItem(int skuId) {
        for (int i = 0; i < itemList.size(); i++) {
            if (skuId == itemList.get(i).getSkuId()) {
                itemList.remove(i);
                return;
            }
        }
    }
}
