class Solution(object):

    def plusOne(self, digits):
        """
        :type digits: List[int]
        :rtype: List[int]
        """
        print("income di",digits)
        if (digits[-1]<9):
            digits[-1] = digits[-1]+1
            print("returning di",digits)
            return digits
        else:
            print("before",digits)
            if len(digits) > 1:
                digits = self.plusOne(digits[:len(digits)-1])
                digits.append(0)
            else:
                digits = [1, 0]
            return digits

        print(digits)
sol= Solution()
print(sol.plusOne([9,9,9,9]))