class Solution:
    def getNumberOfBacklogOrders(self, orders: List[List[int]]) -> int:
        MOD = 10**9 + 7
        ans = 0
        buy_q = list()
        sell_q = list()
        
        for price, amount, order_type in orders:
            if order_type == 0: # buy
                while amount > 0 and sell_q and sell_q[0][0] <= price:
                    sell_price, sell_amount = heapq.heappop(sell_q)
                    if sell_amount > amount:
                        heapq.heappush(sell_q, [sell_price, sell_amount - amount])
                    amount -= sell_amount
                if amount > 0:
                    heapq.heappush(buy_q, [-price, amount])  
            else: # sell
                while amount > 0 and buy_q and -buy_q[0][0] >= price:
                    buy_price, buy_amount = heapq.heappop(buy_q)
                    if buy_amount > amount:
                        heapq.heappush(buy_q, [buy_price, buy_amount - amount])
                    amount -= buy_amount
                if amount > 0:
                    heapq.heappush(sell_q, [price, amount])
                    
        for _, amount in buy_q:
            ans = (ans + amount) % MOD
        for _, amount in sell_q:
            ans = (ans + amount) % MOD
        
        return ans % MOD