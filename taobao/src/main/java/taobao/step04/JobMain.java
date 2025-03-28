package taobao.step04;

import taobao.step04.mapper.HourlyBehaviorMapper;
import taobao.step04.reducer.HourlyBehaviorReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JobMain extends Configured implements Tool {
    public static void main(String[] args) throws Exception{
        // 启动Job任务，使⽤ToolRunner
        // ToolRunner：
        // 第⼀个参数：config类，
        Configuration conf = new Configuration();
        // 第⼆个参数，Tool接⼝，需要传实现类对象，也就是JobMain类本身
        // 第三个参数，输⼊的参数，⽤当前main⽅法的⼊参即可
        // 返回值，0表示任务成功，否则表示任务失败
        int run = ToolRunner.run(conf, new JobMain(), args);
        // 进程退出
        System.exit(run);
    }

    @Override
    public int run(String[] strings) throws Exception {
        // 设置HADOOP使⽤的⽤户，如果使⽤root⽤户需要设置
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop-svr-01:9000");
        Job job = Job.getInstance(conf, "STEP04");
        job.setJarByClass(JobMain.class); job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("/taobao_data_output/Step01"));

        job.setMapperClass(HourlyBehaviorMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        job.setReducerClass(HourlyBehaviorReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        Path out = new Path("/taobao_data_output/Step04");
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(out)){
            fs.delete(out, true);
        }
        TextOutputFormat.setOutputPath(job, out);
        boolean flag = job.waitForCompletion(true);
        if (flag){
            System.out.println("Step04运⾏成功");
            return 0;
        }
        else{
            System.out.println("Step04运⾏失败");
            return 1;
        }
    }
}
