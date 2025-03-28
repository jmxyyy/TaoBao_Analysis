package taobao.step05.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ProIdBehaviorMapper extends Mapper<LongWritable, Text, Text, Text> {
    private final Text outKey = new Text();
    private final Text outValue = new Text();

    @Override
    protected void map(LongWritable key, Text value, 
                        Mapper<LongWritable, Text, Text, Text>.Context context) 
                        throws IOException, InterruptedException {
        String[] values = value.toString().split(",");

        // userid, goodid,goodcate, event, time
        // 130758,375465,154040,pv,2017-12-01,18
        // 130758,4093676,154040,pv,2017-12-01,18
        // String userId = values[0];
        String proId = values[1];
        String proCategory = values[2];
        String eventType = values[3];
        outKey.set(proId);
        outValue.set(proCategory + "," + eventType);
        context.write(outKey, outValue);
    }
}