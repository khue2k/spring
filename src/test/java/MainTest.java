
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class MainTest {
    @Test
    void test() throws JsonProcessingException {
    }


    public static void main(String[] args) {
        System.out.println(new Date());
    }
}
