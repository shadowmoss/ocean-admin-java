import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.ocean.mapper.UserMapper;
import com.ocean.model.entity.AdminUserDO;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author ltx
 */
@MybatisPlusTest
public class UserServiceTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void testFindOneUser()
    {
        AdminUserDO adminUserDO = new AdminUserDO();
        LambdaQueryWrapper<AdminUserDO> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(AdminUserDO::getUsername,"admin");
        AdminUserDO adminUserDO1 = userMapper.selectOne(userLambdaQueryWrapper);
        System.out.println(adminUserDO1.getUsername());

    }
}
