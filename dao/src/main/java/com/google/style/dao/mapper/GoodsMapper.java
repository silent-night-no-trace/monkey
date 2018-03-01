package com.google.style.dao.mapper;

import com.google.style.model.first.Goods;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

/**
 * @author liangz
 * @date 2018/2/9 18:00
 * goods mapper
 **/
@Mapper
@Repository
public interface GoodsMapper {

    /**
     * goods 保存
     * @param goods goods
     * @return
     */
    @InsertProvider(type = GoodsMapper.SaveSql.class, method = "save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer save(Goods goods);

    class SaveSql {
        public String save(final Goods goods) {
            try {
                return new SQL() {
                    {
                        INSERT_INTO("goods");
                        VALUES("name", "#{name}");
                        VALUES("status", "#{status}");
                        VALUES("num", "#{num}");
                    }
                } .toString();
            } catch (Exception e) {
                return "";
            }
        }
    }

    @Select("SELECT * fROM goods WHERE id = #{id}")
    Goods findGoodsById(Integer id);
}
