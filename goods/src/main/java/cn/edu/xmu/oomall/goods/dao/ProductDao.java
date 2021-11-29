package cn.edu.xmu.oomall.goods.dao;

import cn.edu.xmu.oomall.core.model.VoObject;
import cn.edu.xmu.oomall.core.util.Common;
import cn.edu.xmu.oomall.core.util.ReturnNo;
import cn.edu.xmu.oomall.core.util.ReturnObject;
import cn.edu.xmu.oomall.goods.mapper.ProductDraftPoMapper;
import cn.edu.xmu.oomall.goods.model.bo.Goods;
import cn.edu.xmu.privilegegateway.util.RedisUtil;
import cn.edu.xmu.oomall.goods.mapper.ProductPoMapper;
import cn.edu.xmu.oomall.goods.model.bo.Product;
import cn.edu.xmu.oomall.goods.model.po.ProductDraftPo;
import cn.edu.xmu.oomall.goods.model.po.ProductPo;
import cn.edu.xmu.oomall.goods.model.po.ProductPoExample;
import cn.edu.xmu.oomall.goods.model.vo.ProductVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static cn.edu.xmu.oomall.core.util.Common.cloneVo;

/**
 * @author yujie lin
 * @date 2021/11/11
 */
/**
 * @author 黄添悦
 * @date 2021/11/25
 **/
/**
 * @author 王文飞
 * @date 2021/11/25
 */
@Repository
public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    @Autowired
    private ProductPoMapper productPoMapper;
    @Autowired
    private ProductDraftPoMapper productDraftPoMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${oomall.goods.product.expiretime}")
    private long productTimeout;
    public final static String GOODSKEY="goods_%d";
    public boolean hasExist(Long productId) {
        return null != productPoMapper.selectByPrimaryKey(productId);
    }
    public boolean matchProductShop(Long productId, Long shopId) {

        ProductPo productPo=productPoMapper.selectByPrimaryKey(productId);
        return shopId.equals(productPo.getShopId());
    }
    public Long getShopIdById(Long id){
        try{
            Product ret=(Product) redisUtil.get("p_"+id);
            if(null!=ret){
                return ret.getId();
            }

            ProductPo po= productPoMapper.selectByPrimaryKey(id);

            if(po == null) {
                return null;
            }
            Product pro=(Product)cloneVo(po,Product.class);
            redisUtil.set("p_"+pro.getId(),pro,productTimeout);

            return pro.getId();
        }
        catch(Exception e){
            return null;
        }


    }
    /**
     * @author 黄添悦
     * @date 2021/11/25
     **/
    /**
     * @author 王文飞
     * @date 2021/11/25
     */
    public ReturnObject listProductsByFreightId(Long shopId, Long fid, Integer pageNumber, Integer pageSize)
    {
        try {
            PageHelper.startPage(pageNumber, pageSize, true, true, true);
            if (shopId != 0) {
                return new ReturnObject<Product>(ReturnNo.RESOURCE_ID_OUTSCOPE, "此商铺没有发布货品的权限");
            }
            ProductPoExample productPoExample = new ProductPoExample();
            ProductPoExample.Criteria cr = productPoExample.createCriteria();
            cr.andFreightIdEqualTo(fid);
            List<ProductPo> products = productPoMapper.selectByExample(productPoExample);
            List<ProductVo> productList = new ArrayList<>(products.size());
            for (ProductPo productPo : products) {
                ProductVo productVo = (ProductVo) cloneVo(productPo, ProductVo.class);
                productList.add(productVo);
            }
            PageInfo<VoObject> pageInfo = new PageInfo(productList);
            return new ReturnObject<>(pageInfo);
        }catch(Exception e)
        {
            logger.error(e.getMessage());
            return new ReturnObject(ReturnNo.INTERNAL_SERVER_ERR,e.getMessage());
        }

    }
    public ReturnObject alterProductStates(Product product,Byte targetState,Byte... states){
        try{
            ProductPo productPo=new ProductPo();
            productPo.setId(product.getId());
            productPo.setState(product.getState());
            boolean ifValid=false;
            for(Byte state:states){
                if(productPo.getState().equals(state)) {
                    ifValid=true;
                }
            }if(ifValid){
                productPo.setState(targetState);
                productPoMapper.updateByPrimaryKeySelective(productPo);
                return new ReturnObject(productPo);
            }else{
                return new ReturnObject(ReturnNo.STATENOTALLOW,"当前货品状态不支持进行该操作");
            }
        }catch(Exception e){
            return new ReturnObject(ReturnNo.INTERNAL_SERVER_ERR,e.getMessage());
        }
    }
    /**
     * @author 黄添悦
     * @date 2021/11/25
     **/
    /**
     * @author 王文飞
     * @date 2021/11/25
     */
    public ReturnObject<Product> publishById(Long id) {
        try {
            ProductDraftPo productDraftPo = productDraftPoMapper.selectByPrimaryKey(id);
            ProductPo productPo = (ProductPo) cloneVo(productDraftPo, ProductPo.class);
            if (productDraftPo.getProductId() == 0) {
                productPo.setId(null);
                productPoMapper.insert(productPo);
            } else {
                productPo.setId(productDraftPo.getProductId());
                productPoMapper.updateByPrimaryKey(productPo);
            }
            String key = String.format(GOODSKEY, productPo.getGoodsId());
            Goods goods = (Goods) redisUtil.get(key);
            if (goods != null) {
                redisUtil.del(key);
            }
            productDraftPoMapper.deleteByPrimaryKey(id);
            return new ReturnObject<Product>((Product) cloneVo(productPo, Product.class));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ReturnObject(ReturnNo.INTERNAL_SERVER_ERR, e.getMessage());
        }
    }
    public List<Product> getProductsByGoodsId(Long id){
        ProductPoExample productPoExample = new ProductPoExample();
        ProductPoExample.Criteria cr = productPoExample.createCriteria();
        cr.andGoodsIdEqualTo(id);
        List<ProductPo> products = productPoMapper.selectByExample(productPoExample);
        List<Product> productList = new ArrayList<>(products.size());
        for (ProductPo productPo : products) {
            productList.add((Product) cloneVo(productPo, Product.class));
        }
        return productList;
    }
    public int resetGoodsIdForProducts(Long id,Long newId){
        ProductPoExample productPoExample = new ProductPoExample();
        ProductPoExample.Criteria cr = productPoExample.createCriteria();
        cr.andGoodsIdEqualTo(id);
        ProductPo productPo=new ProductPo();
        productPo.setGoodsId(newId);
        return productPoMapper.updateByExampleSelective(productPo,productPoExample);
    }
    public Product getProduct(Long id){
        ProductPo productPo=productPoMapper.selectByPrimaryKey(id);
        return (Product) Common.cloneVo(productPo,Product.class);
    }
    public ProductDraftPo getProductDraft(Long id){
        return productDraftPoMapper.selectByPrimaryKey(id);
    }
}
