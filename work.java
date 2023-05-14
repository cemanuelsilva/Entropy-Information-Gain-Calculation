import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class work{
    
    class Node {
        
        String attribute;
        String value;
        HashMap<String, Node> children;

        Node(String attribute, String value) {
            this.attribute = attribute;
            this.value = value;
            children = new HashMap<>();
        }
    }

    public class decisionTree {
        
        static Node tree;


        // ENTROPY SUPPORT CALCULATIONS //
    
        public static double log2(double N) {
            double result = (Math.log(N) / Math.log(2));
            return result;
        }

        private static double Entropy(double count1, double count2) {
            if(count1 == 0 || count2 == 0) return 0;
        
            double total = count1 + count2;
            double p1 = count1 / total;
            double p2 = count2 / total;
            return -(p1 * log2(p1) + p2 * log2(p2));
        }

        static double calculateExamples(LinkedList<String> examples){

            int countYes = 0, countNo = 0;

            for(int i = 1; i < examples.size(); i++){
                if(examples.get(i).equals("no")){
                    countNo++;
                }
                else{
                    countYes++;
                }
            }
            return Entropy(countYes, countNo);

        }

        // TODO FUNCTION TO CHANGE EXAMPLES TO A INT;
        static double CalculateGain(HashMap<String, Double> inputs, HashMap<String, Double> totals, LinkedList<String> examples){

            double gain = 0;
            double totalInfo = examples.size() - 1;
            
            double atributeMain = calculateExamples(examples);

            for (Map.Entry<String, Double> entry : totals.entrySet()) {
        
                String key = entry.getKey();
                double total = entry.getValue();
                double input = inputs.get(key);
        
                gain += (total / totalInfo) * input;
            }

            System.out.println("Atributo: " + atributeMain);
            gain = atributeMain - gain;
        
            return gain;
        }

        static double CalculateAtrributeEntropy(LinkedList<String> inputs, LinkedList<String> examples){

            HashMap<String, Double> resultsAtrributes = new HashMap<>();
            HashMap<String, Double> verifyTotal = new HashMap<>();


            double value = 0.0;
            double size = inputs.size() - 1;

            // Get No's and save on the HashMap, save the total count of occurences and save on the HashMap.

            for(int i = 1; i < inputs.size(); i++){
                if(!resultsAtrributes.containsKey(inputs.get(i))){
                    resultsAtrributes.put(inputs.get(i), 0.0);
                }

                if (examples.get(i).equals("no")) {
                    String attributeValue = inputs.get(i);
                    double count = resultsAtrributes.get(attributeValue) + 1.0;
                    resultsAtrributes.put(attributeValue, count);
                }

                if(!verifyTotal.containsKey(inputs.get(i))){
                    verifyTotal.put(inputs.get(i), 1.0);
                }
                else{
                    
                    String attributeValue = inputs.get(i);
                    double count = verifyTotal.get(attributeValue) + 1.0;
                    verifyTotal.put(attributeValue, count);
                }
            }

            

            HashMap<String, Double> finalResultsAtrributes = new HashMap<>(); 

            // Calculations of Entropy for each SubSet-Attribute and save on the HashMap.

            for (Map.Entry<String, Double> entry : resultsAtrributes.entrySet()) {
                String attributeValue = entry.getKey();
                double count = entry.getValue();
                double total = verifyTotal.get(attributeValue);
                double countYes = total - count;
                value = Entropy(countYes, count);
                finalResultsAtrributes.put(attributeValue,value);
            }
            
            // CalculateGain

            double gain = 0;
            gain = CalculateGain(finalResultsAtrributes, verifyTotal, examples);

            System.out.println("Input :" + inputs);
            System.out.println("Exemplos :" + examples);
            System.out.println("Hash Resultados: " + resultsAtrributes);
            System.out.println("Hash Final / Entropia: " + finalResultsAtrributes);
            System.out.println("Verify Total :" + verifyTotal);
            System.out.println("Gain :" + gain);
            
            return gain;
        }

        static HashMap CalculateEntropy(LinkedList<LinkedList<String>> input, LinkedList<String> examples){
            
        
            //CalculateAtrributeEntropy(inputs, examples);
            int size = input.size();  //Get size
            double gain = 0;
            int row = input.get(0).size();                
            double EntropyResult = 0;
            double result = 0;
            HashMap<String,Double> AtrributesFinal = new HashMap<>();   // save entropy of each atrributte
            
            LinkedList<String> inputs = new LinkedList<>();
            
            for(int i = 1; i < row-1; i++){
                for(int j = 0; j < size; j++){
                    inputs.add(input.get(j).get(i));
                    //System.out.println(input.get(j).get(i));
                }
                
                gain = CalculateAtrributeEntropy(inputs, examples);
                System.out.println("\n========================");
                AtrributesFinal.put(inputs.get(0), gain);
                inputs.clear();
                
            }
            
            System.out.println("Gain Final Hash: " + AtrributesFinal);
            
            return AtrributesFinal;
        }

        public static int[] binData(double[] data, int numBins) {
            int[] bins = new int[data.length];
        
            // Find the min and max values of the data
            double minVal = Double.MAX_VALUE;
            double maxVal = Double.MIN_VALUE;
            for (double val : data) {
                if (val < minVal) {
                    minVal = val;
                }
                if (val > maxVal) {
                    maxVal = val;
                }
            }
        
            // Calculate the bin width
            double binWidth = (maxVal - minVal) / numBins;
        
            // Bin the data into the appropriate bin
            for (int i = 0; i < data.length; i++) {
                double val = data[i];
                int binIndex = (int) ((val - minVal) / binWidth);
                if (binIndex == numBins) {
                    binIndex--;
                }
                bins[i] = binIndex;
            }
        
            return bins;
        }
        

        static Node ID3(LinkedList<String> Examples, LinkedList<String> Target_atrribute, Atributtes){
            return node;
        }

        // -------------------------------------- //

}


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
    //System.out.println("boas");
    
 

    String filename = WelcomeFunction();
    LinkedList<LinkedList<String>> records = CSVReader.ReadFile(filename);
    LinkedList<String> examples = new LinkedList<>();
    //System.out.println(examples);

    

    int size = records.size();  //Get size
    int row = records.get(0).size();  

    for(int i = row - 1; i < row; i++){
                
        for(int j = 0; j < size; j++){
            examples.add(records.get(j).get(i));
            //System.out.println(input.get(j).get(i));
        }
    }

    ///System.out.println("Boas: " + examples);
    
    //System.out.println(attributes);
    //attributes.removeFirst();
    //attributes.removeLast();
    //System.out.println(attributes);
    
    decisionTree.CalculateEntropy(records, examples);
    //ID3;
    
    /* 
    double[] data = {85, 80, 83, 70, 68, 65, 64, 72, 69, 75, 75, 72, 81, 71};

    int[] bins = decisionTree.binData(data, 3);

    System.out.println("Bins:");
    System.out.println(Arrays.toString(bins));
    */
}
}
