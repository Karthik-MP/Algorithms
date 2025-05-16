class Solution(object):
    def mergeAlternately(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: str
        """
        lst = []
        j, i = 0, 0
        while i < len(word1):
            if j >= len(word2):
                break    
            lst.append(word1[i])
            lst.append(word2[j])
            i +=1
            j += 1
            print(lst)
        for i in range(i, len(word1)):
            lst.append(word1[i])

        for j in range(j, len(word2)):
            lst.append(word2[j])
        
        result = "".join(lst)    
        return result
sol = Solution()
print(sol.mergeAlternately("abc", "pqr"))