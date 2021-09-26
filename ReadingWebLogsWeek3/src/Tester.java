import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("weblog1_log");
        //analyze.printAll();
        
        //System.out.println("Total number of unique IP addresses is: " + analyze.countUniqueIPs())

        //analyze.printAllHigherThanNum(400);
        
        //ArrayList<String> spy = new ArrayList<String>();
        //spy = analyze.uniqueIPVisitsOnDay("Mar 24");
        //System.out.println(spy.size());
        
        //System.out.println(analyze.countUniqueIPsInRange(300, 399));
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tester app = new Tester();
		app.testLogAnalyzer();
	}
}
