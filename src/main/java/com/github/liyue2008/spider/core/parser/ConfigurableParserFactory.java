package com.github.liyue2008.spider.core.parser;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * Created by liyue on 2017/5/8.
 */
@Component
public class ConfigurableParserFactory {
    @Lookup
    public ConfigurableParser getConfigurableParser(){
        return null;
    }
}
