class AuthenticationManager:

    def __init__(self, timeToLive: int):
        self.ttl = timeToLive
        self.et = dict() # expire time

    def generate(self, tokenId: str, currentTime: int) -> None:
        self.et[tokenId] = currentTime + self.ttl

    def renew(self, tokenId: str, currentTime: int) -> None:
        if tokenId not in self.et:
            return
        if self.et[tokenId] <= currentTime:
            del self.et[tokenId]
            return 
        self.et[tokenId] = currentTime + self.ttl

    def countUnexpiredTokens(self, currentTime: int) -> int:
        for tokenId in list(self.et.keys()):
            if self.et[tokenId] <= currentTime:
                del self.et[tokenId]
        return len(self.et)




# Your AuthenticationManager object will be instantiated and called as such:
# obj = AuthenticationManager(timeToLive)
# obj.generate(tokenId,currentTime)
# obj.renew(tokenId,currentTime)
# param_3 = obj.countUnexpiredTokens(currentTime)