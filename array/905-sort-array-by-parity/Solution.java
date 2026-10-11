class Solution {
    // LeetCode entry point: rearranges and returns the original array.
    public int[] sortArrayByParity(int[] nums) {
        return optimal(nums);
    }

    // Straightforward baseline: collect evens, then odds, in a fresh array.
    // O(n) time, O(n) allocated output, O(1) other working space.
    // Does not modify nums and preserves order inside each parity group.
    public int[] bruteForce(int[] nums) {
        int[] result = new int[nums.length];
        int next = 0;
        for (int value : nums) {
            if (value % 2 == 0) {
                result[next++] = value;
            }
        }
        for (int value : nums) {
            if (value % 2 != 0) {
                result[next++] = value;
            }
        }
        return result;
    }

    // Two-pointer partition: O(n) time and O(1) extra space.
    // Order within either group can change; the problem permits this.
    public int[] optimal(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // Everything before left is even; everything after right is odd.
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }
            while (left < right && nums[right] % 2 != 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
