class Solution(object):
    def canPlaceFlowers(self, flowerbed, n):
        """
        :type flowerbed: List[int]
        :type n: int
        :rtype: bool
        """
        for i in range(len(flowerbed)):
            if(flowerbed[i] == 0 and (i == 0 or flowerbed[i-1] == 0) and (len(flowerbed)-1 == i or flowerbed[i+1] == 0)):
                flowerbed[i] = 1
                n -=1
        if n <= 0:
            return True
        else:
            return False

print(Solution().canPlaceFlowers([0,0,0,0,0], 6))