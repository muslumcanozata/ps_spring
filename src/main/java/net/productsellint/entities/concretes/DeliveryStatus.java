package net.productsellint.entities.concretes;

public enum DeliveryStatus {
    INPROCESS(0), ONTHEWAY(1), DELIVERED(2);
    private final Integer value;

    DeliveryStatus(Integer i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
