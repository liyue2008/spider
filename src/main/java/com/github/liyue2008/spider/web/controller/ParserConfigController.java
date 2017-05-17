package com.github.liyue2008.spider.web.controller;

import com.github.liyue2008.spider.core.entity.ParserConfig;
import com.github.liyue2008.spider.core.service.ConfigService;
import com.github.liyue2008.spider.web.utils.RestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liyue on 2017/4/14.
 */
@Controller
public class ParserConfigController {

    @Resource
    private ConfigService configService;
    @RequestMapping("/parser-config-list.html")
    public String parserConfigList(Model model){

        List<ParserConfig> parserConfigList = configService.getAllParserConfigList();
        model.addAttribute("parserConfigs",parserConfigList);
        model.addAttribute("classActiveParserConfigList","active");
        model.addAttribute("title","解析器配置");

        return "parser-config-list";
    }

    @RequestMapping("/parser-config-create.html")
    public String createParserConfig(Model model){
        List<ParserConfig> parserConfigList = configService.getAllParserConfigList();

        model.addAttribute("isCreate",true);
        model.addAttribute("classActiveParserConfigList","active");
        model.addAttribute("parserConfigs",parserConfigList);
        model.addAttribute("title","新建解析器");

        return "parser-config-edit";

    }
    @RequestMapping("/parser-config-edit.html")
    public String editParserConfig(Model model,@RequestParam Integer id){
        List<ParserConfig> parserConfigList = configService.getAllParserConfigList();
        Iterator<ParserConfig> iterator = parserConfigList.iterator();
        while (iterator.hasNext()){
            ParserConfig parserConfig = iterator.next();
            if(parserConfig.getId().equals(id)){
                iterator.remove();
                break;
            }
        }
        model.addAttribute("isCreate",false);
        ParserConfig config = configService.getParserConfigFullById(id);
        model.addAttribute("parserConfig",config);
        model.addAttribute("classActiveParserConfigList","active");
        model.addAttribute("parserConfigs",parserConfigList);
        model.addAttribute("title","编辑解析器");

        return "parser-config-edit";

    }


    @RequestMapping(value = "/parser-config/save",method = RequestMethod.POST)
    public @ResponseBody Object saveParserConfig(@RequestBody ParserConfig parserConfig){

        Integer id = configService.saveParserConfig(parserConfig);
        Map<String,Object> retMap =  RestUtils.successResponse();
        retMap.put("id",id);
        return retMap;

    }

    @RequestMapping(value = "parser-config/delete/{id:\\d+}",method = RequestMethod.POST)
    public @ResponseBody Object deleteParserConfig(@PathVariable Integer id){
        configService.deleteParserConfig(id);
        return RestUtils.successResponse();
    }


}
