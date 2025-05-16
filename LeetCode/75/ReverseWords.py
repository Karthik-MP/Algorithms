class Solution(object):
    def reverseWords(self, s):
        """
        :type s: str
        :rtype: str
        """
        lst = str(s).strip().split(" ")
        result = []
        for i in range(len(lst)-1, -1,  -1):
            if(lst[i] != ""):
                result.append(lst[i])
        print(result)
        return " ".join(result)
sol = Solution()
print(sol.reverseWords("a good   example"))