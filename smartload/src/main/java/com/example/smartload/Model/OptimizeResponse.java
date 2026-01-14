package com.example.smartload.Model;

import java.util.List;

public class OptimizeResponse {
    private String truck_id;
    private List<String> selected_order_ids;

    private long total_payout_cents;
    private long total_weight_lbs;
    private long total_volume_cuft;

    private double utilization_weight_percent;
    private double utilization_volume_percent;

    public String getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(String truck_id) {
        this.truck_id = truck_id;
    }

    public List<String> getSelected_order_ids() {
        return selected_order_ids;
    }

    public void setSelected_order_ids(List<String> selected_order_ids) {
        this.selected_order_ids = selected_order_ids;
    }

    public long getTotal_payout_cents() {
        return total_payout_cents;
    }

    public void setTotal_payout_cents(long total_payout_cents) {
        this.total_payout_cents = total_payout_cents;
    }

    public long getTotal_weight_lbs() {
        return total_weight_lbs;
    }

    public void setTotal_weight_lbs(long total_weight_lbs) {
        this.total_weight_lbs = total_weight_lbs;
    }

    public long getTotal_volume_cuft() {
        return total_volume_cuft;
    }

    public void setTotal_volume_cuft(long total_volume_cuft) {
        this.total_volume_cuft = total_volume_cuft;
    }

    public double getUtilization_weight_percent() {
        return utilization_weight_percent;
    }

    public void setUtilization_weight_percent(double utilization_weight_percent) {
        this.utilization_weight_percent = utilization_weight_percent;
    }

    public double getUtilization_volume_percent() {
        return utilization_volume_percent;
    }

    public void setUtilization_volume_percent(double utilization_volume_percent) {
        this.utilization_volume_percent = utilization_volume_percent;
    }
}
