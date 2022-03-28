package group_2022_02.ru.otus.homework.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import group_2022_02.ru.otus.homework.domain.Exercise;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {

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

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
