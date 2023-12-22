package com.example.springsecurity.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.aspectj.lang.annotation.Aspect;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
//    int n = 5;
//    int a[] = new int[n];
//
//    public void solve(int i) {
//        //kết thúc thì in ra cấu hình
//        if (i == n) {
//            System.out.println(print(a));
//            a = new int[n];
//            return;
//        }
//        int m;
//        if (i == 0)
//            m = i + 1;
//        else
//            m = a[i - 1] + 1;
//        for (int j = m; j <= n; j++) {
//            a[i] = j;
//            solve(i + 1);
//        }
//    }

    List<String> solve(List<String> list) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String s : list) {
            if (!map.containsKey(s)) {
                map.put(s, 0);
                result.add(s);
            } else {
                Integer currentAppear = map.get(s);
                map.put(s, ++currentAppear);
                result.add(s + "_" + currentAppear);
            }
        }
        return result;
    }

    public static <K, V> Map<V, K> reverseMap(Map<K, V> originalMap) {
        Map<V, K> reversedMap = new HashMap<>();
        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey());
        }
        return reversedMap;
    }

    public static boolean isValidArrayString(String input) {
        String regex = "\\[\"[^\"]+\"(,\"[^\"]+\")*\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static List<String> splitLineCSVHasArray(String line) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[[^\\[\\]]*\\]|[^,]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String matched = matcher.group();
            if (matched.startsWith("[") && matched.endsWith("]")) {
                result.add(matched);
            } else {
                String[] splitValues = matched.split("");
                for (String value : splitValues) {
                    result.add(value.trim());
                }
            }
        }
        return result;
    }

    //Sinh hoán vị kế tiếp
    public static void nextPermutation(int n) {


    }

    //sinh hoán vị kế tiếp
//    int n = 3;
//    int a[] = new int[n];
//    int d[] = new int[n + 1];
//
//    void Try(int i) {
//        for (int j = 1; j <= n; j++) {
//            if (d[j] == 0) {
//                a[i] = j;
//                d[j] = 1;
//                if (i == n - 1)
//                    System.out.println(print(a));
//                else
//                    Try(i + 1);
//                d[j] = 0;
//            }
//        }
//    }


    // tìm kiếm nhị phân Binary search
    static int[] a = new int[10];

    void initData(int[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
    }

    void binarySearch(int a[], int x, int l, int r) {
        int mid = (r + l) / 2 + 1;
        if (a[mid] == x) {
            System.out.println(a[mid]);
        } else if (a[mid] < x) {
            binarySearch(a, x, mid + 1, r);
        } else if (x < a[mid]) {
            binarySearch(a, x, l, mid - 1);
        }
    }

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        mainTest.initData(a);
        mainTest.binarySearch(a, 10, 0, a.length - 1);
    }
}