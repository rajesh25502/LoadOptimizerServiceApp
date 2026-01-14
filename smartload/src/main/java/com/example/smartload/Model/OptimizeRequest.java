package com.example.smartload.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OptimizeRequest {
    @NotNull
    @Valid
    private Truck truck;

    @NotNull
    @Valid
    private List<Order> orders;

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
