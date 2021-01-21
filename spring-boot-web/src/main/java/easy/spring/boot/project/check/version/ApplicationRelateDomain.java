package easy.spring.boot.project.check.version;

import lombok.Data;

import java.io.Serializable;

/**
 * 应用之间的关联关系类
 *
 * @Author: luliangliang
 * @Date: 2021/1/21 7:46 下午
 */
@Data
public class ApplicationRelateDomain implements Serializable {

    /**
     * 应用code
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 强、弱依赖
     */
    private Boolean strongRelate;

    public ApplicationRelateDomain(String appCode, String appName, Boolean strongRelate) {
        this.appCode = appCode;
        this.appName = appName;
        this.strongRelate = strongRelate;
    }
}
