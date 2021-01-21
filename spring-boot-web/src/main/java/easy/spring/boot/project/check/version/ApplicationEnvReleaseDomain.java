package easy.spring.boot.project.check.version;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 应用环境发布类
 *
 * @Author: luliangliang
 * @Date: 2021/1/21 9:35 下午
 */
@Data
@AllArgsConstructor
public class ApplicationEnvReleaseDomain implements Serializable {

    /**
     * 是否已发布
     */
    private Boolean release;

    /**
     * 发布环境
     * zcy-prod
     * shanghai-prod
     * wuxi-prod
     */
    private String releaseEnv;
}
