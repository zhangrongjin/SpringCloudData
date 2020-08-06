package network.common.exception;


import lombok.Data;

import java.util.Locale;

/**
*@program: BusinessException.java
*@description: 
*@author: Rongjin Zhang
*@create: 2020/8/5
*/
@Data
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = -2838902301689578334L;
	private int errorCode = -1;
	private String errorMessage;
	/** 国际化 */
	private Locale locale;

	public BusinessException(String msg) {
		this(msg, Locale.getDefault());
	}

	public BusinessException(String msg, Locale locale) {
		this.errorMessage = msg;
		this.locale = locale;
	}

	public BusinessException(Integer errorCode, String msg) {
		this(errorCode, msg, Locale.getDefault());
	}

	public BusinessException(Integer errorCode, String msg, Locale locale) {
		this.errorCode = errorCode;
		this.errorMessage = msg;
		this.locale = locale;
	}

	/**
	 * 构造.
	 *
	 * @param msg 错误信息
	 * @param t   前一异常
	 */
	public BusinessException(Integer errorCode, String msg, Throwable t) {
		this(errorCode, msg, Locale.getDefault(), t);
	}

	public BusinessException(Integer errorCode, String msg, Locale locale, Throwable t) {
		this.setStackTrace(t.getStackTrace());

		this.errorCode = errorCode;
		this.errorMessage = msg;
		this.locale = locale;
	}
}
