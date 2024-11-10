package com.eventsphere.event_sphere.modules.ping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @RequestMapping
    public ResponseEntity<Object> ping() {
        return ResponseEntity.ok().body(new InnerPingController("pong"));
    }

    public record InnerPingController(String ping) {
    }

}
