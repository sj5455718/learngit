package com.haiyisoft.cloud.api;

import com.haiyisoft.cloud.devops.model.ApiResult;
import com.haiyisoft.cloud.devops.project.data.DepartmentInfoWrap;
import com.haiyisoft.cloud.devops.project.service.DepartInfoService;
import com.haiyisoft.cloud.devops.web.util.UserViewUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lx
 */
@RestController
public class DeptApiController {

    @Autowired
    private DepartInfoService departInfoService;

    /**
     * 根据子部门查询顶级部门信息（未找到调用方法）
     * @param subId
     * @return
     */
    @RequestMapping(value = "/getTopDeptBySubId")
    public DepartmentInfoWrap getTopDeptBySubId(String subId, String username) {
        return departInfoService.getUserDepartInfo(subId, username);
    }

    @RequestMapping(value = "/getAllTopDept")
    public List<DepartmentInfoWrap> getAllTopDept() {
        return departInfoService.getAllDepartInfo();
    }

    @RequestMapping(value = "/getAllSubDeptsAndPersons")
    public ApiResult<Map<String, Object>> getAllSubDeptsAndPersons(String deptId){
        try {
            return ApiResult.success(departInfoService.getAllSubDeptsAndPersons(deptId));
        }catch (Exception e){
            return ApiResult.error(e.getMessage());
        }
    }
}
