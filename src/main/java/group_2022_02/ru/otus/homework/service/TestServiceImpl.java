package group_2022_02.ru.otus.homework.service;

import group_2022_02.ru.otus.homework.dao.ExerciseDao;
import group_2022_02.ru.otus.homework.domain.Exercise;
import group_2022_02.ru.otus.homework.domain.Scores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@ShellComponent
@PropertySource("classpath:application.yml")
@Service
public class TestServiceImpl implements TestService {

    @Value("${score.excellent}")
    private String ScoreExcellent;

    @Value("${score.good}")
    private String ScoreGood;

    public ExerciseDao exercise;

    public int result;

    public TestServiceImpl(ExerciseDao exercise) {
        this.exercise = exercise;
    }

    @ShellMethod(value = "Starting testing", key = {"s", "start", "restart"})
    public void startTest() {
        System.out.println("Start of testing\n");
        List<Exercise> exercises = exercise.getAllExercises();
        result = 0;
        for (int i = 0; i < exercises.size(); i++) {
            int number = i + 1;
            System.out.print(String.format("Question number %d: %s\n", number, exercises.get(i).question));
            if (getAnswer().equals(exercises.get(i).answer)) {
                result++;
            }
        }
    }

    public String  getAnswer(){
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    @ShellMethod(value = "Show my result", key = {"r", "result"})
    public void getResult(){
        System.out.print("Number of correct answers:" + result);
        System.out.println("\nYour score is " + getScore(result).toString());
        exercise.getQuestions();
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
