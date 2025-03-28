package taobao.step04.reducer;

// import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HourlyBehaviorReducer extends Reducer<Text, Text, Text, NullWritable> {
    private final Text outKey = new Text();
    private final NullWritable outValue = NullWritable.get();

    @Override
    protected void reduce(Text key, Iterable<Text> values, 
                        Reducer<Text, Text, Text, NullWritable>.Context context) 
                        throws IOException, InterruptedException {
        // String dateStr = key.toString();
        String hourStr = key.toString();
        Map<String, Long> map = new HashMap<>();
        for (Text value : values) {
            String val = value.toString();
            if (map.containsKey(val)) {
                long curCnt = map.get(val);
                map.put(val, curCnt + 1);
            }
            else{
                map.put(val, 1L);
            }
        }
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String result = hourStr + "," + entry.getKey() + "," + entry.getValue();
            outKey.set(result);
            context.write(outKey, outValue);
        }
    }
}
