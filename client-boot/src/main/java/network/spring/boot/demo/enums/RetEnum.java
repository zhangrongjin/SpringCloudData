package network.spring.boot.demo.enums;

/**
 *  业务初始化枚举
 *  @file RetEnum.java
 *  @description 
 *	@author zhangrj
 *  @data 2019年8月31日
 */
public enum RetEnum {

    /** 业务错误码定义 */
    
    RET_SUCCESS(0,"成功"),
    RET_PARAM_VALLID(-1,"请求参数无效"),
    RET_SYS_EXCEPTION(-100,"系统异常"),
    RET_FAIL(1,"失败");

	/** 描述 */
    private String desc;
    /** 错误码 */
    private int code;

    RetEnum(int code, String desc){
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }

    public static RetEnum getEnumByCodeValue(int code){
        RetEnum[] allEnums = values();
        for(RetEnum enableStatus : allEnums){
            if(enableStatus.getCode()==code)
                return enableStatus;
        }
        return null;
    }
}
