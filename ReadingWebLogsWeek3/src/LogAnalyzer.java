import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
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
