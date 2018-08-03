package com.divinedragon.jackson.controller;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacksonController {

    @GetMapping(path = "/get")
    public Map<String, OffsetDateTime> getDates() {
        return Collections.singletonMap("createdAt", OffsetDateTime.now());
    }

    @PostMapping(path = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, OffsetDateTime> postDates(@RequestParam("created_at") final OffsetDateTime createdAt) {
        return Collections.singletonMap("createdAt", createdAt);
    }

    @PutMapping(path = "/put", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, OffsetDateTime> putDates(@RequestBody final Map<String, OffsetDateTime> putObject) {
        return putObject;
    }
}
