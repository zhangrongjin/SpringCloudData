package network.spring.boot.demo.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
*@program: I18nEnum.java
*@description: 国际化枚举
*@author: Rongjin Zhang
*@create: 2020/8/5
*/
public enum I18nEnum {

    // 通用
    SUCCESS;
	private static final Map <String, I18nEnum> ENUMS = new HashMap <>();
    static {
        Arrays.asList(I18nEnum.values()).forEach(en -> ENUMS.put(en.name(), en));
    }
    public static I18nEnum getEnum ( String code ) {
        return ENUMS.get(code);
    }
}
