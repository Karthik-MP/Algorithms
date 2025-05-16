class Solution(object):
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        low=0
        high=len(nums)-1
        position=-1
        while(low<=high):
            mid=(low+high)//2
            # print(mid)
            if(target==nums[mid]):
               return mid
            elif(target>nums[mid]):
                low=mid+1
            elif(target<nums[mid]):
                high = mid-1
        return low
sol= Solution()
print(sol.searchInsert([1,3,5,8], 5))