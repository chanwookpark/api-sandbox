package api.sandbox.order.model;

/**
 * Created by chanwook on 2015. 1. 13..
 */
public class OrderItem {
    private final int skuId;

    public OrderItem(int skuId) {
        this.skuId = skuId;
    }

    public int getSkuId() {
        return skuId;
    }
}
