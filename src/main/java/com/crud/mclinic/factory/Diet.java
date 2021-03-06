package com.crud.mclinic.factory;

public final class Diet implements Treatment {

    final String description;
    final double price;

    public Diet(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public String prescribe(String description) {
        return description;

    }

    @Override
    public double getPaid() {
        return price;
    }
}
