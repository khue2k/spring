package com.example.springsecurity.test;

import lombok.Data;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
class Review {

    private int point;
    private String review;

    // constructor, getters and setters
}

class Rating {
    private double point;
    private List<Review> reviews;

    public void add(Review review) {
        reviews.add(review);
    }

    private double computeRating() {
        int totalPoint = reviews.stream().map(Review::getPoint).reduce(0, Integer::sum);
        this.point = Math.round((totalPoint / reviews.size()) * 100.0) / 100.0;
        return this.point;
    }

    public Rating(Review review1, Review review2) {

    }
}

public class MainTest {
    public static void main(String[] args) throws ScriptException {
        // Khởi tạo một đối tượng ScriptEngine
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        // Mã JavaScript bạn muốn thực thi
        String jsCode = "function myFun(){\n" +
                "    return\"khue\"\n" +
                "}\n" +
                "myFun()";

        // Thực thi mã JavaScript
        Object result = engine.eval(jsCode);

        // In kết quả
        System.out.println(result); // Kết quả sẽ là 30 (tổng của x và y trong mã JavaScript)
    }

}
