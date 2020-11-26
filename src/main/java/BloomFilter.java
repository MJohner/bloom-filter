import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import java.nio.charset.Charset;

public class BloomFilter {

    private int elements_n;
    private double errorProb_p;
    private int filterSize_m;
    private int hashFunctions_k;
    private boolean[] bitVec;
    private HashFunction[] hf;
    private int nrOfElements;

    public BloomFilter(int n, double p){
        this.elements_n = n;
        this.errorProb_p = p;
        // http://matthias.vallentin.net/blog/2011/06/a-garden-variety-of-bloom-filters/
        filterSize_m =  (int) (-1 * Math.round((n * Math.log(p)) / (Math.pow(Math.log(2), 2))));
        hashFunctions_k = (int) Math.round(filterSize_m / n * Math.log(2));
        bitVec = new boolean[filterSize_m];
        hf = new HashFunction[hashFunctions_k];
        for(int i = 0; i < hashFunctions_k; i++){
            hf[i] = Hashing.murmur3_128(i);
        }
    }

    public void add(String s){
        for(int i = 0; i < hashFunctions_k; i++){
            int hashValue = Math.abs(hf[i].hashString(s, Charset.forName("UTF-8")).asInt())%filterSize_m;
            bitVec[hashValue] = true;
            nrOfElements++;
        }
        System.out.println();

    }

    public void add(String[] arr){
        for (String s : arr) {
            add(s);
        }
    }

    public boolean contains(String s){
        for(int i = 0; i < hashFunctions_k; i++){
            int hashValue = Math.abs(hf[i].hashString(s, Charset.forName("UTF-8")).asInt())%filterSize_m;
            if(!bitVec[hashValue]){
                return false;
            }
        }
        return true;
    }

    public void printSpecs(){
        System.out.println("Expected number of elements: " + elements_n);
        System.out.println("Declared error tolerance: " + errorProb_p);
        System.out.println("Number of bits in filter vector: " + filterSize_m);
        System.out.println("Number of hash functions: " + hashFunctions_k);
        System.out.println("Number of elements in filter: " + nrOfElements);
    }
}
