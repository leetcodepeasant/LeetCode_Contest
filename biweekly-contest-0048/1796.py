class Solution:
    def secondHighest(self, s: str) -> int:
        s = set([x for x in s if x in '0123456789'])
        if len(s) <= 1:
            return -1
        return int(sorted(s, reverse=True)[1])