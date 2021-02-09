package by.epam_training.lukyanau.task3;

import by.epam_training.lukyanau.task3.exception.ProjectException;
import by.epam_training.lukyanau.task3.printer.impl.PrinterImpl;
import by.epam_training.lukyanau.task3.reader.XmlFileReader;
import by.epam_training.lukyanau.task3.service.impl.XmlServiceImpl;


public class Runner {

    public static final String PATH = "resources/xmlTest.xml";

    public static void main(String[] args) throws ProjectException {
        XmlFileReader reader = new XmlFileReader();
        XmlServiceImpl xmlService = XmlServiceImpl.getInstance();
        PrinterImpl printer = PrinterImpl.getInstance();
        printer.printInfo(xmlService.createTreeStructure(reader.getFileData(PATH)));

    }
}
