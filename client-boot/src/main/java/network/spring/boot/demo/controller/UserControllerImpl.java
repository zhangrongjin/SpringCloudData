package network.spring.boot.demo.controller;

import network.spring.boot.demo.common.CommonMethod;
import network.spring.boot.demo.common.DataConst;
import network.spring.boot.demo.enums.I18nEnum;
import network.spring.boot.demo.enums.RetEnum;
import network.spring.boot.demo.req.RegisterReq;
import network.spring.boot.demo.res.BaseResp;
import network.spring.boot.demo.res.user.RegisterResp;
import network.spring.boot.demo.service.UserService;
import network.spring.boot.demo.utils.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.validation.Valid;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudData
 * @description: 用户controller实现类
 * @author: Rongjin Zhang
 * @create: 2020-08-05 15:34
 */
@RestController
public class UserControllerImpl implements UserController{

    @Autowired
    private UserService userService;

    @Override
    public WebAsyncTask<BaseResp<RegisterResp>> register(@Valid RegisterReq req) {
        /**
         * 异步调用，超时则进入timeout
         */
        WebAsyncTask<BaseResp<RegisterResp>> webAsyncTask = new WebAsyncTask<>(DataConst.WEB_TIME_OUT, () -> {
            return userService.register(req);
        });
        CommonMethod.onTimeOut(webAsyncTask);
        return webAsyncTask;
    }
}
