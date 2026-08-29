
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store indices
        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        // Sort indices according to nums values
        Arrays.sort(indices, (a, b) -> nums[a] - nums[b]);

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            int j = i + 1;

            // Find one group
            while (j < n &&
                   nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            // Get original indices of this group
            Integer[] group = Arrays.copyOfRange(indices, i, j);

            // Sort original indices
            Arrays.sort(group);

            // Put sorted values into sorted original positions
            for (int k = 0; k < group.length; k++) {
                ans[group[k]] = nums[indices[i + k]];
            }

            i = j;
        }

        return ans;
    }
}