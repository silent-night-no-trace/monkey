package com.google.style.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.style.charts.Legend;
import com.google.style.charts.Serie;
import com.google.style.charts.Title;
import com.google.style.charts.XAxis;

import java.util.List;

/**
 * ECharts 折线工具类（多条，单条）
 * @author liangz
 * @date 2018/3/22 12:00
 **/
public class EChartsJsonUtil {

    public static String getJsonData(Title title, Legend legend, XAxis xAxis, List<Serie> series){
        //echarts option 封装
        JSONObject option = new JSONObject();

        //标题内容
        JSONObject jTitle = new JSONObject();
        jTitle.put("text","tools");
        jTitle.put("subtext",title.getSubtext());
        option.put("title",jTitle);
        //提示
        JSONObject jTooltip = new JSONObject();
        jTooltip.put("trigger","axis");
        option.put("tooltip",jTooltip);

        JSONObject jLegend = new JSONObject();
        jLegend.put("data",legend.getData());
        option.put("legend",jLegend);

        JSONObject jGrid = new JSONObject();
        jGrid.put("left","3%");
        jGrid.put("right","4%");
        jGrid.put("bottom","3%");
        jGrid.put("containLabel",true);
        option.put("grid",jGrid);

        //工具栏
        JSONObject toolbox = new JSONObject();
        JSONObject feature = new JSONObject();
        feature.put("saveAsImage",new JSONObject());
        toolbox.put("feature",feature);
        option.put("toolbox",toolbox);

        //X轴数据
        JSONObject jXAxis = new JSONObject();
        jXAxis.put("type",xAxis.getType());
        jXAxis.put("boundaryGap",xAxis.getBoundaryGap());
        jXAxis.put("data",xAxis.getData());
        option.put("xAxis",jXAxis);

        //y轴数据
        JSONObject yAxis = new JSONObject();
        yAxis.put("type","value");
        option.put("yAxis",yAxis);

        //循环添加数据
        JSONArray jSeries = new JSONArray();
        for (Serie serie : series){
            JSONObject jo = new JSONObject();
            jo.put("name",serie.getName());
            jo.put("type",serie.getType());
            jo.put("stack",serie.getStack());
            jo.put("data",serie.getData());
            jSeries.add(jo);
        }
        option.put("series",jSeries);
        return  option.toJSONString();

    }
}
