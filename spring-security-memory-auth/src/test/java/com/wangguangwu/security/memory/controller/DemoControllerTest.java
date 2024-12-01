package com.wangguangwu.security.memory.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 示例控制器测试类
 *
 * @author wangguangwu
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenNotAuthenticated_thenRedirectToLogin() throws Exception {
        mockMvc.perform(get("/api/user/info"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAuthenticated_canAccessUserApi() throws Exception {
        mockMvc.perform(get("/api/user/info"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void whenUserAuthenticated_cannotAccessAdminApi() throws Exception {
        mockMvc.perform(get("/api/admin/secured"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAuthenticated_canAccessAdminApi() throws Exception {
        mockMvc.perform(get("/api/admin/secured"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, authorities = {"admin:read"})
    void whenAdminWithProperAuthority_canAccessPreAuthorizeApi() throws Exception {
        mockMvc.perform(get("/api/admin/pre-authorize"))
                .andExpect(status().isOk());
    }
}
