package com.tang.generator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;


@Slf4j
public class Generator {

    public static InputStream getResourceAsStream(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(getResourceAsStream("generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        log.info("代码-----》生成成功");
        for (String warning : warnings) {
            log.info(warning);
        }
    }
}
