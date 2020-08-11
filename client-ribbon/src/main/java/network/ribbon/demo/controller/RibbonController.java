package network.ribbon.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import network.ribbon.demo.client.ServiceFeignClient;
import network.ribbon.demo.req.RegisterReq;
import network.ribbon.demo.resp.BaseResp;
import network.ribbon.demo.resp.RegisterResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudData
 * @description: 注入RestTemplate，调用RestTemplate的方法实现远程方法调用
 * @author: Rongjin Zhang
 * @create: 2020-08-06 15:32
 */
@Slf4j
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 使用http://localhost:8060/register，实际上A服务会通过FeignClient调用服务B提供的register接口
     *
     * @param req
     * @return
     */
    @RequestMapping("/register")
    @HystrixCommand(fallbackMethod="consumerServiceRibbonFallback")
    public String register(@RequestBody RegisterReq req) {
        log.info("input"+JSONObject.toJSONString(req));
        BaseResp<RegisterResp> resp = this.restTemplate.postForObject("http://boot-api/boot/user/register?token=1", req, BaseResp.class);
        log.info(JSONObject.toJSONString(resp));
        return JSONObject.toJSONString(resp);
    }

    public String consumerServiceRibbonFallback(@RequestBody RegisterReq serviceInfo){
        return "consumerServiceRibbon异常" ;
    }

}
