package taobao.step02.JobMain;

import taobao.step02.mapper.UserBehaviorMapper;
import taobao.step02.reducer.UserBehaviorReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class UserBehaviorJobMain extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        // 设置HADOOP使⽤的⽤户，如果使⽤root⽤户需要设置
        System.setProperty("HADOOP_USER_NAME", "root");
        // 创建⼀个Job任务对象
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop-svr-01:9000");
        Job job = Job.getInstance(conf, "step02_user_behavior");
        job.setJarByClass(UserBehaviorJobMain.class);
        // 配置Job任务对象（8个步骤）
        // 1. 读取⽂件，指定⽤哪个Format并设置HDFS路径(⽬录就可以，⾃动读取⽬录下所有⽂件)
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("/taobao_data_input"));
        // 2. 指定Map阶段的处理⽅式和数据类型
        job.setMapperClass(UserBehaviorMapper.class);
        // 设置map阶段K2的类型
        job.setMapOutputKeyClass(Text.class);
        // 设置map阶段V2的类型
        job.setMapOutputValueClass(LongWritable.class);
        // 7. 指定Reduce阶段的处理⽅式和数据类型
        job.setReducerClass(UserBehaviorReducer.class);
        // 设置K3的类型
        job.setOutputKeyClass(Text.class);
        // 设置V3的类型
        job.setOutputValueClass(LongWritable.class);
        // 8. 设置输出类型和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        // 结果输出路径
        // 如果存在结果⽬录则先删除⽬录
        FileSystem hdfs = FileSystem.get(conf);
        Path savePath = new Path("/taobao_data_output/Step02/user_behavior");
        if (hdfs.exists(savePath)){
            hdfs.delete(savePath, true);
        }
        TextOutputFormat.setOutputPath(job, savePath);
        // 等待任务结束，flag代表着任务成果和失败
        boolean flag = job.waitForCompletion(true);
        if (flag){
            System.out.println("Step02_UserBehavior任务执⾏成功");
            return 0;
        }
        else {
            System.out.println("Step02_UserBehavior任务执⾏失败");
            return 1;
        }
    }
}
