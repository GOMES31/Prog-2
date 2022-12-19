public class Main {
    public static void main (String[]args){

    }
    public static int pivotIndex(int[] nums) {
       if(nums.length == 0) return -1;
       if(nums.length == 1) {
           return nums[0] == 0 ? 0 : -1;
       }
       int totalSum = 0;
       for(int num: nums) {
           totalSum += num;
       }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }

            leftSum += nums[i];
        }
        return -1;
    }
}
