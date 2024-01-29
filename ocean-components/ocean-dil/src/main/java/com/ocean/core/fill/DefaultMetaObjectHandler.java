package com.ocean.core.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ocean.common.enums.CommonDeletedEnum;
import com.ocean.core.model.BaseDO;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author ltx
 */
public class DefaultMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseDO){
            BaseDO baseDO = (BaseDO) metaObject.getOriginalObject();
            LocalDateTime current = LocalDateTime.now();
            // 创建时间为空,则以当前时间作为插入时间
            if(Objects.isNull(baseDO.getCreateTime())){
                baseDO.setCreateTime(current);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(baseDO.getUpdateTime())) {
                baseDO.setUpdateTime(current);
            }
            // 插入时初始化当前输入数据尚未被删除
            if(Objects.isNull(baseDO.getDeleted())){
                baseDO.setDeleted(CommonDeletedEnum.NOT_DELETE.code);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
