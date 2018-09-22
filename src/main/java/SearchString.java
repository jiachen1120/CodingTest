
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class SearchString {
    BufferedReader br;

    public SearchString() throws Exception {
        File file = new File("king-i.txt");
        br = new BufferedReader(new FileReader(file));
    }

    public String Find(String target) throws IOException {
        Map<String, Object> map = new HashMap<>();
        int count = 0;
        int lineCount = 0;
        List<Map<String, Integer>> list = new ArrayList<>();
        String[] format = new String[] {"query_test", "number_of_occurrences", "occurrences"};
        String[] format1 = new String[] {"line", "start", "end"};
        map.put(format[0], target);
        map.put(format[2], list);
        String line;
        while ((line = br.readLine()) != null) {
            line = line.toLowerCase();
            int indexfound = 0;
            int end = 0;
            int start = 0;
            while (indexfound != -1) {
                indexfound = line.substring(end).indexOf(target);
                if (indexfound >= 0) {
                    start = indexfound + end;
                    end = start + target.length();
                    if ((end >= line.length() || line.charAt(end) < 97 || line.charAt(end) > 122)
                            && (start == 0 || line.charAt(start - 1) < 97 || line.charAt(start - 1) > 122)) {
                        Map<String, Integer> occur = new TreeMap<>();
                        occur.put(format1[0], lineCount + 1);
                        occur.put(format1[1], start + 1);
                        occur.put(format1[2], end + 1);
                        List<Map<String, Integer>> l = (List<Map<String, Integer>>) map.get(format[2]);
                        l.add(occur);
                        count++;
                    }
                }
            }
            lineCount++;
        }
        br.close();
        map.put(format[1], count);
        JSONObject json = new JSONObject(map);
        return json.toString(4);
    }
}
