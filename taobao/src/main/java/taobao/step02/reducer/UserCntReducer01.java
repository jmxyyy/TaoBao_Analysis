package taobao.step02.reducer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserCntReducer01 extends Reducer<Text, LongWritable, Text, LongWritable> {
    private final LongWritable outValue = new LongWritable(1);

    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        context.write(key, outValue);
    }
}