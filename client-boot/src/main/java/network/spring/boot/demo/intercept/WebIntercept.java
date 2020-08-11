package network.spring.boot.demo.intercept;

import com.crossoverjie.distributed.annotation.ControllerLimit;
import lombok.extern.slf4j.Slf4j;
import network.spring.boot.demo.common.RedisLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringCloudData
 * @description: controller拦截器
 * @author: Rongjin Zhang
 * @create: 2020-08-11 10:39
 */
@Slf4j
@Component
public class WebIntercept extends WebMvcConfigurerAdapter {


    @Autowired
    private RedisLimit redisLimit;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new WebIntercept.CustomInterceptor())
                .addPathPatterns("/**");
    }


    private class CustomInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws Exception {

            if (redisLimit == null) {
                throw new NullPointerException("redisLimit is null");
            }

            if (handler instanceof HandlerMethod) {
                HandlerMethod method = (HandlerMethod) handler;

                ControllerLimit annotation = method.getMethodAnnotation(ControllerLimit.class);
                if (annotation == null) {
                    //skip
                    return true;
                }

                boolean limit = redisLimit.limit();
                if (!limit) {
                    log.warn("request has bean limited");
                    response.sendError(500, "request limited");
                    return false;
                }

            }

            return true;

        }
    }
}
