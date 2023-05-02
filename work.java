import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class work{



public class CSVReader {
    
    public static LinkedList<LinkedList<String>> ReadFile(String filename) {
        LinkedList<LinkedList<String>> records = new LinkedList<LinkedList<String>>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(new LinkedList<String>(Arrays.asList(values)));
            }
            return records;
        }
        catch(Exception e){
            return null;
        }
    }
}

    static String WelcomeFunction(){

        int answer = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome! Please make your choice: ");

        System.out.println("1 - weather.csv");
        System.out.println("2 - iris.csv");
        System.out.println("3 - restaurant.csv");
        answer = sc.nextInt();
        
        while(answer < 1 || answer > 3){
            System.out.println("Please select a valid option.");
            System.out.println("1 - weather.csv");
            System.out.println("2 - iris.csv");
            System.out.println("3 - restaurant.csv");
            answer = sc.nextInt();
        }
    
        switch(answer){
            case 1: return "data/weather.csv";
            case 2: return "data/iris.csv";
            case 3: return "data/restaurant.csv";
        }

        return null;
    }

    public static void main(String args[]){
    System.out.println("boas");
    
 

    String filename = WelcomeFunction();
    LinkedList<LinkedList<String>> records = CSVReader.ReadFile(filename);
    LinkedList<String> attributes = records.get(0);

    
    System.out.println(attributes);
    attributes.removeFirst();
    attributes.removeLast();
    System.out.println(attributes);
    
}
}
