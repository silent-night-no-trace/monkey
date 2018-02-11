package com.google.style.service.first;

import com.google.style.dao.mapper.GoodsMapper;
import com.google.style.model.first.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liangz
 * @date 2018/2/11 9:59
 * good service实现
 **/
@Service
public class GoodsServiceImpl implements  GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Integer save(Goods goods) {
        return goodsMapper.save(goods);
    }
}
