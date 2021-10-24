package net.productsellint.entities.concretes;

public enum EntityStatus {
    ACTIVE(0), DELETED(1), DISABLED(2);
    private final Integer value;

    EntityStatus(Integer i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

}
