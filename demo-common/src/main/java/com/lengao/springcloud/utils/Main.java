package com.lengao.springcloud.utils;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * <p>description goes here</p>
 *
 * @author 冷澳
 * @date 2022/12/2
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        String add = Main.callJsFunction("encode", new Object[]{"alert(1)",true,true}, "D:\\有灵魂的Java开发工程师进阶之路\\springboot-demo\\src\\main\\java\\com\\lengao\\utils\\jsUtil.js");
        System.out.println(add);
    }
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * 调用js方法
     *
     *
     * @param functionName js函数名
     * @param params       参数
     * @param jsPath       js真实路径
     * @return 数据
     */
    public static String callJsFunction(String functionName, Object[] params, String jsPath) {
        Object res = null;
        try {
            File file = new File(jsPath);
            String jsStr = readFile(file);
            String[] options = new String[]{"--language=es6"};
            NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
            NashornScriptEngine engine = (NashornScriptEngine) factory.getScriptEngine(options);
            // 加载js
            engine.eval(jsStr);
            // 调用
            res = engine.invokeFunction(functionName, params);
        } catch (Exception e) {
            LOGGER.error("java调用js代码出错：", e);
        }
        if (res == null) {
            return "";
        }
        return res.toString();
    }

    /**
     * 读取文件、读取文本
     *
     * @param file 文件位置
     * @return 数据
     */
    public static String readFile(File file) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            LOGGER.error("读取文件失败：", e);
        }
        return result.toString();
    }
}
