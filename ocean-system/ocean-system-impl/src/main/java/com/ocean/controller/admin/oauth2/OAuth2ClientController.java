package com.ocean.controller.admin.oauth2;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.common.restful.RestfulResponse;
import com.ocean.enums.OceanSystemResCode;
import com.ocean.model.vo.oauth2.*;
import com.ocean.service.oauth2.OAuth2ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ltx
 */
@RestController
@RequestMapping("/oauth2/client")
public class OAuth2ClientController {
    @Resource
    private OAuth2ClientService oAuth2ClientService;
    @PostMapping("/create")
    @Operation(summary = "创建OAuth2新客户端")
    public RestfulResponse<Boolean> createNewClient(@RequestBody CreateNewOAuth2ClientVO createNewOAuth2ClientVO){
        Boolean flag =  oAuth2ClientService.createNewOAuth2Client(createNewOAuth2ClientVO);
        if(flag){
            return RestfulResponse.ok("新建OAuth2客户端成功",flag);
        }else{
            return RestfulResponse.fail(OceanSystemResCode.OAUTH2_CLIENT_CREATE_FAILURE.description, OceanSystemResCode.OAUTH2_CLIENT_CREATE_FAILURE.code,flag);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "更新OAuth2客户端信息")
    public RestfulResponse<Boolean> updateClient(@RequestBody UpdateNewOAuth2ClientVO updateNewOAuth2ClientVO){
        Boolean flag = oAuth2ClientService.updateNewOAuth2Client(updateNewOAuth2ClientVO);
        if(flag){
            return RestfulResponse.ok("更新OAuth2客户端成功",flag);
        }else{
            return RestfulResponse.fail(OceanSystemResCode.OAUTH2_CLIENT_UPDATE_FAILURE.description,OceanSystemResCode.OAUTH2_CLIENT_UPDATE_FAILURE.code,flag);
        }
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除OAuth2客户端")
    public RestfulResponse<Boolean> deleteClient(@RequestParam Long id){
        Boolean flag = oAuth2ClientService.deleteOAuth2Client(id);
        if(flag){
            return RestfulResponse.ok("删除OAuth2客户端成功",flag);
        }else{
            return RestfulResponse.fail(OceanSystemResCode.OAUTH2_CLIENT_DELETE_FAILURE.description,OceanSystemResCode.OAUTH2_CLIENT_DELETE_FAILURE.code,flag);
        }
    }

    @GetMapping("/page")
    @Operation(summary = "OAuth2客户端分页")
    public RestfulResponse<Page<OAuth2ClientPageVO>> pageOAuth2Client(OAuth2ClientPageReqVO oAuth2ClientPageReqVO){
        Page<OAuth2ClientPageVO> page = oAuth2ClientService.pageOAuth2Client(oAuth2ClientPageReqVO);
        return RestfulResponse.ok("分页查询成功",page);
    }
    @GetMapping("/details")
    @Operation(summary = "根据id查询客户端信息")
    public RestfulResponse<OAuth2ClientDetailsVO> getOAuth2ClientDetails(Long id){
        OAuth2ClientDetailsVO vo = oAuth2ClientService.getOAuth2ClientDetails(id);
        return RestfulResponse.ok("查询客户端成功",vo);
    }
}
