package com.example.springsecurity.test;

import org.aspectj.lang.annotation.Aspect;


@Aspect
public class MainTest {


    //    private String[] strings = new String[]{"A", "B", "C"};
//    private StringBuilder result = new StringBuilder();
//
    public static String print(int[] a) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            builder.append(a[i]);
        }
        return builder.toString();
    }

//    public void binaryString(int i) {
//        if (i == 5) {
//            System.out.println(result);
//            return;
//        }
//        for (String s : strings) {
//            result.append(s);
//            binaryString(i + 1);
//            if (result.length() > 0) {
//                result.deleteCharAt(result.length() - 1); // Xóa ký tự cuối cùng
//            }
//        }
//    }


//    public void binary(int i) {
//        //trường hợp cơ sở, nếu là vị trí cuối cùng thì in cấu hình ra
//        if (i == n) {
//            System.out.println(print(a));
//            return;
//        }
//        for (int j = 0; j <= 1; j++) {
//            a[i] = j;
//            binary(i + 1);
//        }
//    }


    /*
    123

    456
    */
    // sinh tổ hợp con
//    private int k = 3;
//    private int n = 6;
//    private int[] a = new int[k];
//
//    public void hand(int i) {
//        //trường hợp cơ sở
//        if (i == k) {
//            System.out.println(print(a));
//            return;
//        }
//
//        int m;
//        if (i == 0)
//            m = i + 1;
//        else
//            m = a[i - 1] + 1;
//        //gọi đệ quy
//        for (int j = m; j <= n; j++) {
//            a[i] = j;
//            hand(i + 1);
//        }
//    }

    //sinh hoán vị kế tiếp
    int n = 5;
    int a[] = new int[n];

    public void solve(int i) {
        //kết thúc thì in ra cấu hình
        if (i == n) {
            System.out.println(print(a));
            a = new int[n];
            return;
        }
        int m;
        if (i == 0)
            m = i + 1;
        else
            m = a[i - 1] + 1;
        for (int j = m; j <= n; j++) {
            a[i] = j;
            solve(i + 1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i += 10) {
            System.out.println(i);
        }
    }
}