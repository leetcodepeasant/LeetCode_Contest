class Solution {
    public int maxScore(int[] nums) {
        // Time: O(m^2 * 2^m). Space: O(m^2 + 2^m). m = 2 * n.
        int m = nums.length;
        int[] f = new int[1 << m]; // f[i]: answer for indices 0 to i.
        int[][] g = new int[m][m]; // Pre-compute gcd for nums[i] and nums[j].
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = gcd(nums[i], nums[j]);
            }
        }

        for (int state = 0; state < (1 << m); state++) {
            int cnt = Integer.bitCount(state);
            if ((cnt & 1) != 0) {
                continue;
            }
            int round = cnt / 2 + 1;
            for (int i = 0; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    if ((state & (1 << i)) != 0 || (state & (1 << j)) != 0) {
                        continue;
                    }
                    int nxt = state | (1 << i) | (1 << j);
                    f[nxt] = Math.max(f[nxt], f[state] + g[i][j] * round);
                }
            }
        }

        return f[(1 << m) - 1];
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}