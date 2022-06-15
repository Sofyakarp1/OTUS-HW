package group_2022_02.ru.otus.homework.service;

import group_2022_02.ru.otus.homework.domain.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testing service of testing students")
@SpringBootTest
public class TestServiceImplTest {

    @Autowired
    TestServiceImpl testService;

    @DisplayName("Get score GOOD")
    @Test
    void shouldGetScoreGOOD() {
        assertThat(testService.getScore(3)).isEqualTo(Scores.ScoresStatus.GOOD);
    }
}
