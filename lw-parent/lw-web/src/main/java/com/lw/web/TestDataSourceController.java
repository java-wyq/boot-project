package com.lw.web;

import com.lw.domain.LwEntity;
import com.lw.dto.AjaxResult;
import com.lw.service.impl.DBChangeServiceImpl;
import com.lw.service.impl.LwServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dataSource")
public class TestDataSourceController {
    @Autowired
    private LwServiceImpl lwService;
    @Autowired
    private DBChangeServiceImpl dbChangeService;
    @RequestMapping("test/{id}/{tenantId}")
    public AjaxResult getId(@PathVariable("id")String id,@PathVariable("tenantId")String tenantId) throws Exception {
        dbChangeService.changeDb(id,tenantId);
        LwEntity lwEntity = lwService.selectByPrimaryKey(123456L);
        return AjaxResult.success(lwEntity);
    }
}
