/*
package com.algorithm.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import com.jacob.activeX.ActiveXComponent;
        import com.jacob.com.Dispatch;
        import com.jacob.com.Variant;

        import java.io.BufferedReader;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
//        readStr("你好啊！");

        textToSpeechIO("12e3456789");

        textToStr("D:/测试.txt");
    }

    */
/**
     * 字符串文本阅读
     * @param str 要读的文字字符串
     *//*

    public static void readStr(String str){
        ActiveXComponent ax = new ActiveXComponent("Sapi.SpVoice");
        //运行时输出语音内容
        Dispatch spVoice = ax.getObject();
        //设置音量 0 ~ 100
        ax.setProperty("Volume",new Variant(100));
        //设置朗读速度 -10 ~ +10
        ax.setProperty("Rate",new Variant(0));
        //执行朗读
        Dispatch.call(spVoice,"Speak",new Variant(str));
    }

    */
/**
     * 字符串文本转 wav格式 语音文件
     * @param text 要读的文字字符串
     *//*

    public static void textToSpeechIO(String text){
        ActiveXComponent ax = null;
        Dispatch spFileStream = null;
        Dispatch spAudioFormat = null;
        Dispatch spVoice = null;
        try{
            ax = new ActiveXComponent("Sapi.SpFileStream");
            spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            spAudioFormat = ax.getObject();

            spVoice = new ActiveXComponent("Sapi.SpVoice").getObject();
            // 设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            // 设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            // 调用输出 文件流打开方法，创建一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant("D:/voice.wav"), new Variant(3), new Variant(true));
            // 设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            // 设置音量  0 ~ 100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            // 设置朗读速度  -10 ~ +10
            Dispatch.put(spVoice, "Rate", new Variant(0));

            Dispatch.call(spVoice, "Speak", new Variant(text));

            System.out.println("输出语音文件成功！");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 关闭输出文件
            Dispatch.call(Objects.requireNonNull(spFileStream), "Close");
            Dispatch.putRef(Objects.requireNonNull(spVoice), "AudioOutputStream", null);

            Objects.requireNonNull(spAudioFormat).safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();
        }
    }

    */
/**
     * txt文件转字符串
     * @param fileName txt文件所在位置
     * @return txt文件中的字符串
     *//*

    public static String textToStr(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null){
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }finally {
            try {
                Objects.requireNonNull(reader).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
*/
