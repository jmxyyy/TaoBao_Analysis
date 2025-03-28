package taobao.step02;

import taobao.step02.JobMain.UserCntJobMain01;
import taobao.step02.JobMain.UserCntJobMain02;
import taobao.step02.JobMain.UserBehaviorJobMain;
import taobao.step02.JobMain.UserSummaryJobMain;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

public class JobMainBase {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // 执⾏第⼆步-⽤户总数统计-步骤1
        int run = ToolRunner.run(conf, new UserCntJobMain01(), args);
        if (run == 1) {
            // 进程退出
            System.exit(run);
        }
        // 执⾏第⼆步-⽤户总数统计-步骤2
        run = ToolRunner.run(conf, new UserCntJobMain02(), args);
        if (run == 1) {
            // 进程退出
            System.exit(run);
        }
        // 执⾏第三步-⽤户⾏为统计
        run = ToolRunner.run(conf, new UserBehaviorJobMain(), args);
        if (run == 1) {
            // 进程退出
            System.exit(run);
        }
        // 执⾏第四步-合并结果集
        run = ToolRunner.run(conf, new UserSummaryJobMain(), args);
        System.exit(run);
    }
}
