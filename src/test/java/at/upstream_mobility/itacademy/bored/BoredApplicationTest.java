package at.upstream_mobility.itacademy.bored;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoredApplicationTest {

    @Test
    public void testMain() {
        String[] args = {};
        ConfigurableApplicationContext context = null;
        try {
            context = SpringApplication.run(BoredApplication.class, args);
            assertNotNull(context, "Application context should not be null");
        } finally {
            if (context != null) {
                context.close();
            }
        }
    }
}
