package group_2022_02.ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import group_2022_02.ru.otus.homework.domain.Exercise;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@PropertySource("classpath:application.yml")
@Service
public class ExerciseDaoImpl implements ExerciseDao {

    @Value("${name.file}")
    public String nameFile;

    @Override
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<Exercise>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(String.valueOf(nameFile)));
                CSVReader csvReader = new CSVReader(reader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                exercises.add(new Exercise(StringUtils.substringBeforeLast(nextRecord[0].toString(), ";")
                        , StringUtils.substringAfterLast(nextRecord[0].toString(), ";")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return exercises;
    }

    @Override
    public List<String> getQuestions() {
        List<String> exercises = new ArrayList<String>();
        var messages = ResourceBundle.getBundle("lable");
        for (int i =0; i<5; i++){
            int k = i+1;
            exercises.add(messages.getString("question." + k));
        }
        //var messages = ResourceBundle.getBundle("lable");
        System.out.println(exercises.toString());
        return null;
    }


    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
