package group_2022_02.ru.otus.homework;

import group_2022_02.ru.otus.homework.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
        TestService service = context.getBean(TestService.class);
        service.startTest();
    }
}
