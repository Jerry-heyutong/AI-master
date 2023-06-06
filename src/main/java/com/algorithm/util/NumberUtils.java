package com.algorithm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 何裕彤
 * 算法: 第一章
 * 典型的静态方法实现
 */
public class NumberUtils {
    /**
     * 计算一个整数的绝对值
     *
     * @param number
     * @return
     */
    public static int abs(int number) {
        return number < 0 ? -number : number;
    }

    /**
     * 计算一个浮点数的绝对值
     */
    public static double abs(double number) {
        return number < 0.0 ? -number : number;
    }

    /**
     * 判定一个数是否为素数
     * 循环从2到该数的平方根开始迭代是因为，如果该数不是质数，那么它一定可以分解为两个数的乘积，其中至少一个数小于或等于该数的平方根。
     * 例如，
     * 如果我们要判断数字n是否为质数，如果n不是质数，那么它一定可以写成n = a * b的形式，其中a和b都是大于1的整数。
     * 如果a和b都大于n的平方根，那么它们的乘积将大于n，这与n = a * b矛盾。
     * <p>
     * 循环的迭代次数是从2到该数的平方根，即√n。因此，算法的时间复杂度是O(√n)。
     * 因此，我们只需要在循环中迭代从2到该数的平方根即可，如果该数不能被这些数字整除，那么它一定是质数。这种方法可以减少循环的次数，提高程序的效率
     */
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算平方根
     * 牛顿迭代法
     */
    public static double sqrt(double c) {
        if (c < 0) {
            return Double.NaN;
        }
        double err = 1e-15;
        double t = c;
        while (abs(t - c / t) > err * t) {
            t = (c / t + t) / 2.0;
        }
        return t;
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     * 二叉搜索树的中序遍历
     * 中序遍历左节点
     * 访问根节点
     * 遍历右节点
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root != null) {
            if(root.left!=null){
                result.addAll(inorderTraversal(root.left));
            }
            result.add(root.val);
            if(root.right!=null){
                result.addAll(inorderTraversal(root.right));
            }
        }
        return result;
    }

    /**
     * 二叉搜索树的前序遍历
     * 访问根节点
     * 遍历左树
     * 遍历右树
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if (root != null) {
            result.add(root.val);
            if(root.left!=null){
                result.addAll(preorderTraversal(root.left));
            }
            if(root.right!=null){
                result.addAll(preorderTraversal(root.right));
            }
        }
        return result;
    }

    /**
     * 校验前序遍历二叉搜索树
     *
     * @param preorder
     * @return
     */
    public static boolean verifyPreorder(int[] preorder) {
        if(preorder.length==0){
            return false;
        }
        boolean result = false;
        for(int i = 1 ; i < preorder.length;i++){
            if(preorder[i]<preorder[0]){
                //校验左边树
                int[] leftTree = new int[preorder.length-i];
                for (int j = 0; j < leftTree.length; j++) {
                    leftTree[j] = preorder[j+i];
                }
                result =  verifyPreorder(leftTree);
            }
            if(preorder[i]>preorder[0]){
                //校验右边树
                int[] rightTree = new int[preorder.length-i];
                for (int j = 0; j < rightTree.length; j++) {
                    rightTree[j] = preorder[j+i];
                }
                result =  verifyPreorder(rightTree);
            }

        }
        return result;
    }

    public static void main(String[] args) {
       int[] preorder = {5,2,6,1,3};
        System.out.println(verifyPreorder(preorder));
    }
}
