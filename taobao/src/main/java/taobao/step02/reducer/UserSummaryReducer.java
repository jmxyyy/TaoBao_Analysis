package taobao.step02.reducer;

// import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class UserSummaryReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
    private final NullWritable outValue = NullWritable.get();

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Reducer <Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        context.write(key, outValue);
    }
}
