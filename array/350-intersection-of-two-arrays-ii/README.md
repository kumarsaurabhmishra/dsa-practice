# 350 - Intersection of Two Arrays II

Status: Reference solution (to attempt)

LeetCode submission: Accepted, 61/61 test cases, Java.

[Accepted submission](https://leetcode.com/problems/intersection-of-two-arrays-ii/submissions/2169539161/) on kumarsaurabh_7, October 11, 2026. Automation acceptance keeps this marked as reference work until you code it yourself.

Topic: Array | Difficulty: Easy | Language: Java

Source: [LeetCode 350](https://leetcode.com/problems/intersection-of-two-arrays-ii/)

## Problem

Find the common values in two arrays, keeping repeated occurrences. A value appears in the answer the smaller number of times it occurs in either input. The answer may use any order.

Both arrays have 1 to 1000 elements, and values range from 0 to 1000 inclusive.

Examples: `[1,2,2,1]` and `[2,2]` produce `[2,2]`. `[4,9,5]` and `[9,4,9,8,4]` produce `[4,9]`.

Workbook row 11 is labelled "Find the Union and Intersection of the two arrays." Its hyperlink targets this problem, which asks for intersection with duplicates. This solution follows that link. The original workbook label is preserved.

## Brute force

For each value in the larger array, search for one equal, unused occurrence in the smaller array. Mark that position used and stop searching for this occurrence. Without the used flags, one occurrence could be counted repeatedly.

Time: O(n*m) worst case. Auxiliary space: O(min(n,m)) for flags and a temporary result buffer, plus O(k) for the returned array. Neither input changes.

## Optimal for the stated constraints

Use two arrays of 1001 counters, one for each input. For every possible value, the number of matches is the smaller of its two counts. Sum these matches to allocate the exact result size, then write those copies into it. Direct indexing gives predictable linear work without searching or sorting the inputs.

Time: O(n+m+U), where U=1001, with O(k) output writing included since k <= min(n,m). For this fixed value range, time is O(n+m). Auxiliary space: O(U), constant for the stated constraints, plus O(k) for the returned result. Neither input changes. Output is sorted, which is allowed.

The counters rely on the stated 0..1000 range. For arbitrary integers, use frequency maps instead. If both inputs are already sorted, scan them with two pointers in O(n+m) time and O(1) auxiliary space excluding output. When one input is much smaller, counting that input in a map saves memory. If the other input is on disk, stream its values and consume those counts without loading the whole file.

## Key takeaway

Intersection with duplicates uses **minimum frequency**, not simple membership. A set would lose repeated matches.

## Edge cases and validation

Checked unequal duplicate counts, zero and 1000, disjoint arrays, single elements, identical arrays, and maximum lengths. Java 21 local validation passed 15,406 cases, including every pair of arrays of lengths 1..4 over values 0..2 and 1000 deterministic random pairs. Both approaches and the entry point matched an independent frequency oracle and preserved their inputs. The judge-only Java source compiled separately.
