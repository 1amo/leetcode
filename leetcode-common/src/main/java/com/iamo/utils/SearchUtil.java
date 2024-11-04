package com.iamo.utils;

/**
 * @author qiu ji xing
 * @description 查找工具
 * @date 2024/11/4 17:38:55
 */

public class SearchUtil {


    /**
     *
     *  二分查找法查询目标值
     * @param arr 目标数组
     * @param  target 目标值
     * @return 目标值数组下标
     */
    public static int binarySearch(int [] arr,int target){
        // 定义首尾节点下标
        int left = 0 ;
        int right = arr.length - 1;

        while ( left <= right){
            // 获取当前中间节点下标
            int mid = left + ( right - left) / 2;
            if( arr[mid] == target){
                return mid;
            }else if( arr[mid] > target){
                // 在左边
                right = mid -1;
            }else {
                // 在右边
                left = mid + 1;
            }
        }
        return -1;
    }
}
