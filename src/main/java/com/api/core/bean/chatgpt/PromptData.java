package com.api.core.bean.chatgpt;

import lombok.Data;

import java.util.List;

@Data
public class PromptData {

    private String model = "gpt-3.5-turbo";

    /**
     * 字符串，表示用户输入的文本
     */
    private List<Content> messages;

    /**
     *  max_tokens 是一个整数，表示最多可以返回的令牌数
     */
  //  private Integer max_tokens = 1;

    /**
     *  temperature 是一个浮点数，表示随机性的程度。较低的温度会导致较保守的输出，而较高的温度会导致较大胆的输出。
     */
   // private Integer temperature;

    /**
     * top_p 是一个浮点数，表示从候选输出中选择的最高概率的比例。
     * 顶部概率，表示完成结果的多样性
     */
    //private float top_p;

    /**
     * n 是一个整数，表示要生成的候选输出的数量。
     * 数量，表示要返回的完成结果的数量
     */
/*    private Integer n = 1;*/
    /**
     * `stream`：流，表示要返回的完成结果是否应以流的形式返回。
     */
    private Boolean stream = false;
    /**
     * 每个可能的补全的概率值的对数。
     */
   /* private Object logprobs;
    private String[] stop;*/

}
