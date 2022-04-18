package com.iamo.ds.tree;

import lombok.Data;

/**
 * @ Author：    qiu ji xing
 * @ Date：      2022/4/14 10:45
 * @ Description： 二叉树结构 （个人定义漏掉的后面补充）
 */
@Data
public class BinaryTreeDS {

    private String val;
    private BinaryTreeDS left;
    private BinaryTreeDS right;

    public BinaryTreeDS() {
    }

    public BinaryTreeDS(String val) {
        this.val = val;
    }

    public BinaryTreeDS(String val, BinaryTreeDS left, BinaryTreeDS right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }



}
