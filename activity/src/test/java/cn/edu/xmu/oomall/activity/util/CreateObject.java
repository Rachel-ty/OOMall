package cn.edu.xmu.oomall.activity.util;
import cn.edu.xmu.oomall.activity.microservice.vo.SimpleSaleInfoVo;
import cn.edu.xmu.oomall.activity.microservice.vo.ShopInfoVo;
import cn.edu.xmu.oomall.core.util.ReturnObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiuchen lang 22920192204222
 * @date 2021/11/13 15:00
 */
public class CreateObject {
    public static ReturnObject createOnSaleInfoDTO(Long id) {
        if(id<=0){
            return new ReturnObject();
        }
        List<SimpleSaleInfoVo> list = new ArrayList<>();
        SimpleSaleInfoVo simpleSaleInfoVO = new SimpleSaleInfoVo();
        simpleSaleInfoVO.setShareActId(1l);
        list.add(simpleSaleInfoVO);
        SimpleSaleInfoVo simpleSaleInfoVo2 = new SimpleSaleInfoVo();
        simpleSaleInfoVo2.setShareActId(2l);
        list.add(simpleSaleInfoVo2);
        SimpleSaleInfoVo simpleSaleInfoVo3 = new SimpleSaleInfoVo();
        simpleSaleInfoVo3.setShareActId(3l);
        list.add(simpleSaleInfoVo3);
        //模拟不是share活动
        SimpleSaleInfoVo simpleSaleInfoVo5 = new SimpleSaleInfoVo();
        list.add(simpleSaleInfoVo5);
        SimpleSaleInfoVo simpleSaleInfoVo4 = new SimpleSaleInfoVo();
        simpleSaleInfoVo4.setShareActId(4l);
        list.add(simpleSaleInfoVo4);
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("total",10);
        return new ReturnObject(map);
    }

    public static ReturnObject<ShopInfoVo> createShopInfoDTO(Long id) {
        if(id<=0){
            return new ReturnObject();
        }
        return new ReturnObject<>(new ShopInfoVo(id,"良耳的商铺"));
    }

}
