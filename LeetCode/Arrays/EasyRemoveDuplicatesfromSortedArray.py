class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        noDuplicates = 0
        alreadyVisited = []
        i = 0
        while i < len(nums):
            if nums[i] in alreadyVisited:
                nums.remove(nums[i])  # Remove the duplicate
            else:
                alreadyVisited.append(nums[i])
                i += 1  # Only increment i if no element was removed
    
        for i in range(noDuplicates):
            nums.append("_")
        return len(nums)-noDuplicates
sol= Solution()
print(sol.removeDuplicates([0,0,1,1,1,2,2,3,3,4]))