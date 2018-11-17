import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GSyslogFile {

    private static LinkedHashMap<String, Integer> GSyslogFile(String fileName) {
        File f = new File(fileName);
        LinkedHashMap<String, Integer> h = new LinkedHashMap<String, Integer>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String st;
            while ((st = br.readLine()) != null) {
                if (st.contains("api")){
                    int cmdBeginning  = st.indexOf('"') + 1;
                    String leftOverString1 = st.substring(cmdBeginning);
                    int startingIndex  = leftOverString1.indexOf(' ') + 1;
                    String leftOverString2 = leftOverString1.substring(startingIndex);
                    int endIndex  = leftOverString2.indexOf(' ');
                    String url = st.substring(cmdBeginning + startingIndex, cmdBeginning + startingIndex + endIndex);
                    if(h.containsKey(url)){
                        int value = h.get(url) + 1;
                        h.put(url, value);
                    } else {
                        h.put(url, 1);
                    }
                }
            }
        }catch (IOException e) {
            System.out.println("IOException Exception Occurced");
            e.printStackTrace();
        }
        return h;
    }

    private static LinkedHashMap<String, List<StatusCount>> statusCount(String fileName) {
        File f = new File(fileName);
        LinkedHashMap<String, List<StatusCount>> h = new LinkedHashMap<String, List<StatusCount>>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String st;
            while ((st = br.readLine()) != null) {
                if (st.contains("api")){
                    int cmdBeginning  = st.indexOf('"') + 1;
                    String leftOverString1 = st.substring(cmdBeginning);
                    int startingIndex  = leftOverString1.indexOf(' ') + 1;
                    String leftOverString2 = leftOverString1.substring(startingIndex);
                    int endIndex  = leftOverString2.indexOf(' ');
                    String url = st.substring(cmdBeginning + startingIndex, cmdBeginning + startingIndex + endIndex);
                    int statusCodeStarting = leftOverString1.indexOf('"') + 2;
                    String leftOverString3 = leftOverString1.substring(statusCodeStarting);
                    int statusCodeEnd  = leftOverString3.indexOf(' ');
                    String status  = st.substring(cmdBeginning + statusCodeStarting, statusCodeEnd + cmdBeginning + statusCodeStarting);
                    if(h.containsKey(url)){
                        List<StatusCount> statusList = h.get(url);
                        for ( int i = 0 ; i < statusList.size() ; i++ ){
                            StatusCount statusCount = statusList.get(i);
                            if (statusCount.getStatus().equals(status)){
                                statusCount.increaseBy(1);
                                break;
                            }
                        }
                        h.put(url, statusList);
                    } else {
                        List<StatusCount> statusList = new ArrayList<StatusCount>();
                        statusList.add(new StatusCount(status, 1));
                        h.put(url, statusList);
                    }
                }
            }
        }catch (IOException e) {
            System.out.println("IOException Exception Occurced");
            e.printStackTrace();
        }
        return h;
    }

    public static void main(String[] args) {
        String input = "input";
        LinkedHashMap<String, Integer> urlCount = GSyslogFile(input);
        for (Map.Entry<String, Integer>  entry : urlCount.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println("Status Counting");
        LinkedHashMap<String, List<StatusCount>> statusCount = statusCount(input);
        for (Map.Entry<String, List<StatusCount>>  entry : statusCount.entrySet()){
            String key = entry.getKey();
            System.out.println(key);
            List<StatusCount> statusList = statusCount.get(key);
            for ( int i =0 ; i < statusList.size() ; i++){
                System.out.println(statusList.get(i).getStatus() + ": " + statusList.get(i).getCount());
            }
        }
    }
}
