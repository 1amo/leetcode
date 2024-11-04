package com.iamo.binarysearch;

import com.iamo.utils.SearchUtil;

/**
 * @author qiu ji xing
 * @description 二分查找法
 * @date 2024/11/4 17:37:48
 */

public class BinarySearch {

    /**
     * 测试二分查找
     * @param
     * @return
     */

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,3,4,5};

        int i = SearchUtil.binarySearch(arr, 6);
        System.out.println(i);
    }
}
