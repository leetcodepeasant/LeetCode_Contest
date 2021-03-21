class Solution:
    def maxAscendingSum(self, nums: List[int]) -> int:
        N = len(nums)
        ans = nums[0]
        cur = nums[0]
        i = 1
        while i < N:
            if nums[i - 1] < nums[i]:
                cur += nums[i]
            else:
                ans = max(ans, cur)
                cur = nums[i]
            i += 1
        ans = max(ans, cur)
        return ans