import java.util.LinkedHashMap;
import java.util.Map;
import java.lang.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Filename:   GSyslogFile.java
 * Project:    Coding Challenge
 * Authors:    Tulsi Jain
 */

public class GSyslogFile {

    // For counting of URLs
    private static LinkedHashMap<String, Integer> urlCount(String fileName) {
        File f = new File(fileName);
        // LinkedHashMap to maintain a order
        // Reading data a file
        LinkedHashMap<String, Integer> h = new LinkedHashMap<String, Integer>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String st;
            while ((st = br.readLine()) != null) {
                // if (st.contains("api")){
                int firstCommaIndex  = st.indexOf('"') + 1;
                String leftOverString1 = st.substring(firstCommaIndex);
                int startingIndex  = leftOverString1.indexOf(' ') + 1;
                String leftOverString2 = leftOverString1.substring(startingIndex);
                int endIndex  = leftOverString2.indexOf(' ');
                String url = st.substring(firstCommaIndex + startingIndex, firstCommaIndex + startingIndex + endIndex);
                if(h.containsKey(url)){
                    int value = h.get(url) + 1;
                    h.put(url, value);
                } else {
                    h.put(url, 1);
                }
                // }
            }
        }catch (IOException e) {
            // IOException whenever an input or output operation is failed
            System.out.println("IOException Exception Occurced");
            e.printStackTrace();
        }
        return h;
    }

    // For count of status for all URLs
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
                    int firstCommaIndex  = st.indexOf('"') + 1;
                    String leftOverString1 = st.substring(firstCommaIndex);
                    int startingIndex  = leftOverString1.indexOf(' ') + 1;
                    String leftOverString2 = leftOverString1.substring(startingIndex);
                    int endIndex  = leftOverString2.indexOf(' ');
                    String url = st.substring(firstCommaIndex + startingIndex, firstCommaIndex + startingIndex + endIndex);
                    int statusCodeStarting = leftOverString1.indexOf('"') + 2;
                    String leftOverString3 = leftOverString1.substring(statusCodeStarting);
                    int statusCodeEnd  = leftOverString3.indexOf(' ');
                    String status  = st.substring(firstCommaIndex + statusCodeStarting, statusCodeEnd + firstCommaIndex + statusCodeStarting);
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
            // IOException whenever an input or output operation is failed
            System.out.println("IOException Exception Occurced");
            e.printStackTrace();
        }
        return h;
    }

    public static void main(String[] args) {
        String fileName = "input";
        LinkedHashMap<String, Integer> urlCount = urlCount(fileName);
        for (Map.Entry<String, Integer>  entry : urlCount.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
        System.out.println("Status Counting");
        LinkedHashMap<String, List<StatusCount>> statusCount = statusCount(fileName);
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
