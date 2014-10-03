package textanalytics;
import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.WrappedIOException;

public class Stem extends EvalFunc<String>{
	public String exec(Tuple input) throws IOException{
		Porter stem = new Porter();
		String output = "";
		output = stem.stripAffixes((String)input.get(0));
		return output;
	}
}
