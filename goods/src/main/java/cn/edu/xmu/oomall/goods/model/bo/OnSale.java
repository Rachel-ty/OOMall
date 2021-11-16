package cn.edu.xmu.oomall.goods.model.bo;

import cn.edu.xmu.oomall.goods.model.po.OnSalePo;
import cn.edu.xmu.oomall.goods.model.po.ProductDraftPo;
import cn.edu.xmu.oomall.goods.model.po.ProductPo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Huang Tianyue
 * 2021.11.15
 **/
@Data
@NoArgsConstructor
public class OnSale implements Serializable {
    private OnSalePo onSalePo;
    public Long getId(){return onSalePo.getId();}
    public String getName(){return onSalePo.getCreateName();}
    public void setState(Byte state)
    {
        this.onSalePo.setState(state);
    }
    public OnSale(ProductPo productPo)
    {
        onSalePo.setProductId(productPo.getId());
    }
    public OnSalePo getOnSalePo(){return onSalePo;}

}
