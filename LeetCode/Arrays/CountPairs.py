class Solution:
    def countKDifference(self, nums, k) :
        d=dict()
        for i in nums:
            if i in d:
                d[i]+=1
            else:
                d[i]=1
        c=0
        print(d)
        for key,v in d.items():
            if key+k in d:
                print(d[k+key])
                c+=d[key]*d[k+key]
        return c
sol = Solution()
print(sol.countKDifference([1, 1, 2, 2], 1))