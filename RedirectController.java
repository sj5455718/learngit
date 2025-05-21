package com.haiyisoft.cloud.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haiyisoft.cloud.devops.util.JwtTokenUtil;
import com.haiyisoft.cloud.web.ui.spring.service.LoginHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lx
 */
@RestController
public class RedirectController {

    @Autowired
    private LoginHandleService loginHandleService;

    @RequestMapping("/toPipeline2Page")
    public void toPipeline2Page(HttpServletRequest request,
                                HttpServletResponse response,
                                String token,
                                long projectId) throws IOException {
        DecodedJWT decodedJWT = JwtTokenUtil.getDecodedJWT(token);
        String username = decodedJWT.getClaim("username").asString();
        String password = decodedJWT.getClaim("password").asString();
        String loginInfo = username + ";;" + password;
        loginHandleService.handleLogin(request, loginInfo);

        response.sendRedirect("/cloud-devops/#/project/pipeline/pipelines?projectId=" + projectId);
    }
    
    @RequestMapping("/toProjectTaskPage")
    public void toProjectTaskPage(HttpServletRequest request, HttpServletResponse response, String token, long projectId) throws IOException {
        DecodedJWT decodedJWT = JwtTokenUtil.getDecodedJWT(token);
        String username = decodedJWT.getClaim("username").asString();
        String password = decodedJWT.getClaim("password").asString();
        String loginInfo = username + ";;" + password;
        loginHandleService.handleLogin(request, loginInfo);

        response.sendRedirect("/cloud-devops/#/project/manage/task?projectId=" + projectId);
    }
}
