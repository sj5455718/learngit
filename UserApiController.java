package com.haiyisoft.cloud.api;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.haiyisoft.cloud.devops.base.util.UumCachedUtil;
import com.haiyisoft.cloud.devops.base.util.XinKeCachedUtil;
import com.haiyisoft.entity.devops.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lx
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {


    // 获取所有人员
    @RequestMapping("/getAllPersons")
    public String getAllPersons() {
        List<User> users = XinKeCachedUtil.getAllUsers();
        JSONObject jo = new JSONObject();
        for (User user : users) {
            jo.set(user.getUserName(), user.getUserLink());
        }
        return JSONUtil.toJsonStr(jo);
    }
}
