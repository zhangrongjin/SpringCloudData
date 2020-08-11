package network.ribbon.demo.req;

import lombok.Data;

/**
 * @program: SpringCloudData
 * @description:
 * @author: Rongjin Zhang
 * @create: 2020-08-05 15:32
 */
@Data
public class RegisterReq {

    private String userName;

    private String password;

    private String code;
}
