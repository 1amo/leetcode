package com.iamo.ds.tree;

import lombok.Data;

/**
 * @ Author：    qiu ji xing
 * @ Date：      2022/4/14 10:45
 * @ Description： 二叉树结构
 */
@Data
public class BinaryTreeDS {

    int val;
    BinaryTreeDS left;
    BinaryTreeDS right;

    public BinaryTreeDS() {
    }

    public BinaryTreeDS(int val) {
        this.val = val;
    }

    public BinaryTreeDS(int val, BinaryTreeDS left, BinaryTreeDS right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }



}
