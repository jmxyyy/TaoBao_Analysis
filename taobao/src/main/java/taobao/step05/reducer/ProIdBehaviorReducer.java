package taobao.step05.reducer;

// import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProIdBehaviorReducer extends Reducer<Text, Text, Text, NullWritable> {
    private final Text outKey = new Text();
    private final NullWritable outValue = NullWritable.get();

    @Override
    protected void reduce(Text key, Iterable<Text> values, 
                        Reducer<Text, Text, Text, NullWritable>.Context context) 
                        throws IOException, InterruptedException {
        // String dateStr = key.toString();
        String proId = key.toString();
        Map<String, Long> map = new HashMap<>();
        for (Text value : values) {
            // category, behavior
            String[] val = value.toString().split(",");

            // hashmap key: id, category, behavior
            if (map.containsKey(proId + "," + val[0] + "," + val[1])) {
                long curCnt = map.get(proId + "," + val[0] + "," + val[1]);
                map.put(proId + "," + val[0] + "," + val[1], curCnt + 1);
            }
            else{
                map.put(proId + "," + val[0] + "," + val[1], 1L);
            }
        }
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            // id, category, behavior
            String[] val = entry.getKey().toString().split(",");
            // id, category, behavior, cnt
            String result = proId + "," + val[1] + "," + val[2] + "," + entry.getValue();
            outKey.set(result);
            context.write(outKey, outValue);
        }
    }
}
