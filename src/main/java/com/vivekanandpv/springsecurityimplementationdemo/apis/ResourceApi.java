package com.vivekanandpv.springsecurityimplementationdemo.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/resource")
public class ResourceApi {
    @GetMapping("authenticated-route")
    public ResponseEntity<Map<String, String>> getAuthenticatedRoute() {
        return ResponseEntity.ok(Map.of("message", "authenticated route ok"));
    }

    @GetMapping("authorized-route")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<Map<String, String>> getAuthorizedRoute() {
        return ResponseEntity.ok(Map.of("message", "authorized route ok"));
    }
}
