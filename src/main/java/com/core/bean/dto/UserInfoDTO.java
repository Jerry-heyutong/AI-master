package com.core.bean.dto;

import com.alibaba.fastjson.JSON;
import com.core.bean.ImABean;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author ly
 * @date 2019/7/15 17:16
 * desc: 用户DTO对象
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserInfoDTO implements ImABean {
    private static final long serialVersionUID = 6145930493043915878L;

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String phone;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
