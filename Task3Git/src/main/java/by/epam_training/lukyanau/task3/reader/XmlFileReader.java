package by.epam_training.lukyanau.task3.reader;

import by.epam_training.lukyanau.task3.exception.ProjectException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class XmlFileReader {
    public List<String> getFileData(String path) throws ProjectException {
        Path file = Paths.get(path);
        List<String> lines;
        try {
            lines = Files.readAllLines(file);
        } catch (IOException exp) {
            throw new ProjectException("Error while opening file:" + file.toString(), exp);
        }
        return lines;
    }

}
