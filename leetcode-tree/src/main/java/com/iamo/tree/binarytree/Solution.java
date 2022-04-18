package com.iamo.tree.binarytree;

import com.iamo.build.BulidUtils;
import com.iamo.common.TreeTraverseMode;
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
        String root = "1,null,2,3";
        String format = BulidUtils.formatBinaryTree(root);
        //转换成树结构
        BinaryTreeDS binaryTreeDS = BulidUtils.binaryTreeDeserialize(format);
        System.out.println(binaryTreeDS);
        List<String> integers = Solution.bulid().inorderTraversal(binaryTreeDS,TreeTraverseMode.POSTORDER_TRAVERSAL);
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
    public List<String> inorderTraversal(BinaryTreeDS root,String mode) {
        //递归方式实现很简单但效率不高，这里通过非递归完成
        List<String> integers;


        /**
         * 先序遍历 根-左-右
         * 中序遍历 左-根-右
         * 后序遍历 左-右-根
         * 1.第一种通过栈的方式迭代完成，同递归差不多，算是个显式的栈运用
         *
         * 时间复杂度：O(n)
         * 空间复杂度：O(h)，h 是树的高度
         * */
        integers = new Solution() {
            @Override
            public List<String> inorderTraversal(BinaryTreeDS root,String mode) {
                System.out.println("懒得重新写个方法，就用匿名内部类实现 迭代方式，显式使用栈");
                //返回的结果集
                List<String> result = new ArrayList<String>();
                //先序
                if(TreeTraverseMode.PREORDER_TRAVERSAL.equals(mode)){
                    //初始化构建栈结构
                    Stack<BinaryTreeDS> stack = new Stack<BinaryTreeDS>();
                    //当源数据为空并且栈中没有数据的时候跳出
                    while (root != null || !stack.isEmpty()) {
                        //root源数据不为空，进行进栈操作，当跳出循环时，说明栈顶存放的是最左边子树的节点
                        while (root != null) {
                            //先存根数据
                            result.add(root.getVal());
                            //将数据存入栈中
                            stack.push(root);
                            //左边遍历
                            root = root.getLeft();
                        }
                        //取出栈顶数据
                        root = stack.pop();
                        //迭代当前节点的右子树
                        root = root.getRight();
                    }
                }

                //中序
                if(TreeTraverseMode.INORDER_TRAVERSAL.equals(mode)){
                    //初始化构建栈结构
                    Stack<BinaryTreeDS> stack = new Stack<BinaryTreeDS>();
                    //当源数据为空并且栈中没有数据的时候跳出
                    while (root != null || !stack.isEmpty()) {
                        //root源数据不为空，进行进栈操作，当跳出循环时，说明栈顶存放的是当前节点最左边子树的节点
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
                }

                //后序
                if(TreeTraverseMode.POSTORDER_TRAVERSAL.equals(mode)){
                    //初始化构建栈结构
                    Stack<BinaryTreeDS> stack = new Stack<BinaryTreeDS>();
                    //后序遍历需要用一个中间变量存储
                    BinaryTreeDS prve = null;

                    //当源数据为空并且栈中没有数据的时候跳出
                    while (root != null || !stack.isEmpty()) {
                        //root源数据不为空，进行进栈操作，当跳出循环时，说明栈顶存放的是当前节点最左边子树的节点
                        while (root != null) {
                            //将数据存入栈中
                            stack.push(root);
                            //左边遍历
                            root = root.getLeft();
                        }
                        root = stack.pop();
                        //当前节点没有子节点，或者当前节点的子节点是处理过的节点（上一次处理过的）
                        if(root.getRight() == null || root.getRight() == prve){
                            result.add(root.getVal());
                            prve = root;
                            root = null;
                        }else {
                            //重新存放进入，向右迭代
                            stack.push(root);
                            root = root.getRight();
                        }

                    }
                }

                System.out.println("栈迭代："+result);
                return result;
            }

        }.inorderTraversal(root,mode);


        /**
         * 2.
         * 用递归和迭代的方式都使用了辅助的空间，而莫里斯遍历的优点是没有使用任何辅助空间。
         * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构。
         *
         * 时间复杂度:找到每个前驱节点的复杂度是 O(n)，因为 n 个节点的二叉树有 n-1条边，每条边只可能使用 2 次(一次定位到节点，一次找到前驱节点)，故时间复杂度为 O(n)
         * 空间复杂度：O(1)
         *
         * */
        integers =  new Solution(){
            @Override
            public List<String> inorderTraversal(BinaryTreeDS root,String mode) {
                System.out.println("懒得重新写个方法，就用匿名内部类实现 莫里斯(Morris)遍历");
                //返回的结果集
                List<String> result = new ArrayList<String>();
                //先序
                if(TreeTraverseMode.PREORDER_TRAVERSAL.equals(mode)){

                }
                //中序
                if(TreeTraverseMode.INORDER_TRAVERSAL.equals(mode)){
                    //定义树结构，用于嫁接节点
                    BinaryTreeDS graft;
                    while (root != null){
                        //判断左子树是否为空，不为空则进行嫁接操作，将当前节点的右子树（包括当前节点）全部嫁接到当前节点下一个左节点的最终右节点上
                        if(root.getLeft() != null){
                            graft = root.getLeft();
                            //迭代当前节点的最终右子树
                            while (graft.getRight() != null){
                                graft = graft.getRight();
                            }
                            //嫁接
                            graft.setRight(root);
                            //迭代下一个,并且清除多余节点
                            BinaryTreeDS left = root;
                            root = root.getLeft();
                            left.setLeft(null);
                        }else {
                            //当迭代都最底部时（已经没有左子树了），打印节点，迭代右边
                            result.add(root.getVal());
                            root = root.getRight();
                        }
                    }

                }

                //后序
                if(TreeTraverseMode.POSTORDER_TRAVERSAL.equals(mode)){

                }

                System.out.println("莫里斯迭代："+result);
                return result;
            }
        }.inorderTraversal(root,mode);



        return integers;

    }


    /**
     * 给你一个整数 n ，
     * 请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。
     * 可以按 任意顺序 返回答案。
     * @Param: n  输入整数
     * @Return: 树结构
     */
    public List<BinaryTreeDS> generateTrees(int n) {


        return null;

    }
}
