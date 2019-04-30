package com.woniu.yoga.pay.UnionpayConfig.load;

import com.woniu.yoga.pay.UnionpayConfig.sdk.SDKConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AutoLoadRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        SDKConfig.getConfig().loadPropertiesFromSrc();
        System.out.println("================加载配置文件");
    }
}
