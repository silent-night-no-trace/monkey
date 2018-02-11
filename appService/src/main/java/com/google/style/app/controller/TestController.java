package com.google.style.app.controller;

import com.google.model.first.Goods;
import com.google.service.first.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

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
      public void save(@RequestBody Goods goods){
          Integer saveStatus = goodsService.save(goods);
          if(saveStatus!=1){
                log.info("=======save fail ======");
          }else {
              log.info("======= success ======");
          }
      }
}
