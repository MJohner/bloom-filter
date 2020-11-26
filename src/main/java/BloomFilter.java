import java.util.Arrays;

public class BloomFilter {

    private int elements_n;
    private double errorProb_p;
    private int filterSize_m;
    private int hashFunctions_k;
    private boolean[] bitVec;

    public BloomFilter(int n, double p){
        this.elements_n = n;
        this.errorProb_p = p;
        // http://matthias.vallentin.net/blog/2011/06/a-garden-variety-of-bloom-filters/
        filterSize_m =  (int) (-1 * Math.round((n * Math.log(p)) / (Math.pow(Math.log(2), 2))));
        hashFunctions_k = (int) Math.round(filterSize_m / n * Math.log(2));
        bitVec = new boolean[filterSize_m];
    }

    public void add(String s){

    }

    public void add(String[] arr){
        for (String s : arr) {
            add(s);
        }
    }

    public double contains(String s){

        return 0.0;
    }

    public void printSpecs(){
        System.out.println("Expected number of elements: " + elements_n);
        System.out.println("Declared error tolerance: " + errorProb_p);
        System.out.println("Number of bits in filter vector: " + filterSize_m);
        System.out.println("Number of hash functions: " + hashFunctions_k);
        System.out.println("Filter vector: " + Arrays.toString(bitVec));
    }
}
