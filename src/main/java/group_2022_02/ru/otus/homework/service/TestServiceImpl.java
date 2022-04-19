package group_2022_02.ru.otus.homework.service;

import group_2022_02.ru.otus.homework.dao.ExerciseDao;
import group_2022_02.ru.otus.homework.domain.Exercise;
import group_2022_02.ru.otus.homework.domain.Scores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@PropertySource("classpath:application.yml")
@Service
public class TestServiceImpl implements TestService {

    @Value("${score.excellent}")
    private String ScoreExcellent;

    @Value("${score.good}")
    private String ScoreGood;

    public ExerciseDao exercise;

    public TestServiceImpl(ExerciseDao exercise) {
        this.exercise = exercise;
    }

    public void startTest() {
        System.out.println("Start of testing\n");
        Scanner in = new Scanner(System.in);
        List<Exercise> exercises = exercise.getAllExercises();
        int count = 0;
        for (int i = 0; i < exercises.size(); i++) {
            int number = i + 1;
            System.out.print(String.format("Question number %d: %s\n", number, exercises.get(i).question));
            var answer = in.next();
            if (answer.equals(exercises.get(i).answer)) {
                count++;
            }
        }
        System.out.print("Number of correct answers:" + count);
        System.out.println("\nYour score is " + getScore(count).toString());
    }

    public Scores.ScoresStatus getScore(int count){
        if (Integer.parseInt(ScoreExcellent) == count || count > Integer.parseInt(ScoreGood)){
            return Scores.ScoresStatus.EXCELLENT;
        }
        else if (count == Integer.parseInt(ScoreGood)){
            return Scores.ScoresStatus.GOOD;
        }
        else return Scores.ScoresStatus.SATISFACTORY;
    }
}
