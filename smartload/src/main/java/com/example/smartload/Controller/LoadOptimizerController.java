package com.example.smartload.Controller;

import com.example.smartload.Model.OptimizeRequest;
import com.example.smartload.Model.OptimizeResponse;
import com.example.smartload.Service.LoadOptimizerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/load-optimizer")
public class LoadOptimizerController {
    private final LoadOptimizerService service;

    public LoadOptimizerController(LoadOptimizerService service) {
        this.service = service;
    }

    @PostMapping("/optimize")
    public ResponseEntity<OptimizeResponse> optimize(
            @Valid @RequestBody OptimizeRequest request) {
        return ResponseEntity.ok(service.optimize(request));
    }
}
