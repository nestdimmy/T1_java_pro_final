package com.example.t1.final_work.converter;

import com.example.t1.final_work.model.Limit;
import com.example.t1.final_work.model.dto.limit.LimitDto;
import com.example.t1.final_work.model.dto.limit.LimitFetchResult;
import org.springframework.stereotype.Component;

@Component
public class LimitConverter {

    public LimitDto convert(Limit limit) {
        LimitDto result = new LimitDto();
        result.setCurrent(limit.getCurrent());
        result.setUserId(limit.getUserId());
        return result;
    }

    public LimitDto convert(LimitFetchResult limit) {
        LimitDto result = new LimitDto();
        result.setCurrent(limit.getAmount());
        result.setUserId(limit.getUserId());
        return result;
    }
}
