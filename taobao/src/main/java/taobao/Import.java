package taobao;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Import {
    public static void main(String[] args) throws IOException {
        // 获取HDFS文件系统对象
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop-svr-01:9000");
        FileSystem hdfs = FileSystem.get(conf);

        // 创建要操作的文件对象
        Path path = new Path("/workspace/taobao/" + "src/main/resources/UserBehavior_part_data.csv");
        Path dst = new Path("/taobao_data_input/UserBehavior_part_data.csv");

        // 上传文件
        hdfs.copyFromLocalFile(path, dst);

        System.out.println("文件上传完成");
        FileStatus[] statuses = hdfs.listStatus(new Path("/taobao_data_input"));
        Arrays.stream(statuses).forEach(System.out::println);
    }
}
