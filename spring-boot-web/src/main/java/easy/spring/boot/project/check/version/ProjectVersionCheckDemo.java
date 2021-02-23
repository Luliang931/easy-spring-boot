package easy.spring.boot.project.check.version;

import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 校验入口
 *
 * @Author: luliangliang
 * @Date: 2021/1/21 7:55 下午
 */
public class ProjectVersionCheckDemo {

    public ProjectDomain getProject(){
        ProjectDomain project = new ProjectDomain();
        project.setId(1L);
        project.setName("测试项目");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startTime = dateFormat.parse("2021-01-01 00:00:00");
            project.setStartTime(startTime);
            project.setCreatedAt(startTime);
            project.setUpdatedAt(startTime);
            Date endTime = dateFormat.parse("2021-01-31 00:00:00");
            project.setStartTime(endTime);
        } catch (ParseException e) {
        }
        project.setCreator("test");

        /**
         * orderApp -》 planApp
         * orderApp -》 creedApp
         * orderApp -》 contractApp
         * requireApp -》 orderApp
         * requireApp -》 planApp
         */
        // 应用定义
        ApplicationDomain requireApp = new ApplicationDomain("requireApp","采购单中心","采购单中心负责人",new ArrayList<>(),new ArrayList<>());
        ApplicationDomain orderApp = new ApplicationDomain("orderApp","订单中心","订单中心负责人",new ArrayList<>(),new ArrayList<>());
        ApplicationDomain planApp = new ApplicationDomain("planApp","采购计划","采购计划负责人",new ArrayList<>(),new ArrayList<>());
        List<ApplicationEnvReleaseDomain> creedAppReleaseEnvs = new ArrayList<>();
        creedAppReleaseEnvs.add(new ApplicationEnvReleaseDomain(true,"wuxi-prod"));
        ApplicationDomain creedApp = new ApplicationDomain("creedApp","履约单中心","履约单中心负责人",new ArrayList<>(),creedAppReleaseEnvs);
        List<ApplicationEnvReleaseDomain> contractAppReleaseEnvs = new ArrayList<>();
        contractAppReleaseEnvs.add(new ApplicationEnvReleaseDomain(false,"wuxi-prod"));
        ApplicationDomain contractApp = new ApplicationDomain("contractApp","合同中心","合同中心负责人",new ArrayList<>(),contractAppReleaseEnvs);

        // 相邻关联关系
        // 采购单
        List<ApplicationRelateDomain> requireApplicationRelates = new ArrayList<>();
        requireApplicationRelates.add(this.buildAppRelate(orderApp,true));
        requireApplicationRelates.add(this.buildAppRelate(planApp,false));
        requireApp.setApplicationRelates(requireApplicationRelates);


        // 订单
        List<ApplicationRelateDomain> orderApplicationRelates = new ArrayList<>();
        orderApplicationRelates.add(this.buildAppRelate(planApp,false));
        orderApplicationRelates.add(this.buildAppRelate(creedApp,true));
        orderApplicationRelates.add(this.buildAppRelate(contractApp,true));
        orderApp.setApplicationRelates(orderApplicationRelates);

        // 采购计划
        planApp.setApplicationRelates(new ArrayList<>());

        // 履约
        creedApp.setApplicationRelates(new ArrayList<>());

        // 合同
        contractApp.setApplicationRelates(new ArrayList<>());

        // 项目应用关联
        List<ApplicationDomain> applications = new ArrayList<>();
        applications.add(requireApp);
        applications.add(orderApp);
        applications.add(planApp);
        applications.add(creedApp);
        applications.add(contractApp);

        project.setApplications(applications);
        return project;
    }

    private ApplicationRelateDomain buildAppRelate(ApplicationDomain relateToApp, boolean relate) {
        return new ApplicationRelateDomain(relateToApp.getAppCode(),relateToApp.getAppName(),relate);
    }

    public static void main(String[] args) {

        ProjectVersionCheckDemo projectVersionCheckDemo = new ProjectVersionCheckDemo();
        ProjectDomain project = projectVersionCheckDemo.getProject();

        // 校验的应用
        String checkAppCode = "orderApp";
        String checkReleaseEnv = "wuxi-prod";

        System.out.println(checkAppCode + "应用校验开始。");

        List<ApplicationDomain> applications = project.getApplications();
        Map<String/*appCode*/, ApplicationDomain> appsMap = applications.stream().collect(Collectors.toMap(ApplicationDomain::getAppCode, Function.identity(), (k1, k2) -> k2));
        ApplicationDomain checkApp = appsMap.get(checkAppCode);
        if (checkApp == null) {
            System.out.println(checkAppCode + "应用不存在，请重新输入。");
            return;
        }

        List<ApplicationEnvReleaseDomain> appEnvReleases = checkApp.getAppEnvReleases();
        List<ApplicationEnvReleaseDomain> envReleases = appEnvReleases.stream().filter(app -> checkReleaseEnv.equals(app.getReleaseEnv()) && app.getRelease()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(envReleases)){
            System.out.println(checkAppCode + "应用已发布。");
            return;
        }

        List<ApplicationRelateDomain> appRelates = checkApp.getApplicationRelates();
        if (CollectionUtils.isEmpty(appRelates)){
            System.out.println(checkAppCode + "应用不存在依赖，可以自由发布。");
            return;
        }
        List<ApplicationRelateDomain> strongNotReleaseApps = appRelates.stream().filter(app -> adjustStrongNotReleaseApps(app,appsMap.get(app.getAppCode()),checkReleaseEnv)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(strongNotReleaseApps)){
            System.out.println(checkAppCode + "应用不存在未发布的强依赖，可以选择发布。");
        }else {
            StringBuffer sb = new StringBuffer();
            for (ApplicationRelateDomain strongNotReleaseApp : strongNotReleaseApps) {
                sb.append("[");
                sb.append(strongNotReleaseApp.getAppCode());
                sb.append("(");
                sb.append(appsMap.get(strongNotReleaseApp.getAppCode()).getOwner());
                sb.append(")");
                sb.append("]");
            }
            System.out.println(checkAppCode + "应用存在未发布的强依赖应用，分别为："+ sb.toString()+"，请联系相关负责人。");
        }

        System.out.println(checkAppCode + "应用校验完毕。");

        return;
    }

    private static boolean adjustStrongNotReleaseApps(ApplicationRelateDomain appRelate, ApplicationDomain app, String checkReleaseEnv) {
        Boolean strongRelate = appRelate.getStrongRelate();
        for (ApplicationEnvReleaseDomain appEnvRelease : app.getAppEnvReleases()) {
            if (!(checkReleaseEnv.equals(appEnvRelease.getReleaseEnv()) && appEnvRelease.getRelease()) && strongRelate){
                return true;
            }
        }
        return false;
    }
}
