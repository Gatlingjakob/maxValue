/**
 * Created by Jakob on 28-09-2016.
 */


public class maxValue {
    public static void main(String[] args) {

        int[] myList = {45, 64, 75, 23, 46, 345, 234 ,23 ,1};

        // Create tasks


        Runnable findMax = new SumThread(myList, 0 , 9 );

        // Create threads

        Thread thread1 = new Thread(findMax);

        // Start threads

        thread1.start();



    }
}

/**
 * This thread finds the sum of a subsection of an array.
 */
class SumThread extends Thread {
    private int lo, hi;
    private int[] arr;
    private int ans = 0;

    public SumThread(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public void run() {
        int temp= 0;
        int sum =0;
        for (int i = lo; i < hi; i++) {
            temp = arr[i];
            if (temp > sum) {
                sum = temp;

            }

        }
        System.out.println(sum);
    }

    /**
     * find max in an array.
     *
     * @param arr array to sum
     * @return sum of the array's elements
     * @throws InterruptedException shouldn't happen
     */
    public static int sum(int[] arr) throws InterruptedException {
        int len = arr.length;
        int temp = 0;
        int ans = 0;

        // Create and start 4 threads.
        SumThread[] ts = new SumThread[4];
        for (int i = 0; i < 4; i++) {
            ts[i] = new SumThread(arr, (i * len) / 4, ((i + 1) * len / 4));
            ts[i].start();
        }

        // Wait for the threads to finish and find max.
        for (int i = 0; i < 4; i++) {
            ts[i].join();
            for (int j= 0; j <= len; j++){
                temp = arr[j];
                if (temp > ans){
                    ans=temp;
                }

            }
        }
        return ans;
    }
}

