package taobao.step02.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserCntReducer02 extends Reducer<LongWritable, LongWritable, Text, LongWritable> {
    private final Text outKey = new Text();
    private final LongWritable outValue = new LongWritable();

    @Override
    protected void reduce(LongWritable key, Iterable<LongWritable> values, Reducer<LongWritable, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        long cnt = 0;
        for (@SuppressWarnings("unused") LongWritable value: values) {
            cnt++;
        }
        outKey.set("userCnt");
        outValue.set(cnt);
        context.write(outKey, outValue);
    }
}
