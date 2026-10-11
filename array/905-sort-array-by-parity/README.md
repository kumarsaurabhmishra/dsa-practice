# 905. Sort Array By Parity

Topic: Array  
Difficulty: Easy  
Status: Reference solution (to attempt)  
Source: [LeetCode 905](https://leetcode.com/problems/sort-array-by-parity/)

LeetCode submission: **Accepted** ([submission 2168902501](https://leetcode.com/problems/sort-array-by-parity/submissions/2168902501/)), 286/286 test cases, Java, October 11, 2026 at 10:05 IST. This automation submission remains a reference solution until you code it yourself.

## Problem

Rearrange an integer array so that every even value precedes every odd value. Preserve all occurrences. Any order within either group is valid.

Constraints: the array contains 1 to 5000 elements; each value is between 0 and 5000. Zero is even.

| Input | One valid output |
| --- | --- |
| `[3,1,2,4]` | `[2,4,3,1]` |
| `[0]` | `[0]` |

Workbook provenance: LeetCoded row 10 is labelled `Move all the negative elements to one side of the array`. Row 38, labelled `Minimum swaps required bring elements less equal K together`, links to the same problem. These labels differ from the linked statement, which groups values by parity and asks neither for sign grouping nor a minimum swap count. This solution follows that statement while leaving both workbook labels unchanged.

## Brute force / straightforward baseline: build a new array

Scan the input once to copy even values, then again to copy odd values into the remaining positions. Each occurrence is copied exactly once. This preserves the input and the relative order within each group.

Time: **O(n)**. Allocated output space: **O(n)**. Auxiliary working space excluding the returned array: **O(1)**. Total additional memory, including that array: **O(n)**.

## Optimal: two-pointer partition in place

Start a pointer at each end. Advance the left pointer past correctly placed even values and move the right pointer past correctly placed odd values. If the pointers have not met, swap the misplaced odd on the left with the misplaced even on the right, then move both inward.

Everything before the left pointer is even, and everything after the right pointer is odd. Each swap extends both finished regions. When the pointers meet or cross, no misplaced pair remains. Every movement reduces the unchecked range, so the total work is linear.

Time: **O(n)** worst case. Auxiliary space: **O(1)**. The returned array is the original input, so there is no new output allocation. This improves memory use over the baseline, while both approaches already have optimal linear time. It mutates the input and does not preserve order within groups. Clone the input first if its original order must remain available, using O(n) additional memory.

## Pattern to remember

Partition by a yes/no condition with two pointers. Skip correctly placed ends, then swap a misplaced pair. A stable ordering is unnecessary when the problem allows any valid arrangement.

## Edge cases and validation

Check a single even or odd value, zero, all even, all odd, duplicates, an already partitioned array, and reversed parity groups. Both methods assume the stated constraints.

Local validation: **22,856 cases passed**. Checks cover both results for even-before-odd ordering and preservation of every occurrence, rather than requiring identical output order. They also verify that the baseline preserves the input, the optimal method returns the same array, and the baseline preserves order within each group. Coverage includes official examples, exhaustive short arrays, seeded randomized inputs, and 5000-element boundary cases. Java 21 compilation also covers the separate judge-compatible optimal submission.
