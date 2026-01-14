package com.example.smartload.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class Order {
    @NotBlank
    private String id;

    @Min(1)
    private long payout_cents;

    @Min(1)
    private long weight_lbs;

    @Min(1)
    private long volume_cuft;

    @NotBlank
    private String origin;
    @NotBlank
    private String destination;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickup_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate delivery_date;

    private boolean hazmat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPayout_cents() {
        return payout_cents;
    }

    public void setPayout_cents(long payout_cents) {
        this.payout_cents = payout_cents;
    }

    public long getWeight_lbs() {
        return weight_lbs;
    }

    public void setWeight_lbs(long weight_lbs) {
        this.weight_lbs = weight_lbs;
    }

    public long getVolume_cuft() {
        return volume_cuft;
    }

    public void setVolume_cuft(long volume_cuft) {
        this.volume_cuft = volume_cuft;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(LocalDate pickup_date) {
        this.pickup_date = pickup_date;
    }

    public LocalDate getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(LocalDate delivery_date) {
        this.delivery_date = delivery_date;
    }

    public boolean isHazmat() {
        return hazmat;
    }

    public void setHazmat(boolean hazmat) {
        this.hazmat = hazmat;
    }
}
