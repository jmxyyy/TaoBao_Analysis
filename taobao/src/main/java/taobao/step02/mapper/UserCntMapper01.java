package taobao.step02.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class UserCntMapper01 extends Mapper<LongWritable, Text, Text, LongWritable>{
    private final Text outKey = new Text();
    private final LongWritable outValue = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        String[] keys = value.toString().split(",");
        outKey.set(keys[0]);
        context.write(outKey, outValue);
    }
}
