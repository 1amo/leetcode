package com.iamo.tree.binarytree;

import com.iamo.build.BulidUtils;
import com.iamo.ds.tree.BinaryTreeDS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ Author：    qiu ji xing
 * @ Date：      2022/4/14 10:51
 * @ Description：   力扣二叉树问题集
 *
 * <pre>
 *       个人理解：
 *          树的遍历是根据根节点的维度来理解的，
 *            1.如果是先遍历根节点就是先序遍历。根-左-右
 *            2.如果是中间遍历根节点就是中序遍历。左-根-右
 *            3.如果是最后遍历根结点就是后序遍历。左-右-根
 *          当然还有一个层次遍历，是根据树的的高度（深度）从左到右逐层遍历
 *       实现方式：
 *          树的遍历的实现大致分为两种：递归/非递归
 *          具体操作：百度多的是。
 * </pre>
 */
public class Solution {

    public static Solution bulid(){
        return new Solution();
    }

    public static void main(String[] args) {
        //测试源数据
        String root = "1,2,3,NULL,NULL,4,5";
        //转换成树结构
        BinaryTreeDS binaryTreeDS = BulidUtils.binaryTreeDeserialize(root);
        System.out.println(binaryTreeDS);
        List<Integer> integers = Solution.bulid().inorderTraversal(binaryTreeDS);
        System.out.println(integers);

    }

    /**
     * <p>二叉树的中序遍历 (给定一个二叉树的根节点 root ，返回 它的 中序 遍历)
     * <pre>
     *    样例：
     *       输入：root = [1,null,2,3]
     *       输出：[1,3,2]
     *   </pre>
     * 地址： {@link String <tt> https://leetcode-cn.com/problems/binary-tree-inorder-traversal/</tt>}
     *
     * @Param: root 二叉树根节点
     * @Return: 中序遍历
     */
    public List<Integer> inorderTraversal(BinaryTreeDS root) {
        //递归方式实现很简单但效率不高，这里通过非递归完成



        //中序遍历 左-根-右
        //1.第一种通过栈的方式迭代完成，同递归差不多，算是个显式的栈运用
        List<Integer> integers = new Solution() {
            @Override
            public List<Integer> inorderTraversal(BinaryTreeDS root) {
                System.out.println("懒得重新写个方法，就用匿名内部类实现");
                //返回的结果集
                List<Integer> result = new ArrayList<Integer>();
                //初始化构建栈结构
                Stack<BinaryTreeDS> stack = new Stack<BinaryTreeDS>();
                //当源数据为空并且栈中没有数据的时候跳出
                while (root != null || !stack.isEmpty()) {
                    //root源数据不为空，进行进栈操作，当跳出循环时，说明栈顶存放的是最左边子树的节点
                    while (root != null) {
                        //将数据存入栈中
                        stack.push(root);
                        //左边遍历
                        root = root.getLeft();
                    }
                    //取出栈顶数据
                    root = stack.pop();
                    //将当前值存放如结果集
                    result.add(root.getVal());
                    //迭代当前节点的右子树
                    root = root.getRight();

                }

                return result;
            }

        }.inorderTraversal(root);


        return integers;

    }
}
