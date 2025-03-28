package taobao.step02.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserCntMapper02 extends Mapper<LongWritable, Text, LongWritable, LongWritable> {
    private final LongWritable outKey = new LongWritable(1);
    private final LongWritable outValue = new LongWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, LongWritable>.Context context) throws IOException, InterruptedException {
        context.write(outKey, outValue);
    }
}
