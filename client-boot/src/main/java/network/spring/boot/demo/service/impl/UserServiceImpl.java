package network.spring.boot.demo.service.impl;

import network.data.dao.entity.User;
import network.data.dao.mapper.UserMapper;
import network.spring.boot.demo.enums.RetEnum;
import network.spring.boot.demo.req.RegisterReq;
import network.spring.boot.demo.res.BaseResp;
import network.spring.boot.demo.res.user.RegisterResp;
import network.spring.boot.demo.service.UserService;
import network.data.dao.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: SpringCloudData
 * @description: 用户模块逻辑实现类
 * @author: Rongjin Zhang
 * @create: 2020-08-05 15:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResp<RegisterResp> register(RegisterReq req) {
        User user = new User();
        BeanUtils.copyProperties(req,user);
        int ret = userMapper.insert(user);
        if(ret < 1){
            return BaseResp.build(RetEnum.RET_FAIL);
        }
        return BaseResp.buildSuccess();
    }
}
