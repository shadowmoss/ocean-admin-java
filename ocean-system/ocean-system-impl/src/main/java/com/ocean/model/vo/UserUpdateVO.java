package com.ocean.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ltx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateVO extends UserCreateVO{
    /**
     * 用户id
     */
    private Long id;
}
