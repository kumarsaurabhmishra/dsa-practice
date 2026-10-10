import java.util.concurrent.ThreadLocalRandom;

class Solution {
    // LeetCode entry point. Quickselect rearranges nums in place.
    public int findKthLargest(int[] nums, int k) {
        return optimal(nums, k);
    }

    // Pick the largest unused occurrence k times. Duplicates count separately.
    // Time: O(n * k). Auxiliary space: O(n). Does not modify nums.
    public int bruteForce(int[] nums, int k) {
        boolean[] used = new boolean[nums.length];
        int answer = 0;
        for (int rank = 0; rank < k; rank++) {
            int best = -1;
            for (int i = 0; i < nums.length; i++) {
                if (!used[i] && (best == -1 || nums[i] > nums[best])) {
                    best = i;
                }
            }
            used[best] = true;
            answer = nums[best];
        }
        return answer;
    }

    // Randomized three-way quickselect. Expected O(n), worst-case O(n^2).
    // Auxiliary space: O(1); iterative, with no recursion or array copy.
    public int optimal(int[] nums, int k) {
        int target = nums.length - k; // Zero-based index in ascending order.
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int pivot = nums[ThreadLocalRandom.current().nextInt(left, right + 1)];
            int less = left;
            int scan = left;
            int greater = right;

            // [left, less) < pivot, [less, scan) == pivot,
            // [scan, greater] unclassified, (greater, right] > pivot.
            while (scan <= greater) {
                if (nums[scan] < pivot) {
                    swap(nums, less++, scan++);
                } else if (nums[scan] > pivot) {
                    swap(nums, scan, greater--);
                    // The value swapped in has not been classified yet.
                } else {
                    scan++;
                }
            }

            if (target < less) {
                right = less - 1;
            } else if (target > greater) {
                left = greater + 1;
            } else {
                return pivot; // Target lies in the block of equal values.
            }
        }
        throw new IllegalArgumentException("k must be between 1 and nums.length");
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
