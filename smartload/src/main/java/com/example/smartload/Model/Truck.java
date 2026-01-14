package com.example.smartload.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Truck {
    @NotBlank
    private String id;

    @Min(1)
    private long max_weight_lbs;

    @Min(1)
    private long max_volume_cuft;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMax_weight_lbs() {
        return max_weight_lbs;
    }

    public void setMax_weight_lbs(long max_weight_lbs) {
        this.max_weight_lbs = max_weight_lbs;
    }

    public long getMax_volume_cuft() {
        return max_volume_cuft;
    }

    public void setMax_volume_cuft(long max_volume_cuft) {
        this.max_volume_cuft = max_volume_cuft;
    }
}
