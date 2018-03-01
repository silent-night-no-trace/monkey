package com.google.style.service.first;

import com.google.style.model.first.Goods;
import org.springframework.stereotype.Component;

/**
 * @author liangz
 * @date 2018/2/11 9:57
 **/
@Component
public interface GoodsService {
    /**
     * save
     * @param goods
     * @return
     */
    Integer save(Goods goods);

    /**
     *  select
     * @param id
     * @return
     */
    Goods findGoodsById(Integer id);

}
