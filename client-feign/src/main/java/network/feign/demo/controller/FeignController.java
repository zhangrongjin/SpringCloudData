package network.feign.demo.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import network.feign.demo.client.ServiceFeignClient;
import network.feign.demo.req.RegisterReq;
import network.feign.demo.resp.BaseResp;
import network.feign.demo.resp.RegisterResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringCloudData
 * @description: 注入FeignClient，调用feignClient的方法实现远程方法调用
 * @author: Rongjin Zhang
 * @create: 2020-08-06 15:32
 */
@Slf4j
@RestController
public class FeignController {
    @Autowired
    private ServiceFeignClient serviceFeignClient;

    /**
     * 使用http://localhost:8060/register，实际上A服务会通过FeignClient调用服务B提供的register接口
     *
     * @param req
     * @return
     */
    @RequestMapping("/register")
    public String registerue(@RequestBody RegisterReq req) {
        log.info("input"+JSONObject.toJSONString(req));
        BaseResp<RegisterResp> resp = serviceFeignClient.register(req);
        log.info(JSONObject.toJSONString(resp));
        return JSONObject.toJSONString(resp);
    }

}
