package easy.spring.boot.project.check.version;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目包含的应用类
 *
 * @Author: luliangliang
 * @Date: 2021/1/21 7:41 下午
 */
@Data
@AllArgsConstructor
public class ApplicationDomain implements Serializable {

    /**
     * 应用code
     */
    private String appCode;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用负责人
     */
    private String owner;

    /**
     * 关联应用及关系
     */
    private List<ApplicationRelateDomain> applicationRelates = new ArrayList<>();

    /**
     * 应用发布信息
     */
    private List<ApplicationEnvReleaseDomain> appEnvReleases = new ArrayList<>();
}
