package network.spring.boot.demo.common;

import lombok.extern.slf4j.Slf4j;
import network.spring.boot.demo.exception.ConfigLoadingException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 链参数统一配置项
 * @Auther: Chendongming
 * @Date: 2019/8/10 16:12
 * @Description:
 */

@Slf4j
@Configuration
public class DBConfig {

    static {
        File saltFile = FileUtils.getFile(System.getProperty("user.dir"), "jasypt.properties");
        Properties properties = new Properties();
        try(InputStream in = new FileInputStream(saltFile)) {
            properties.load(in);
            String salt=properties.getProperty("jasypt.encryptor.password");
            if(StringUtils.isBlank(salt)) throw new ConfigLoadingException("加密盐不能为空!");
            salt=salt.trim();
            System.setProperty("JASYPT_ENCRYPTOR_PASSWORD",salt);
            log.info("salt:{}",salt);
        } catch (IOException | ConfigLoadingException e) {
            log.error("加载解密文件出错",e);
            System.exit(1);
        }
    }
}