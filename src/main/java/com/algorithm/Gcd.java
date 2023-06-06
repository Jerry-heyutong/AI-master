package com.algorithm;

/**
 * @author 何裕彤
 * 算法: 第一章
 * 欧几里得算法
 * 计算两个非负整数p和q的最大公约数;
 * 若q是0，则最大公约数为p。否则,将p除以q得到余数r，p和q最大公约数即为q和r的最大公约数。
 */
public class Gcd {
    /**
     * 计算两个非负整数p和q的最大公约数;
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q) {
        //若q是0，则最大公约数为p。
        if (q == 0) {
            return p;
        }else{
            //否则,将p除以q得到余数r，p和q最大公约数即为q和r的最大公约数。
            int r = p % q;
            System.out.println("p="+p +",q="+q+ ",r="+r);
            return gcd(q,r);
        }
    }

    /**
     * 测试:给出使用欧几里得算法计算105和24的最大公约数的过程中得到的一系列p和q的值。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(gcd(105,24));

        //计算1 111 111 和 1 234 567 的最大公因数
        System.out.println(gcd(105,72));

        System.out.println("test1d".replaceAll("\\D*",""));


    }


}
