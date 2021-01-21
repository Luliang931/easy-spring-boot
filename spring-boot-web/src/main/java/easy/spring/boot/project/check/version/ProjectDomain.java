package easy.spring.boot.project.check.version;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 项目类
 *
 * @Author: luliangliang
 * @Date: 2021/1/21 7:38 下午
 */
@Data
public class ProjectDomain implements Serializable {

    /**
     * 项目ID
     */
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 项目关联的应用列表
     */
    private List<ApplicationDomain> applications;

}
