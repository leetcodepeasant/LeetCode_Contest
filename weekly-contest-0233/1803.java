class Solution {

    private static class Trie {
        Trie[] next = new Trie[2];
        int cnt = 1;
    }
    
    public int countPairs(int[] nums, int low, int high) {
        return solve(nums, low - 1) - solve(nums, high);
    }
    
    private int solve(int[] nums, int target) { // returns number of nice pairs whose xor > target
        int maxIndex = 16; // log2(2*10^4) ~ 14.3
        Trie trie = buildTrie(nums, maxIndex); 
        int ans = 0;
        for (int num : nums) {
            ans += queryTrie(trie, num, target, maxIndex);
        }
        return ans / 2;
    }

    private Trie buildTrie(int[] nums, int maxIndex) {
        Trie trie = new Trie();
        for (int num : nums) {
            Trie curr = trie;
            for (int j = maxIndex; j >= 0; j--) {
                int digit = (num >> j) & 1;
                if (curr.next[digit] == null) {
                    curr.next[digit] = new Trie();
                } else {
                    curr.next[digit].cnt++;
                }
                curr = curr.next[digit];
            }
        }
        return trie;
    }

    private int queryTrie(Trie trie, int num, int target, int index) {
        if (trie == null) {
            return 0;
        }
        Trie curr = trie;
        for (int i = index; i >= 0; i--) {
            int nDigit = (num >> i) & 1;
            int tDigit = (target >> i) & 1;
            if (tDigit == 1) {
                if (curr.next[nDigit == 1 ? 0 : 1] == null) {
                    return 0;
                }
                curr = curr.next[nDigit == 1 ? 0 : 1];
            } else {
                int p = queryTrie(curr.next[nDigit], num, target, i - 1);
                int q = curr.next[nDigit == 1 ? 0 : 1] == null ? 0 : curr.next[nDigit == 1 ? 0 : 1].cnt;
                return p + q;
            }
        }
        return 0;
    }
}