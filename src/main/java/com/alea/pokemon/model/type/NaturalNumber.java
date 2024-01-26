package com.alea.pokemon.model.type;

import jakarta.validation.constraints.NotNull;

import static java.util.Optional.ofNullable;

public class NaturalNumber extends Number implements Comparable<NaturalNumber> {

    private final Integer value;

    public NaturalNumber(@NotNull String value) {
        this(Integer.valueOf(value));
    }

    public NaturalNumber(@NotNull Integer value) {
        this.value = ofNullable(value)
                .filter(v -> v > 0)
                .orElseThrow(() -> new IllegalArgumentException("Natural number must be positive"));
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
        if (!(o instanceof NaturalNumber that)) return false;
        return value.equals(that.value);
    }

    // implements hashCode
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    // implements CompareTo
    @Override
    public int compareTo(NaturalNumber o) {
        return value.compareTo(o.intValue());
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
