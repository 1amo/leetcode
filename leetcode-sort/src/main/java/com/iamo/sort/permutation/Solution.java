package com.iamo.sort.permutation;
import com.iamo.utils.PermutationUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author： 邱际兴
 * @date： 2022/11/23 10:36
 * @description： 排列组合
 */
public class Solution {
    public static void main(String[] args) {
        //在此编写你的代码

        List<String> list = new ArrayList<>();
        list.add("山姆");
        list.add("我");
        list.add("是");
        List<String> result =  PermutationUtil.permutationNoRepeat(list,3);
        for(int i = 0 ; i < result.size();i ++){
            System.out.println(result.get(i));
        }

    }
}

