# 215. Kth Largest Element in an Array

Topic: Array  
Difficulty: Medium  
Status: Reference solution (to attempt)  
Source: [LeetCode 215](https://leetcode.com/problems/kth-largest-element-in-an-array/)

LeetCode submission: **Accepted** ([submission 2168422111](https://leetcode.com/problems/kth-largest-element-in-an-array/submissions/2168422111/)), 47/47 test cases, Java, October 10, 2026 at 21:53 IST. The automation submitted this reference; the status stays unchanged until you code it yourself.

## Problem

Find the value occupying position `k` when an integer array is ordered from largest to smallest. Repeated values occupy separate positions.

Constraints: `1 <= k <= nums.length <= 100000`; each value is between `-10000` and `10000`.

| Array | k | Answer |
| --- | --- | --- |
| `[3,2,1,5,6,4]` | 2 | 5 |
| `[3,2,3,1,2,4,5,5,6]` | 4 | 4 |

Workbook provenance: LeetCoded row 8 is labelled `Find the "Kth" max and min element of an array`. Its hyperlink targets this problem, which asks only for the kth largest value. This solution follows that linked statement.

## Brute force: repeatedly select the maximum

Scan every unused position to find the largest remaining occurrence. Mark that position used, and repeat k times. The last chosen value is the answer. Tracking positions keeps duplicate values separate and leaves the input unchanged.

Time: **O(nk)**, reaching O(n²) when k = n. Auxiliary space: **O(n)** for the used-position flags. The returned integer takes O(1) output space.

## Optimal general selection: randomized three-way quickselect

The kth largest value has ascending zero-based index `n - k`. Choose a random pivot and rearrange the current range into values smaller than it, equal to it, and larger than it. If the target index lies in the equal block, return the pivot. Otherwise keep only the side containing that index and repeat.

Partitioning puts each region in its correct relative position, so discarded regions cannot contain the target. Grouping equal values avoids repeatedly processing duplicates. Random pivots give expected linear work; an unusually poor sequence of pivots can still take quadratic time.

Time: **O(n) expected, O(n²) worst case**. Auxiliary space: **O(1)** with an iterative loop and in-place swaps. Output space: O(1). This approach changes the order of `nums`; clone it first if order must be preserved, adding O(n) space. A frequency array is another option under the bounded value range, requiring O(n + R) time and O(R) space, where R is the number of possible values.

## Pattern to remember

For one order statistic, partition and keep only the relevant side. Convert kth largest to index `n - k`, and keep duplicate values together.

## Edge cases and validation

Check a single element, k = 1, k = n, negative values, duplicates, all equal values, and sorted inputs. Both methods assume the stated valid-input constraints.

Local validation: **10,327 cases passed**, including the official examples; boundary and duplicate cases; deterministic generated arrays checked for every k against an independent sorting oracle; optimal-method checks at the 100000-element limit. Brute-force input preservation was also verified. Java 21 compilation covers the repository solution and the separate judge-compatible submission. Both official examples passed in LeetCode before submission.
