package network.spring.boot.demo.common;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.TimeoutException;

/**
 * 统一静态方法
 *  @file CommonMethod.java
 *  @description 
 *	@author zhangrj
 */
public class CommonMethod {

	/**
	 * web异步请求超时方法调用
	 * @method onTimeOut
	 * @param webAsyncTask
	 */
	public static <T> void onTimeOut(WebAsyncTask<T> webAsyncTask) {
		webAsyncTask.onTimeout(() -> {
			// 超时的时候，直接抛异常，让外层统一处理超时异常
			throw new TimeoutException("System busy!");
		});
	}

}
