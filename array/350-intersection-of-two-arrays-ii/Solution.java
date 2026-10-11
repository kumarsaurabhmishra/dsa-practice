import java.util.Arrays;

class Solution {
    // LeetCode entry point. Neither approach modifies either input array.
    public int[] intersect(int[] nums1, int[] nums2) {
        return optimal(nums1, nums2);
    }

    // Match each occurrence once. O(n*m) time, O(min(n,m)) auxiliary space.
    public int[] bruteForce(int[] nums1, int[] nums2) {
        int[] smaller = nums1.length <= nums2.length ? nums1 : nums2;
        int[] larger = nums1.length <= nums2.length ? nums2 : nums1;
        boolean[] used = new boolean[smaller.length];
        int[] matches = new int[smaller.length];
        int size = 0;
        for (int value : larger) {
            for (int j = 0; j < smaller.length; j++) {
                if (!used[j] && smaller[j] == value) {
                    used[j] = true;
                    matches[size++] = value;
                    break; // This occurrence cannot match a second occurrence.
                }
            }
        }
        return Arrays.copyOf(matches, size);
    }

    // The stated value range is 0..1000. Direct counts avoid repeated searches.
    // O(n+m+U) time and O(U) auxiliary space, U=1001; result uses O(k).
    public int[] optimal(int[] nums1, int[] nums2) {
        final int VALUE_COUNT = 1001;
        int[] first = new int[VALUE_COUNT];
        int[] second = new int[VALUE_COUNT];
        for (int value : nums1) first[value]++;
        for (int value : nums2) second[value]++;

        int size = 0;
        for (int value = 0; value < VALUE_COUNT; value++) {
            size += Math.min(first[value], second[value]);
        }
        int[] result = new int[size];
        int index = 0;
        for (int value = 0; value < VALUE_COUNT; value++) {
            int copies = Math.min(first[value], second[value]);
            while (copies-- > 0) result[index++] = value;
        }
        return result; // Sorted output is valid because any order is allowed.
    }
}
