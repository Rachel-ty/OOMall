package cn.edu.xmu.oomall.freight.controller;

import cn.edu.xmu.privilegegateway.annotation.util.JacksonUtil;
import cn.edu.xmu.privilegegateway.annotation.util.JwtHelper;
import cn.edu.xmu.privilegegateway.annotation.util.RedisUtil;
import cn.edu.xmu.oomall.freight.FreightApplication;
import cn.edu.xmu.oomall.freight.model.vo.RegionVo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author ziyi guo
 * @date 2021/11/11
 */
@SpringBootTest(classes = FreightApplication.class)
@AutoConfigureMockMvc
@Transactional
public class RegionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RedisUtil redisUtil;

    private static JwtHelper jwtHelper = new JwtHelper();
    private static String adminToken = jwtHelper.createToken(1L,"admin",0L,1,3600);

    @Test
    public void getParentRegionTest() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/region/4191/ancestor"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":[{\"id\":1,\"pid\":0,\"name\":\"中国\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":14,\"pid\":1,\"name\":\"福建省\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":151,\"pid\":14,\"name\":\"厦门市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}}],\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getParentRegionTest1() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/region/2/ancestor"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":[{\"id\":1,\"pid\":0,\"name\":\"中国\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}}],\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getParentRegionTest2() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/region/0/ancestor"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void addRegionTest() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(post("/shops/0/regions/1599/subregions").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void addRegionTest1() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(post("/shops/0/regions/4191/subregions").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":995,\"errmsg\":\"地区已废弃\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void addRegionTest2() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(post("/shops/1/regions/4191/subregions").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void addRegionTest3() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(post("/shops/0/regions/0/subregions").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void adminGetChildRegionTest() throws Exception {
        String responseString = this.mvc.perform(get("/shops/0/regions/14/subregions").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":[{\"id\":150,\"pid\":14,\"name\":\"福州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":151,\"pid\":14,\"name\":\"厦门市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":152,\"pid\":14,\"name\":\"莆田市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":153,\"pid\":14,\"name\":\"三明市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":154,\"pid\":14,\"name\":\"泉州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":155,\"pid\":14,\"name\":\"漳州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":156,\"pid\":14,\"name\":\"南平市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":157,\"pid\":14,\"name\":\"龙岩市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":158,\"pid\":14,\"name\":\"宁德市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}}],\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void adminGetChildRegionTest1() throws Exception {
        String responseString = this.mvc.perform(get("/shops/0/regions/0/subregions").header("authorization", adminToken).contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void adminGetChildRegionTest2() throws Exception {
        String responseString = this.mvc.perform(get("/shops/1/regions/14/subregions").header("authorization", adminToken))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getChildRegionTest() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/regions/14/subregions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"data\":[{\"id\":150,\"pid\":14,\"name\":\"福州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":151,\"pid\":14,\"name\":\"厦门市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":152,\"pid\":14,\"name\":\"莆田市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":153,\"pid\":14,\"name\":\"三明市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":154,\"pid\":14,\"name\":\"泉州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":155,\"pid\":14,\"name\":\"漳州市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":156,\"pid\":14,\"name\":\"南平市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":157,\"pid\":14,\"name\":\"龙岩市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}},{\"id\":158,\"pid\":14,\"name\":\"宁德市\",\"state\":0,\"gmtCreate\":\"2020-12-15T13:29:49\",\"gmtModified\":null,\"creator\":{\"id\":1,\"name\":\"admin\"},\"modifier\":{\"id\":null,\"name\":null}}],\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getChildRegionTest1() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/regions/0/subregions").contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void getChildRegionTest2() throws Exception {
        Mockito.when(redisUtil.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(redisUtil.set(Mockito.anyString(),Mockito.any(),Mockito.anyLong())).thenReturn(true);
        String responseString = this.mvc.perform(get("/regions/4191/subregions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":995,\"errmsg\":\"地区已废弃\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }


    @Test
    public void modifyRegionTest() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/0/regions/4191").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();
        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void modifyRegionTest1() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/1/regions/4191").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void modifyRegionTest2() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/0/regions/0").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test   //标识此方法为测试方法
    public void abandonRegionTest() throws Exception {
        String responseString = this.mvc.perform(put("/shops/0/regions/2/suspend").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

        responseString = this.mvc.perform(delete("/shops/0/regions/2").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
    }

    @Test   //标识此方法为测试方法
    public void abandonRegionTest1() throws Exception {
        String responseString = this.mvc.perform(delete("/shops/0/regions/3").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":507,\"errmsg\":\"当前状态禁止此操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, false);
    }

    @Test
    public void abandonRegionTest2() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(delete("/shops/1/regions/4191").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void abandonRegionTest3() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(delete("/shops/0/regions/0").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test   //标识此方法为测试方法
    public void suspendRegionTest() throws Exception {
        String responseString = this.mvc.perform(put("/shops/0/regions/4/suspend").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test   //标识此方法为测试方法
    public void suspendRegionTest1() throws Exception {

        String responseString = this.mvc.perform(put("/shops/0/regions/4191/suspend").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":507,\"errmsg\":\"当前状态禁止此操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void suspendRegionTest2() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/1/regions/4191/suspend").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void suspendRegionTest3() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/0/regions/0/suspend").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test   //标识此方法为测试方法
    public void resumeRegionTest() throws Exception {
        String responseString = this.mvc.perform(put("/shops/0/regions/6/suspend").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

        responseString = this.mvc.perform(put("/shops/0/regions/6/resume").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        expectedResponse = "{\"errno\":0,\"errmsg\":\"成功\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test   //标识此方法为测试方法
    public void resumeRegionTest1() throws Exception {

        String responseString = this.mvc.perform(put("/shops/0/regions/4191/resume").header("authorization", adminToken))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":507,\"errmsg\":\"当前状态禁止此操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);
    }

    @Test
    public void resumeRegionTest2() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/1/regions/4191/resume").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isForbidden())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":505,\"errmsg\":\"非管理员无权操作\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

    @Test
    public void resumeRegionTest3() throws Exception {
        RegionVo r =  new RegionVo();
        r.setName("test");

        String goodJson = JacksonUtil.toJson(r);

        String responseString = this.mvc.perform(put("/shops/0/regions/0/resume").header("authorization", adminToken).contentType("application/json;charset=UTF-8").content(goodJson))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString();

        String expectedResponse = "{\"errno\":504,\"errmsg\":\"操作的资源id不存在\"}";

        JSONAssert.assertEquals(expectedResponse, responseString, true);

    }

}
