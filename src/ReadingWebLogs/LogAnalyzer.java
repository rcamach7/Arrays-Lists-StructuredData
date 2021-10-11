package src.ReadingWebLogs;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
     
     // In the LogAnalyzer class, write the method iPsWithMostVisitsOnDay, which has two parameters—the first one is a 
     // HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses 
     // that occurred on that day, and the second parameter is a String representing a day in the format “MMM DD” described above. 
     // This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day. 
     // For example, if you use the file weblog3-short_log, and the parameter for the day is “Sep 30”, 
     // then there are two IP addresses in the ArrayList returned: 61.15.121.171 and 177.4.40.87. 
     // Hint: This method should call another method you have written.
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> myMap, String day) {
    	 ArrayList<String> answer = new ArrayList<String>();
    	 HashMap<String, Integer> ipToCountMap = new HashMap<String, Integer>();
    	 
    	 // we are already given a list of days with all IPs that accesed including repeats. We are also given the day we want to extract from that list. 
    	 for (String key : myMap.keySet()) {
    		 if (key.equals(day)) {
    			 // answer = myMap.get(key); // this is giving us the unique IPs only
    			 for (String ip : myMap.get(key) ) {
    				 if (!ipToCountMap.containsKey(ip)) {
    					 ipToCountMap.put(ip, 1);
    				 } else {
    					 ipToCountMap.put(ip, ipToCountMap.get(ip) + 1);
    				 }
    			 }
    		 }
    	 } 
    	 
    	 int maxCount = mostNumberVisitsByIP(ipToCountMap);
    	 for (String key : ipToCountMap.keySet()) {
    		 if (ipToCountMap.get(key) == maxCount) {
    			 answer.add(key);
    		 }
    	 }
    	 return answer;
     }

     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> myMap) {
    	 String answer = "";
    	 int currentMax = 0;
    	 for (String day : myMap.keySet()) {
    		 int currentSize = myMap.get(day).size();
    		 if (currentSize > currentMax) {
    			 currentMax = currentSize;
    			 answer = day;
    		 }
    	 }
    	 return answer;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>(); 
    	 
    	 for (LogEntry log : records) {
    		 String currentDayOfLog = log.getAccessTime().toString().substring(4, 10);
    		 
    		 if (!myMap.containsKey(currentDayOfLog)) {
    			 ArrayList<String> uniqueIpsOnGivenDay = new ArrayList<String>();
    			 uniqueIpsOnGivenDay.add(log.getIpAddress());
    			 myMap.put(currentDayOfLog, uniqueIpsOnGivenDay);
    		 } else {
    			 ArrayList<String> pullCurrent = myMap.get(currentDayOfLog);
    			 pullCurrent.add(log.getIpAddress());
    			 myMap.put(currentDayOfLog, pullCurrent);
    		 }
    	 }
    	 return myMap;
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someDay) {
    	 ArrayList<String> uniqueVisits = new ArrayList<String>();
    	 for (LogEntry log : records) {
    		 String dateOfLog = log.getAccessTime().toString();
    		 String dateExtract = dateOfLog.substring(4, 10);
    		 
    		 if (dateExtract.equals(someDay) && !uniqueVisits.contains(log.getIpAddress()) ) {
    			 uniqueVisits.add(log.getIpAddress());
    		 }
    	 }
    	 return uniqueVisits;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> myMap) {
    	 // This method returns an ArrayList of Strings of IP addresses that all have the maximum number of visits to this website. 
    	 ArrayList<String> mostVisits = new ArrayList<String>();
    	 int mostNumberVisitsByIP = mostNumberVisitsByIP(myMap);
    	 
    	 for (String key : myMap.keySet()) {
    		 int currentVisits = myMap.get(key);
    		 if (mostNumberVisitsByIP == currentVisits) {
    			 mostVisits.add(key);
    		 }
    	 }
    	 return mostVisits;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> myMap) {
    	 int count = 0;
    	 for (String key : myMap.keySet()) {
    		 if (myMap.get(key) > count) {
    			 count = myMap.get(key); 
    		 }
    	 }
    	 return count;
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
    	 HashMap<String, Integer> unique = new HashMap<String, Integer>();
    	 for (LogEntry log : records) {
    		 String currentIP = log.getIpAddress();
    		 if (!unique.containsKey(currentIP)) {
    			 unique.put(currentIP, 1);
    		 } else {
    			 unique.put(currentIP, unique.get(currentIP) + 1);
    		 }
    	 }
    	 return unique;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 int count = 0;
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 
    	 for (LogEntry log : records) {
    		 String currentIP = log.getIpAddress();
    		 boolean isInRange = false;
    		 if (log.getStatusCode() >= low && log.getStatusCode() <= high) {
    			 isInRange = true;
    		 }
    		 if (!uniqueIPs.contains(currentIP) && isInRange) {
    			 uniqueIPs.add(currentIP);
    			 count += 1;
    		 }
    	 }
    	 return count;
     }

     
     public void printAllHigherThanNum(int num) {
    	 System.out.println("These are the Logs that are greater than " + num);
    	 for (LogEntry log : records) {
    		 int currentStatusCode = log.getStatusCode();
    		 if (currentStatusCode > num) {
    			 System.out.println(log.toString());
    		 }
    	 }
     }
     
     public int countUniqueIPs() {
    	 ArrayList<String> uniqueIPs = new ArrayList<String>();
    	 
    	 for (LogEntry log : records) {
    		 String currentIP = log.getIpAddress();
    		 if (!uniqueIPs.contains(currentIP)) {
    			 uniqueIPs.add(currentIP);
    		 }
    	 }
    	 return uniqueIPs.size();
     }
     
     public void readFile(String filename) {
         FileResource resource = new FileResource(filename);
         for (String line : resource.lines()) {
        	 records.add(WebLogParser.parseEntry(line)); 
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }  
}
