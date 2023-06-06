package com.algorithm.util;

import lombok.SneakyThrows;

import java.util.Arrays;

/**
 * @author 何裕彤
 * 算法: 第一章
 * 典型的数组处理代码
 */
public class ArrayUtils {
    /**
     * 找出数组最大的元素
     */
    public static double findMax(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * 计算数组平均值
     */
    public static double avg(double[] arr) {
        double sum = 0.0;
        for (double tmp :
                arr) {
            sum += tmp;
        }
        return sum / arr.length;
    }

    /**
     * 复制数组
     *
     * @param arr
     */
    public static double[] deepCloneArr(double[] arr) {
        double[] cloneArr = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            cloneArr[i] = arr[i];
        }
        return cloneArr;
    }

    /**
     * 复制数组
     *
     * @param arr
     */
    public static double[] arrCopy(double[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    /**
     * 颠倒数组元素的顺序
     *
     * @param arr
     */
    public static double[] reverse(double[] arr){
        int length = arr.length;
        for (int i = 0; i < length/2; i++) {
            double tmp = arr[i];
            arr[i]=arr[length-1-i];
            arr[length-1-i] = tmp;
        }
        return arr;
    }

    /**
     * 矩阵相乘
     * 方阵为 m=n, 普通矩阵为 m!=n
     * 口诀: 前取行后取列，对应相乘再相加
     * a的列要和b的行相等
     * a[][] * b[][] = c[][];
     */
    public static double[][] matrixMultiplication(double[][] a,double[][] b){
        int ma = a.length;//A的行数
        int na = a[0].length;//A的列数
        int nb = b[0].length;//B的列数
        double[][] c = new double[ma][nb];
        for (int i = 0; i < ma; i++) {
            for (int j = 0; j < na; j++) {
                //计算a行和b列的点乘
                for (int l = 0; l < nb; l++) {
                        c[i][l] += a[i][j] * b[j][l];
                }
            }
        }
        return c;
    }

    /**
     * 方阵相乘
     * 方阵为 m=n, 普通矩阵为 m!=n
     * 口诀: 前取行后取列，对应相乘再相加
     * a的列要和b的行相等
     * a[][] * b[][] = c[][];
     */
    public static double[][] squareMatrixMultiplication(double[][] a,double[][] b){
        int m = a.length;//A的行数
        double[][] c = new double[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                //计算a行和b列的点乘
                for (int l = 0; l < m; l++) {
                    c[i][l] += a[i][j] * b[j][l];
                }
            }
        }
        return c;
    }

    @SneakyThrows
    public static void main(String[] args) {
      double[][] a = {{1,2},{3,4}};
      double[][] b = {{4,5},{6,7}};
      double[][] c = squareMatrixMultiplication(a,b);
      for (int i = 0; i < c.length; i++) {
          for (int j = 0; j < c[i].length; j++) {
              System.out.print(c[i][j]+" ");
          }
          System.out.println();
      }
    }

}
