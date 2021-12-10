/**
 * Copyright School of Informatics Xiamen University
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package cn.edu.xmu.oomall.privilege;

import cn.edu.xmu.oomall.BaseTestOomall;
import cn.edu.xmu.oomall.PublicTestApp;
import cn.edu.xmu.privilegegateway.annotation.util.ReturnNo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PublicTestApp.class)
public class DepartsUsersRoles extends BaseTestOomall {

    private static String DELURL ="/privilege/departs/{did}/users/{uid}/roles/{rid}";
    private static String GETURL= "/privilege/departs/{did}/users/{id}/roles";
    private static String GETDEPARTROLEURL = "/privilege/departs/{did}/roles";


    /**
     * 19
     * 不同店铺管理员失败
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(1)
    public void getRoleTest1() throws Exception {
        String token = this.adminLogin("8131600001", "123456");
        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.RESOURCE_ID_OUTSCOPE.getCode())
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 19
     * 用户权限不够
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(2)
    public void getRoleTest2() throws Exception {
        String token = this.adminLogin("shop1_coupon", "123456");
        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.AUTH_NO_RIGHT.getCode())
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 19
     * 用户id不存在
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(3)
    public void getRoleTest3() throws Exception {
        String token = this.adminLogin("8131600001", "123456");
        this.mallClient.get().uri(GETURL,2,1999999).header("authorization", token)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.RESOURCE_ID_NOTEXIST.getCode())
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 19
     * 同店铺管理员
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(4)
    public void getRoleTest4() throws Exception {
        String token = this.adminLogin("2721900002", "123456");
        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 112)]").exists()
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 19
     * 超级管理员
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(5)
    public void getRoleTest5() throws Exception {
        String token = this.adminLogin("13088admin", "123456");
        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 112)]").exists()
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 20
     * 取消用户角色测试2:角色不存在
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(6)
    public void revokeRoleTest1() throws Exception {
        String token = this.adminLogin("13088admin", "123456");
        byte[] responseString = this.mallClient.delete().uri(DELURL,1,17343, 1000).header("authorization", token)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.RESOURCE_ID_NOTEXIST.getCode())
                .returnResult().getResponseBodyContent();
    }

    /**
     * 21
     * 取消用户角色测试3:用户权限不够
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(7)
    public void revokeRoleTest2() throws Exception {
        String token = this.adminLogin("shop1_coupon", "123456");
        this.mallClient.delete().uri(DELURL,1, 17346,111).header("authorization", token)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.AUTH_NO_RIGHT.getCode())
                .returnResult().getResponseBodyContent();

        this.mallClient.get().uri(GETURL,1,17346).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 111)]").exists()
                .returnResult()
                .getResponseBodyContent();
    }

    /**
     * 19
     * 取消用户角色测试1 不同店铺管理员失败
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(8)
    public void revokeRoleTest3() throws Exception {
        String token = this.adminLogin("8131600001", "123456");
        this.mallClient.delete().uri(DELURL,2, 17347,112).header("authorization", token)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.RESOURCE_ID_OUTSCOPE.getCode())
                .returnResult().getResponseBodyContent();

        token = this.adminLogin("2721900002", "123456");
        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 112)]").exists()
                .returnResult()
                .getResponseBodyContent();
    }


    /**
     * 19
     * 取消用户角色测试1 同店铺管理员成功
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(9)
    public void revokeRoleTest7() throws Exception {
        //判断用户是否有权限
        String token1 = this.adminLogin("delrole_user1", "123456");
        this.mallClient.get().uri(GETDEPARTROLEURL,1).header("authorization", token1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .returnResult()
                .getResponseBodyContent();

        String token = this.adminLogin("8131600001", "123456");
        this.mallClient.delete().uri(DELURL,1, 17346,111).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .returnResult().getResponseBodyContent();

        //角色不存在
        this.mallClient.get().uri(GETURL,1,17346).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 111)]").doesNotExist()
                .returnResult()
                .getResponseBodyContent();

        //权限不够
        this.mallClient.get().uri(GETDEPARTROLEURL,1).header("authorization", token1)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.AUTH_NO_RIGHT.getCode())
                .returnResult()
                .getResponseBodyContent();

    }


    /**
     * 19
     * 取消用户角色测试1 平台管理员成功
     * @throws Exception
     * @author Li Zihan
     */
    @Test
    @Order(10)
    public void revokeRoleTest8() throws Exception {
        //判断用户是否有权限
        String token1 = this.adminLogin("delrole_user2", "123456");
        this.mallClient.get().uri(GETDEPARTROLEURL,2).header("authorization", token1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .returnResult()
                .getResponseBodyContent();


        String token = this.adminLogin("13088admin", "123456");
        this.mallClient.delete().uri(DELURL,2, 17347,112).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .returnResult().getResponseBodyContent();

        this.mallClient.get().uri(GETURL,2,17347).header("authorization", token)
                .exchange()
                .expectStatus().isOk()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.OK.getCode())
                .jsonPath("$.data.list[?(@.id == 112)]").doesNotExist()
                .returnResult()
                .getResponseBodyContent();

        //权限不够
        this.mallClient.get().uri(GETDEPARTROLEURL,2).header("authorization", token1)
                .exchange()
                .expectStatus().isForbidden()
                .expectHeader()
                .contentType("application/json;charset=UTF-8")
                .expectBody()
                .jsonPath("$.errno").isEqualTo(ReturnNo.AUTH_NO_RIGHT.getCode())
                .returnResult()
                .getResponseBodyContent();
    }
}