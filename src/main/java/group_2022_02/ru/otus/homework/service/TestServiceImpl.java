package group_2022_02.ru.otus.homework.service;

import group_2022_02.ru.otus.homework.dao.ExerciseDao;
import group_2022_02.ru.otus.homework.domain.Exercise;

import java.util.List;
import java.util.Scanner;

public class TestServiceImpl implements TestService {

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
            String answer = in.next();
            if (answer.equals(exercises.get(i).answer)) {
                count++;
            }
        }
        System.out.print("Number of correct answers:" + count);
    }
}
