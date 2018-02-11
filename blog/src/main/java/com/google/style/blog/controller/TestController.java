package com.google.style.blog.controller;

import com.google.style.service.first.GoodsService;
import com.google.style.model.first.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangz
 * @date 2018/1/26 17:05
 *  For test
 **/

@Slf4j
@RestController
public class TestController {


      @Autowired
      private GoodsService goodsService;

      @RequestMapping("/save")
      public String  save(@RequestBody Goods goods){
          Integer saveStatus = goodsService.save(goods);
          if(saveStatus!=1){
                log.info("=======save fail ======");
                return "fail";
          }else {
              log.info("======= success ======");
              return "success";
          }
      }
}
