class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        i=0
        while i < len(nums):
            if nums[i] == val:
                nums.remove(nums[i])  # Remove the duplicate
            else: i += 1
        return nums
sol= Solution()
print(sol.removeElement([3,2,2,3], 3))