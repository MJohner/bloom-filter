public class Main {

    public static void main(String[] args){
        BloomFilter b = new BloomFilter(60000, 0.01);

        b.add("Test");
        b.printSpecs();

    }
}
