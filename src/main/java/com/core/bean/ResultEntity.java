package com.core.bean;

import com.alibaba.fastjson.JSON;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author ly
 * @date 2019/5/16 15:44
 * description:
 * 返回实体类，其中data数据主要由DTO和AO构成:
 * DTO: 数据传输对象，Service或Manager向外传输的对象。
 * AO: 应用对象，在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高
 *
 * 用于微服务和Web通信，区分有返回值和无返回值，下面只讨论返回值情况：
 * 1. web通信，带返回值，无需关心反序列化问题，使用AO
 * 2. 微服务通信，带返回值，T必须使用DTO，用于RPC返回值反序列化
 */
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ResultEntity<T> implements ImABean{

    private static final long serialVersionUID = -587728047893738620L;
    private Boolean flag;
    private int code;
    private T data;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
