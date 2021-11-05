package net.productsellint.entities.concretes;

import net.productsellint.exception.custom.EntityStatusNotFoundException;

import java.util.Optional;

public enum EntityStatus {
    ACTIVE(0), DELETED(1), DISABLED(2);
    private final Integer value;

    EntityStatus(Integer i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static EntityStatus getStatus(Integer value) {
        if (value == 0) {
            return EntityStatus.ACTIVE;
        }
        else if(value == 1) {
            return EntityStatus.DELETED;
        }
        else if(value == 2) {
            return EntityStatus.DISABLED;
        }
        else {
             throw new EntityStatusNotFoundException(value);
        }
    }
}
