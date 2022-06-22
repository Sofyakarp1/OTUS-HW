package group_2022_02.ru.otus.homework;

import group_2022_02.ru.otus.homework.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class);
        MessageSource msg = context.getBean(MessageSource.class);
        TestService service = context.getBean(TestService.class);
        service.startTest();


    }
}

