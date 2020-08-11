package network.feign.demo.client;

import network.feign.demo.req.RegisterReq;
import network.feign.demo.resp.BaseResp;
import network.feign.demo.resp.RegisterResp;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudData
 * @description: 用于熔断发生时的处理。
 * @author: Rongjin Zhang
 * @create: 2020-08-06 15:16
 */
@Component
public class ServiceFallback implements ServiceFeignClient{
    @Override
    public BaseResp<RegisterResp> register(RegisterReq req) {
        return BaseResp.build(10001,"");
    }
}
