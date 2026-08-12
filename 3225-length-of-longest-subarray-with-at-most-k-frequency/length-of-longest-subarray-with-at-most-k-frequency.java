class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int left=0;
        int max=0;
        for(int right=0;right<nums.length;right++){
            int num=nums[right];
            map.put(num,map.getOrDefault(num,0)+1);
            while(map.get(num)>k){
                int leftnum=nums[left];
                map.put(leftnum,map.get(leftnum)-1);
                left++;
            }
            max=Math.max(max,right-left+1);
        }
        return max;
    }
}