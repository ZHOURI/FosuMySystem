package cn.honeyjam.dorm;

import org.junit.Test;

public class Mytest {
    @Test
    public void largestnumsber()
    {
        int[] nums = {10,2};
        int t;
        for(int i =0;i<nums.length-1;i++)
        {
            for(int j=0;j<nums.length-1-i;j++)
            {
                String a=String.valueOf(nums[j]);
                String b=String.valueOf(nums[j+1]);
                int len = a.length()<=b.length()?a.length():b.length();
                for(int k =0;k<len;k++)
                {
                    if(a.charAt(k)<b.charAt(k))
                    {
                        t= nums[j];
                        nums[j]=nums[j+1];
                        nums[j+1] = t;
                        break;
                    }
                    else if(a.charAt(k)==b.charAt(k))
                    {
                        continue;
                    }
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =0;i<nums.length;i++)
        {
            stringBuffer.append(String.valueOf(nums[i]));
        }
        System.out.println(stringBuffer);
    }
}
