class Solution {
    public int getMaximumConsecutive(int[] coins) {
        // Time: O(nlogn). Space: O(1).
        Arrays.sort(coins);
        int cur = 0; // Currently, can construct how many consecutive integers starting from 1
        for (int coin : coins) {
            if (coin <= cur + 1) {
                cur += coin;
            } else { // If coin >= cur + 2, cannot construct consecutively anymore. Stop.
                break;
            }     
        }
        return cur + 1;
    }
}