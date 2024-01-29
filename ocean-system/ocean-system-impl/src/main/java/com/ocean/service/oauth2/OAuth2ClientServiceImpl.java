package com.ocean.service.oauth2;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ocean.convert.SystemOAuth2ClientConvert;
import com.ocean.mapper.oauth2.SystemOAuth2ClientMapper;
import com.ocean.model.entity.SystemOAuth2ClientDO;
import com.ocean.model.vo.oauth2.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author ltx
 */
@Service
public class OAuth2ClientServiceImpl implements OAuth2ClientService{

    @Resource
    private SystemOAuth2ClientMapper systemOAuth2ClientMapper;
    @Override
    public Boolean createNewOAuth2Client(CreateNewOAuth2ClientVO createNewOAuth2ClientVO) {
        SystemOAuth2ClientDO systemOAuth2ClientDO = new SystemOAuth2ClientDO();
        systemOAuth2ClientDO = SystemOAuth2ClientConvert.INSTANCE.createVOToDO(createNewOAuth2ClientVO);
        int insert = systemOAuth2ClientMapper.insert(systemOAuth2ClientDO);
        if(insert>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean updateNewOAuth2Client(UpdateNewOAuth2ClientVO updateNewOAuth2ClientVO) {
        SystemOAuth2ClientDO systemOAuth2ClientDO = new SystemOAuth2ClientDO();
        systemOAuth2ClientDO = SystemOAuth2ClientConvert.INSTANCE.updateVOToDO(updateNewOAuth2ClientVO);
        int update = systemOAuth2ClientMapper.updateById(systemOAuth2ClientDO);
        if(update>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean deleteOAuth2Client(Long id) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> systemOAuth2ClientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        systemOAuth2ClientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getId,id);
        int delete = systemOAuth2ClientMapper.delete(systemOAuth2ClientDOLambdaQueryWrapper);
        if(delete>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page<OAuth2ClientPageVO> pageOAuth2Client(OAuth2ClientPageReqVO oAuth2ClientPageReqVO) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> systemOAuth2ClientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!Objects.isNull(oAuth2ClientPageReqVO.getName())){
            systemOAuth2ClientDOLambdaQueryWrapper.like(SystemOAuth2ClientDO::getName,oAuth2ClientPageReqVO.getName());
        }
        if(!Objects.isNull(oAuth2ClientPageReqVO.getStatus())){
            systemOAuth2ClientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getStatus,oAuth2ClientPageReqVO.getStatus());
        }
        Page<SystemOAuth2ClientDO> oAuth2ClientPageVOPage = new Page<>(oAuth2ClientPageReqVO.getCurrentPageNo(),oAuth2ClientPageReqVO.getPageSize());
        Page<SystemOAuth2ClientDO> systemOAuth2ClientDOPage = systemOAuth2ClientMapper.selectPage(oAuth2ClientPageVOPage, systemOAuth2ClientDOLambdaQueryWrapper);
        Page<OAuth2ClientPageVO> result = new Page<>();
        result.setPages(systemOAuth2ClientDOPage.getPages());
        result.setCurrent(systemOAuth2ClientDOPage.getCurrent());
        result.setSize(systemOAuth2ClientDOPage.getSize());
        result.setTotal(systemOAuth2ClientDOPage.getTotal());
        result.setRecords(new ArrayList<>());
        for(SystemOAuth2ClientDO item : systemOAuth2ClientDOPage.getRecords()){
            OAuth2ClientPageVO vo = SystemOAuth2ClientConvert.INSTANCE.DOToPageVO(item);
            result.getRecords().add(vo);
        }
        return result;
    }

    @Override
    public OAuth2ClientDetailsVO getOAuth2ClientDetails(Long id) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> clientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        clientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getId,id);
        SystemOAuth2ClientDO systemOAuth2ClientDO = systemOAuth2ClientMapper.selectOne(clientDOLambdaQueryWrapper);
        OAuth2ClientDetailsVO vo =  SystemOAuth2ClientConvert.INSTANCE.DOToDetailsVO(systemOAuth2ClientDO);
        return vo;
    }

    @Override
    public SystemOAuth2ClientDO getOAuth2ClientByClientId(String client_id, String secret) {
        LambdaQueryWrapper<SystemOAuth2ClientDO> oAuth2ClientDOLambdaQueryWrapper = new LambdaQueryWrapper<>();
        oAuth2ClientDOLambdaQueryWrapper.eq(SystemOAuth2ClientDO::getClientId,client_id)
                                        .eq(SystemOAuth2ClientDO::getSecret,secret);
        return systemOAuth2ClientMapper.selectOne(oAuth2ClientDOLambdaQueryWrapper);
    }
}
