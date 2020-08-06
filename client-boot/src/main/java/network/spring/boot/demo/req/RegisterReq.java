package network.spring.boot.demo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: SpringCloudData
 * @description:
 * @author: Rongjin Zhang
 * @create: 2020-08-05 15:32
 */
@Data
public class RegisterReq {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private String code;
}
