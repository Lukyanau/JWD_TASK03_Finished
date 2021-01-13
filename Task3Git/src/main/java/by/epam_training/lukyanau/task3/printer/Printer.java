package by.epam_training.lukyanau.task3.printer;

import by.epam_training.lukyanau.task3.entity.Node;
import by.epam_training.lukyanau.task3.exception.ProjectException;

public interface Printer {
    void printInfo(Node node) throws ProjectException;
}
