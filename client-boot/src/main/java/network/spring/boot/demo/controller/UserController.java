package network.spring.boot.demo.controller;

import com.crossoverjie.distributed.annotation.CommonLimit;
import com.crossoverjie.distributed.annotation.ControllerLimit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import network.spring.boot.demo.req.RegisterReq;
import network.spring.boot.demo.res.BaseResp;
import network.spring.boot.demo.res.user.RegisterResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.validation.Valid;

@Api(value = "/user", tags = "User")
public interface UserController {

    @ControllerLimit
    @ApiOperation(value = "user/register", nickname = "user register", notes = "", response = RegisterResp.class, tags = { "User" })
    @PostMapping(value = "user/register", produces = { "application/json" })
    WebAsyncTask<BaseResp<RegisterResp>> register(@ApiParam(value = "RegisterReq", required = true)@Valid @RequestBody RegisterReq req);
}
