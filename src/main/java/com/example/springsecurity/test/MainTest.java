package com.example.springsecurity.test;

import com.example.springsecurity.dtos.UserDTO;
import com.example.springsecurity.reflection.ExcelColumn;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Executor;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainTest {

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

    //tìm kiếm nhị phân

    //solution 1
//    public int binarySearch(int a[], int x) {
//        int l = 0;
//        int r = a.length - 1;
//        while (l <= r) {
//            int mid = l + (r - l) / 2;
//            if (a[mid] == x) {
//                return a[mid];
//            } else if (a[mid] > x) {
//                r = mid - 1;
//            } else if (a[mid] < x) {
//                l = mid + 1;
//            }
//        }
//        return -1;
//    }
    //solution 2

//    public void binarySearch(int a[], int l, int r, int x) {
//        int mid = l + (r - l) / 2;
//        if (l > r) {
//            System.out.println(-1);
//            return;
//        }
//        if (a[mid] == x) {
//            System.out.println(x);
//        } else if (a[mid] < x) {
//            binarySearch(a, mid + 1, r, x);
//        } else if (a[mid] > x) {
//            binarySearch(a, l, mid - 1, x);
//        }
//    }


    // bài toán phân tích số tiền
//    private int[] b = new int[10];
//    private int sum = 0;
//
//    public void print(int[] b) {
//        System.out.println("[" + Arrays.stream(b).mapToObj(operand -> new String(String.valueOf(operand))).reduce((s, s2) -> s.concat("," + s2)).get() + "]");
//    }
//
//    public void Try(int i, int a[], int s) {
//        for (int j = 0; j < a.length; j++) {
//            b[i] = a[j];
//            sum += a[j];
//            if (sum >= s) {
//                if (sum == s)
//                    print(b);
//            } else
//                Try(i + 1, a, s);
//            sum = 0;
//        }
//    }
//


    public int maxArea(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int l = j - i;
                result = Math.max(result, h * l);
            }
        }
        return result;

    }

    //contest 2
    public int fibonacci(int n) {
        int[] a = new int[n];
        a[0] = 1;
        a[1] = 1;
        for (int i = 2; i < n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        return 0;
    }

    public void print(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void solve(int a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            a[i] = a[i] + a[i + 1];
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (date == null)
            return null;
        return dateFormat.format(date);
    }


    public static String convert(long size) {
        long b = 1;
        long kb = 1024;
        long mb = 1024 * 1024;
        long gb = 1024 * 1024 * 1024;
        double result = 0;
        if (size > gb) {
            result = (double) size / gb;
            return String.format("%.2f", result) + " GB";
        } else if (size >= mb && size < gb) {
            result = (double) size / mb;
            return String.format("%.2f", result) + " MB";
        } else if (size >= kb && size < mb) {
            result = (double) size / kb;
            return String.format("%.2f", result) + " KB";
        } else if (size >= b && size < kb) {
            result = (double) size / b;
            return String.format("%.2f", result) + " B";
        }
        return null;
    }

    public static String getTimeAgo(long seconds) {
        if (seconds < 60) {
            return seconds + " s ago";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + " m ago";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + " h ago";
        } else if (seconds < 2592000) {
            long days = seconds / 86400;
            return days + " d ago";
        } else if (seconds < 31536000) {
            long months = seconds / 2592000;
            return months + " M ago";
        } else {
            long years = seconds / 31536000;
            return years + " y ago";
        }
    }


    class Person implements Serializable {
        @ExcelColumn
        private String id;
        @ExcelColumn(index = 1)
        private String name;
        @ExcelColumn(index = 2)
        private int age;
        @ExcelColumn(index = 3)
        public String address;
    }

    public static void main(String[] args) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("songlhecole@gmail.com");
        userDTO.setLastName("khue1234");
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\ADMIN\\Documents\\spring\\src\\test\\test.data");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(userDTO);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}