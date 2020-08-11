package network.ribbon.demo.resp;



/**
 *  Base response message
 *  @file BaseResp.java
 *  @description
 *	@author Rongjin Zhang
 */
public class BaseResp<T> {
    protected String errMsg;
    protected Integer code;
    protected T data;


    public BaseResp() {
    	this.code=0;
    }

    public BaseResp(Integer code, String errMsg, T data){
        this.code=code;
        this.errMsg=errMsg;
        this.data=data;
    }

    /** static function , init return object */
    public static <T> BaseResp<T> build(Integer code, String errMsg){
        return BaseResp.build(code,errMsg, null);
    }

    public static <T> BaseResp<T> build(Integer code, String errMsg, T data){
        return new BaseResp<>(code,errMsg, data);
    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}