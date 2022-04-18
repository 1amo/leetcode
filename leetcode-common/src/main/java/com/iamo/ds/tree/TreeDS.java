package com.iamo.ds.tree;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ Author：    qiu ji xing
 * @ Date：      2022/4/15 17:57
 * @ Description： N叉树结构 （个人定义漏掉的后面补充）
 */
@Data
public class TreeDS {

    public static TreeDS build() {
        return new TreeDS();
    }
    private TreeDS(){

    }

    public TreeDS(String val) {
        this.parent = null;
        this.val = val;
        this.chlidNodes = null;
    }
    public TreeDS(TreeDS parent ,String val){
        this.parent = parent;
        this.val = val;
        this.chlidNodes = null;
    }
    public  TreeDS(TreeDS parent,String val ,List<TreeDS> chlidNodes){
        this.parent = parent;
        this.val = val;
        this.chlidNodes = chlidNodes;
    }

    //节点值
    private String val;
    //父类节点
    private TreeDS parent;
    //子节点
    private List<TreeDS> chlidNodes;

}
