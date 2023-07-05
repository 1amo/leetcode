package com.iamo.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author： 邱际兴
 * @date： 2022/11/23 10:37
 * @description： 排列组合工具
 * 参考资料 <a>https://zhuanlan.zhihu.com/p/108033147?from_voters_page=true</a>
 */
public class PermutationUtil {

    /**
     *  排列组合（字符重复排列）
     * @param  list 待排列字符集
     * @param  length 排列生成长度
     * @return 指定长度结果集
     */
    public static List<String> permutation(List<String> list, int length) {
        Stream<String> stream = list.stream();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream().map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }

    /**
     * 排列组合(字符不重复排列)
     * @param  list 待排列字符集
     * @param  length 排列生成长度
     * @return 指定长度结果集
     */
    public static List<String> permutationNoRepeat(List<String> list, int length) {
        // 去重
        Stream<String> stream = list.stream().distinct();
        for (int n = 1; n < length; n++) {
            stream = stream.flatMap(str -> list.stream()
                    .filter(temp -> !str.contains(temp))
                    .map(str::concat));
        }
        return stream.collect(Collectors.toList());
    }
}
