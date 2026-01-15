public class MissingNumber {
    void main() {
    IO.println(missingNumber(new int[]{3, 0, 1}));
    IO.println(missingNumber(new int[]{0, 1}));
    IO.println(missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

//    public int missingNumber(int[] nums) {
//        if (nums.length == 1) return 0;
//        java.util.Arrays.sort(nums);
//
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i + 1] != nums[i] + 1) return nums[i] + 1;
//        }
//        return nums[nums.length - 1] + 1;
//    }

    /**
     * takes the real and imaginary sum, then
     * subtracts to find the missing number...*/
    public int missingNumber(int[] nums) {
        int largestNum = 0, real = 0, imaginary = 0;
        for (int i : nums) {
            real += i;
        }

        for (int i : nums) {
            if (i > largestNum) largestNum = i;
        }

        for (int i = 0; i < largestNum; i++) {
            imaginary++;
        }

        imaginary = nums.length * (nums.length + 1) / 2;
        return imaginary - real;
    }
}