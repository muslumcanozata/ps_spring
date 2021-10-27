package net.productsellint.entities.concretes;

public enum Gender {
    MALE(0), FEMALE(1), OTHER(2), NOTSPECIFIED(2);
    private final Integer gender;

    Gender(Integer i) {
        this.gender = i;
    }

    public int getValue() {
        return this.gender;
    }
}
