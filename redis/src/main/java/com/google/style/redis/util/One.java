package com.google.style.redis.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangz
 * @date 2018/4/3 18:11
 * redis template 测试
 *
 **/
@Slf4j
@RestController
public class One {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @RequestMapping("/sa/{id}")
    public String  sa(@PathVariable("id") Integer id){
        //键默认规则 good+id
        String s = redisTemplate.opsForValue().get("good_" + id);
        Good cacheGoods = null;
        if(s!=null){
             cacheGoods = JSONObject.parseObject(s, Good.class);
            if(cacheGoods==null){
                return readAndPut(id);
            }else{
                log.info("缓存读取 ---------");
                return cacheGoods.toString();
            }
        }else{
            return readAndPut(id);
        }
    }


    public String readAndPut(Integer id){
        Good good = new Good();
        good.setId(1);
        good.setGoodName("iphone16x");
        good.setPrice(18000);
        good.setCount(1);
        String jsonString = JSONObject.toJSONString(good);
        redisTemplate.opsForValue().set("good_"+id,jsonString);
        return  good.toString();
    }

    @ToString
    @Data
    static class Good{
        private Integer id;
        private String goodName;
        private Integer price;
        private Integer count;

    }
}
