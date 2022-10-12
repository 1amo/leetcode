package com.iamo.build;

import com.iamo.common.CommonConstant;
import com.iamo.ds.tree.BinaryTreeDS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ Author：    qiu ji xing
 * @ Date：      2022/4/15 9:40
 * @ Description： 结构构建工具类
 */
public class BuildUtils {

    /**
     * 序列化二叉树  层次遍历（BFS）输出
     *
     * @Param: root 需要序列化的二叉树结构
     * @Return: 序列化数据 (没带括号)
     */
    public static String binaryTreeSerialize(final BinaryTreeDS root) {
        if (root == null) {
            return "";
        }
        //定义结果集，运用可变字符串
        StringBuilder result = new StringBuilder();
        //初始化队列,添加二叉树入队
        Queue<BinaryTreeDS> queue = new LinkedList() {{
            add(root);
        }};
        while (!queue.isEmpty()) {
            //获取出队数据
            BinaryTreeDS node = queue.poll();
            //当前节点不为空就进队，为空就添加NONE/NULL,自己定义
            if (node != null) {
                result.append(node.getVal() + CommonConstant.NODE_SPLIT);
                //将当前节点的子节点入队,注意先左后右
                queue.add(node.getLeft());
                queue.add(node.getRight());
            } else {
                result.append(CommonConstant.NODE_NULL + CommonConstant.NODE_SPLIT);
            }

        }
        //去掉最后的符号
        result.deleteCharAt(result.length() - 1);
        //标准化数据
        String res = formatBinaryTree(result.toString());
        return res;
    }

    /**
     * 反序列化树 输入内容是层次遍历的结果集（BFS）
     *
     * @Param: path 二叉树层次遍历的序列化路径（BFS）
     * @Return: 树结构
     */
    public static BinaryTreeDS binaryTreeDeserialize(final String path) {
        //过滤掉无效数据
        if (path == null || path.length() < 0 || path.equals(CommonConstant.NODE_NULL)) {
            return null;
        }
        //初始化二叉树
        final BinaryTreeDS binaryTree = new BinaryTreeDS();
        //拆分路径
        String[] paths = path.split(CommonConstant.NODE_SPLIT);
        //定义队列，并赋初始值
        binaryTree.setVal(paths[0]);
        Queue<BinaryTreeDS> queue = new LinkedList<BinaryTreeDS>() {{
            add(binaryTree);
        }};
        int cumsum = 0;
        while (!queue.isEmpty()) {
            //获取出队数据
            BinaryTreeDS node = queue.poll();
            //防止下标越界,如果是标准数据可以不用添加，由于是完整的二叉树，所以输入的数据节点至少为3
            if (paths.length-1 <= cumsum) {
                break;
            }
            //由于层次遍历源数据，因此先左后右存入队列
            if (!paths[++cumsum].equals(CommonConstant.NODE_NULL)) {
                //补充左节点数据
                node.setLeft(new BinaryTreeDS(paths[cumsum]));
                queue.add(node.getLeft());
            }
            //存入右侧数据
            if (!paths[++cumsum].equals(CommonConstant.NODE_NULL)) {
                //补充右节点数据
                node.setRight(new BinaryTreeDS(paths[cumsum]));
                queue.add(node.getRight());
            }
        }

        return binaryTree;
    }

    /**
     * 标准化数据源，生产完整二叉树结构
     *
     * @Param: path     树路径
     * @Return: 格式化树路径
     */
    public static String formatBinaryTree(String path) {
        if (path != null && path.length() > 0 && path.split(CommonConstant.NODE_SPLIT).length % 2 == 0) {
            return path+CommonConstant.NODE_SPLIT+CommonConstant.NODE_NULL;
        }
        return path;
    }
}
