package taobao.step03.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class DateStatMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Text outKey = new Text();
    private final Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] values = value.toString().split(",");
        // 130758,375465,154040,pv,2017-12-01,18
        // 130758,4093676,154040,pv,2017-12-01,18
        String date = values[4];
        String eventType = values[3];
        outKey.set(date);
        outValue.set(eventType);
        context.write(outKey, outValue);
    }
}
