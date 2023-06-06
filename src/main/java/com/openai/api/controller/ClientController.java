package com.openai.api.controller;


import com.core.bean.ResultEntity;
import com.core.bean.ResultFactory;
import com.core.bean.chatgpt.PromptData;
import com.openai.api.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api")
@Api(tags = "openAI接口")
@Slf4j
@CrossOrigin
public class ClientController {

    @Resource
    BaseService baseService;

    @GetMapping("completions/getMessage")
    public SseEmitter getEvents(@RequestParam String apiKey,@RequestParam(required = false) String groupId) {

        try {
            return baseService.subscribe(apiKey,groupId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("completions")
    @ApiOperation("补全接口")
    ResultEntity<String> completions(@RequestBody PromptData promptData, @RequestParam String apiKey) {
        if(ObjectUtils.isEmpty(apiKey)){
            return ResultFactory.fail();
        }
        return ResultFactory.success(baseService.completions(promptData, apiKey));
    }


    @PostMapping("completions/stream")
    @ApiOperation("实时会话接口")
    void completions(@RequestBody PromptData promptData,@RequestParam String groupId,@RequestParam String apiKey) {
        baseService.completions(promptData, groupId, apiKey);
    }



}
