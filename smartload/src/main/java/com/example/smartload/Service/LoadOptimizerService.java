package com.example.smartload.Service;

import com.example.smartload.Model.OptimizeRequest;
import com.example.smartload.Model.OptimizeResponse;
import com.example.smartload.Model.Order;
import com.example.smartload.Model.Truck;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadOptimizerService
{
    public OptimizeResponse optimize(OptimizeRequest request) {

        Truck truck = request.getTruck();
        List<Order> orders = request.getOrders();

        if (orders.isEmpty()) {
            return emptyResponse(truck.getId());
        }

        // ---- Filter by route (same origin & destination) ----
        Order base = orders.get(0);
        orders = orders.stream()
                .filter(o -> o.getOrigin().equals(base.getOrigin())
                        && o.getDestination().equals(base.getDestination()))
                .collect(Collectors.toList());

        int n = orders.size();
        if (n == 0) {
            return emptyResponse(truck.getId());
        }

        long maxWeight = truck.getMax_weight_lbs();
        long maxVolume = truck.getMax_volume_cuft();

        long bestPayout = 0;
        int bestMask = 0;

        for (int mask = 1; mask < (1 << n); mask++) {

            long weight = 0, volume = 0, payout = 0;
            boolean hasHazmat = false, hasNonHazmat = false;

            LocalDate latestPickup = LocalDate.MIN;
            LocalDate earliestDelivery = LocalDate.MAX;

            boolean invalid = false;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    Order o = orders.get(i);
                    weight += o.getWeight_lbs();
                    volume += o.getVolume_cuft();
                    payout += o.getPayout_cents();

                    if (o.isHazmat()) hasHazmat = true;
                    else hasNonHazmat = true;

                    latestPickup = latestPickup.isAfter(o.getPickup_date())
                            ? latestPickup : o.getPickup_date();
                    earliestDelivery = earliestDelivery.isBefore(o.getDelivery_date())
                            ? earliestDelivery : o.getDelivery_date();

                    if (weight > maxWeight || volume > maxVolume) {
                        invalid = true;
                        break;
                    }
                    if (hasHazmat && hasNonHazmat) {
                        invalid = true;
                        break;
                    }
                }
            }

            if (invalid) continue;
            if (latestPickup.isAfter(earliestDelivery)) continue;

            if (payout > bestPayout) {
                bestPayout = payout;
                bestMask = mask;
            }
        }

        return buildResponse(truck, orders, bestMask);
    }
    private OptimizeResponse buildResponse(Truck truck, List<Order> orders, int mask) {
        OptimizeResponse res = new OptimizeResponse();
        res.setTruck_id(truck.getId());

        List<String> ids = new ArrayList<>();
        long weight = 0, volume = 0, payout = 0;

        for (int i = 0; i < orders.size(); i++) {
            if ((mask & (1 << i)) != 0) {
                Order o = orders.get(i);
                ids.add(o.getId());
                weight += o.getWeight_lbs();
                volume += o.getVolume_cuft();
                payout += o.getPayout_cents();
            }
        }
        res.setSelected_order_ids(ids);
        res.setTotal_weight_lbs(weight);
        res.setTotal_volume_cuft(volume);
        res.setTotal_payout_cents(payout);

        res.setUtilization_weight_percent(
                round((weight * 100.0) / truck.getMax_weight_lbs()));
        res.setUtilization_volume_percent(
                round((volume * 100.0) / truck.getMax_volume_cuft()));

        return res;
    }
    private OptimizeResponse emptyResponse(String truckId) {
        OptimizeResponse r = new OptimizeResponse();
        r.setTruck_id(truckId);
        r.setSelected_order_ids(Collections.emptyList());
        return r;
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
