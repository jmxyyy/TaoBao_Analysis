package taobao.step01;

import taobao.step01.mapper.MyMapper;

// import com.neuedu.hadoop.wordcount.combiner.WordCountCombiner;
// import com.neuedu.hadoop.wordcount.mapper.WordCountMapper;
// import com.neuedu.hadoop.wordcount.reducer.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import taobao.step01.reducer.MyReducer;

public class JobMain extends Configured implements Tool {
    public static void main(String[] args) throws Exception{ // 启动Job任务，使⽤ToolRunner
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
        // default:HADOOP_USER_NAME
        System.setProperty("HADOOP_USER_NAME", "root");
        // 创建⼀个Job任务对象
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop-svr-01:9000");
        Job job = Job.getInstance(conf, "TaoBao_Step01");
        job.setJarByClass(JobMain.class);
        // 配置Job任务对象（8个步骤）
        // 1. 读取⽂件，指定⽤哪个Format并设置HDFS路径(⽬录就可以，⾃动读取⽬录下所有⽂件)
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path("/taobao_data_input"));
        // 2. 指定Map阶段的处理⽅式和数据类型
        job.setMapperClass(MyMapper.class);
        // 设置map阶段K2的类型
        job.setMapOutputKeyClass(Text.class);
        // 设置map阶段V2的类型
        job.setMapOutputValueClass(Text.class);
        // 3、4、5、6采⽤默认⽅式，暂时不做处理
        // 7. 指定Reduce阶段的处理⽅式和数据类型
        job.setReducerClass(MyReducer.class);
        // 设置K3的类型
        job.setOutputKeyClass(NullWritable.class);
        // 设置V3的类型
        job.setOutputValueClass(Text.class);

        // 8. 设置输出类型和输出路径
        job.setOutputFormatClass(TextOutputFormat.class);
        // 结果输出路径
        // 如果存在结果⽬录则先删除⽬录
        FileSystem hdfs = FileSystem.get(conf);
        Path savePath = new Path("/taobao_data_output/Step01");
        if (hdfs.exists(savePath)) {
            hdfs.delete(savePath, true);
        }
        TextOutputFormat.setOutputPath(job, savePath);
        // 等待任务结束，flag代表着任务成果和失败
        boolean flag = job.waitForCompletion(true);
        if (flag){
            System.out.println("任务执⾏成功");
            return 0;
        }
        else {
            System.out.println("任务执⾏失败");
            return 1;
        }
    }
}
