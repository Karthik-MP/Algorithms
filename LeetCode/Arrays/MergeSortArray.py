class Solution(object):
    def merge(self, nums1, m, nums2, n):
        """
        :type nums1: List[int]
        :type m: int
        :type nums2: List[int]
        :type n: int
        :rtype: None Do not return anything, modify nums1 in-place instead.
        """
        j = 0
        if n == 0:
            return nums1
        elif m == 0:
            nums1 = [x for x in nums2]
            return nums1
        for i in range(len(nums1)):
            if j < len(nums2) and (nums1[i] > nums2[j] or (i>=m and nums1[i] == 0)):
                print(nums1[i]," ", nums2[j])
                # try:
                nums1.insert(i, nums2[j])
                nums1.pop()
                j = j+1

                # except:
                    # print(j)
                    # print(nums2[j])
        return nums1



sol = Solution()
print(sol.merge([-1,0,0,0,3,0,0,0,0,0,0], 5, [-1,-1,0,0,1,2], 6))
# print(sol.merge([1, 2, 3, 0, 0, 0], 3, [2, 5, 6], 3))