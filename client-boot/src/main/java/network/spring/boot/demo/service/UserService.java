package network.spring.boot.demo.service;

import network.spring.boot.demo.req.RegisterReq;
import network.spring.boot.demo.res.BaseResp;
import network.spring.boot.demo.res.user.RegisterResp;

public interface UserService {

    BaseResp<RegisterResp> register(RegisterReq req);
}
