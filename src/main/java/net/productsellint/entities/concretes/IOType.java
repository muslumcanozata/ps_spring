package net.productsellint.entities.concretes;

public enum IOType {
    OUT(0), IN(1);
    private final Integer ioType;

    IOType(Integer i) {
        this.ioType = i;
    }

    public int getValue() {
        return this.ioType;
    }
}
