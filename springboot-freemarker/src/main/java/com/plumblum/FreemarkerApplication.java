package com.plumblum;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * @author chenpeibin
 * @version 1.0
 * @date 2020/12/4 14:40
 */
@Slf4j
public class FreemarkerApplication {
    public static void main(String[] args) throws IOException, TemplateException {
        String sql = "select * from ${tableName} where a = a and b = b \n" +
                "<#if tem??> and d = ${tem}</#if>";
        Template template  = new Template("template",sql,new Configuration());
        Map<String,String> params= new HashMap<>();
        StringWriter result = new StringWriter();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("freemarker");
        int size = 10000;
        for(int i = 0; i<size; i++) {
            params.put("tableName", "table_name");
            template.process(params, result);
            // System.out.println(result);
            // params.put("tem", "12");
            // template.process(params, result);
            // System.out.println(result);
        }
        stopWatch.stop();
        stopWatch.prettyPrint();
        stopWatch.start("common");
        for(int i = 0; i<size; i++) {
            // System.out.println(" ");
            // System.out.println(" ");
        }
        stopWatch.stop();
        stopWatch.prettyPrint();
        log.info("{}", stopWatch.prettyPrint());

    }
}
