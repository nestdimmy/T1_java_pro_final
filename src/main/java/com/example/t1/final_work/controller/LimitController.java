package com.example.t1.final_work.controller;

import com.example.t1.final_work.converter.LimitConverter;
import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.model.dto.limit.LimitDto;
import com.example.t1.final_work.model.dto.limit.LimitFetchResult;
import com.example.t1.final_work.model.dto.limit.LimitUpdateRequest;
import com.example.t1.final_work.service.LimitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitController {

    private final LimitService limitService;
    private final LimitConverter limitConverter;

    @GetMapping("/{userId}")
    public LimitDto getLimitByUserId(@PathVariable("userId") String userId) {
        LimitFetchResult limit = limitService.findByUserId(userId);
        return limitConverter.convert(limit);
    }

    @PostMapping("/{userId}/change")
    public LimitDto changeLimit(@PathVariable("userId") String userId, @RequestBody LimitUpdateRequest request) {
        Limit limit = limitService.changeLimit(userId, request);

        return limitConverter.convert(limit);
    }
}
