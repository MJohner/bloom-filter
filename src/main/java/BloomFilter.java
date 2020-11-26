public class BloomFilter {

    private int elements_n;
    private double errorProb_p;
    private int filterSize_m;
    private int hashFunctions_k;

    public BloomFilter(int n, double p, int m, int k){
        this.elements_n = n;
        this.errorProb_p = p;
        this.filterSize_m = m;
        this.hashFunctions_k = k;

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
}
