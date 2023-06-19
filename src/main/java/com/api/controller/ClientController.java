package com.api.controller;


import com.api.core.bean.ResultEntity;
import com.api.core.bean.ResultFactory;
import com.api.core.bean.chatgpt.Content;
import com.api.core.bean.chatgpt.Group;
import com.api.core.bean.chatgpt.PromptData;
import com.api.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/v1/api")
@Api(tags = "openAI接口")
@Slf4j
@CrossOrigin
@Valid
public class ClientController {

    @Resource
    BaseService baseService;

    @GetMapping("completions/getMessage")
    public SseEmitter getEvents(@RequestParam @NotNull String apiKey, @RequestParam @NotNull String groupId) {
        try {
            if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(groupId)) {
                return null;
            }
            return baseService.createEmitter(apiKey, groupId);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("completions/stream")
    @ApiOperation("实时会话接口")
    void completions(@RequestBody Content content, @RequestParam @NotNull String groupId, @RequestParam @NotNull String apiKey) {
        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(groupId)) {
            return;
        }
        ConcurrentHashMap<String, Group> groupMap = baseService.sessionMap.get(apiKey);
        if (groupMap != null) {
            Group group = groupMap.get(groupId);
            if (group != null) {
                PromptData promptData = group.getPromptData();
                List<Content> messages = promptData.getMessages();
                if (messages != null) {
                    messages.add(content);
                    promptData.setMessages(messages);
                    promptData.setStream(true);
                    baseService.completions(promptData, groupId, apiKey);
                }
            }
        }
    }

    @PostMapping("completions")
    @ApiOperation("补全接口")
    ResultEntity<String> completions(@RequestBody PromptData promptData, @RequestParam String apiKey) {
        if (ObjectUtils.isEmpty(apiKey)) {
            return ResultFactory.fail();
        }
        return ResultFactory.success(baseService.completions(promptData, apiKey));
    }

}
