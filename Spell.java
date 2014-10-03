package textanalytics;
import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.impl.util.WrappedIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Spell extends EvalFunc<String>{
	ArrayList <String> cacheFile = new ArrayList <String> ();

	public String exec(Tuple input) throws IOException{
		FileReader fr = new FileReader("./f1");
		BufferedReader br = new BufferedReader(fr);
		String fileLine = "";
		while ((fileLine = br.readLine()) != null) {
			cacheFile.add(fileLine);
		}
		fr.close();

		int distance = 100000;
		int minDistance = 100000;
		String correctedWord = "";
		String dictWord = "";
		String jobWord = (String)input.get(0);
		
		for(int i = 0; i < cacheFile.size(); i++){
			dictWord = (String)cacheFile.get(i);
			if(jobWord.equals(dictWord))
				correctedWord = jobWord;
			else{
				if(jobWord.length() < 2)
					correctedWord = jobWord;
				else{
					Levenshtein spell = new Levenshtein();
					distance = spell.getLevenshteinDistance(jobWord,dictWord);
					if(distance < minDistance){
						correctedWord = dictWord;
						minDistance = distance;
					}
				}
			}
		}
		return correctedWord;
	}

	public List<String> getCacheFiles() { 
        List<String> list = new ArrayList<String>(1); 
        list.add("dictionary.txt#f1"); 
        return list; 
    } 
}
