class Solution {
    public int maxValue(int n, int index, int maxSum) {
        // nums[index] is the peak of a mountain-shaped array; left is arithmetic sequence with common difference 1, right -1.
        // sum(nums) can be represented with a function of nums[index] and it increases when nums[index] increases.
        // Binary search for the target value of nums[index] that meets the requirement of sum(nums) <= maxSum.
        // Time: O(log(maxSum)). Space: O(1).
        int start = 1, end = maxSum;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check(n, index, maxSum, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return check(n, index, maxSum, end) ? end : start;
    }

    private boolean check(int n, int index, long maxSum, int guess) {
        long leftSum = calcSum(index, guess - 1);
        long rightSum = calcSum(n - 1 - index, guess - 1);
        return leftSum + guess + rightSum <= maxSum;
    }

    private long calcSum(int len, long hi) { // sum of arithmetic sequence with hi as the first element and common difference -1
        if (len > hi) { // remaining len - hi elements will be 1
            return (hi + 1) * hi / 2 + (len - hi);
        }
        return (hi + hi - len + 1) * len / 2;
    }
}