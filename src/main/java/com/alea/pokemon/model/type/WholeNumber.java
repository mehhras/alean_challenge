package com.alea.pokemon.model.type;

import jakarta.validation.constraints.NotNull;

import static java.util.Optional.ofNullable;

public class WholeNumber extends Number implements Comparable<WholeNumber>{

    protected Integer value;

    public WholeNumber(@NotNull Integer value) {
        this.value = ofNullable(value)
                .filter(v -> v >= 0)
                .orElseThrow(() -> new IllegalArgumentException("Whole Number out of range"));
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    // implements equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WholeNumber that)) return false;
        return value.equals(that.value);
    }

    // implements hashCode
    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int compareTo(@NotNull WholeNumber o) {
        return value.compareTo(o.intValue());
    }
}

