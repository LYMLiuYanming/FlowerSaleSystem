package com.flowers.example.response;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Data
public class ResultJson<T> {
    private String code;
    private String msg;
    private T data;

    public static ResultJson success(List<Map<String, Integer>> maps) {
        ResultJson result = new ResultJson<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
}

