package by.epam_training.lukyanau.task3.printer.impl;

import by.epam_training.lukyanau.task3.entity.Attribute;
import by.epam_training.lukyanau.task3.entity.Node;
import by.epam_training.lukyanau.task3.exception.ProjectException;
import by.epam_training.lukyanau.task3.printer.Printer;

import java.util.List;

public class PrinterImpl implements Printer {
    private static final PrinterImpl instance = new PrinterImpl();

    private PrinterImpl() {

    }

    public static PrinterImpl getInstance() {
        return instance;
    }

    private static final StringBuilder treeInto = new StringBuilder();

    @Override
    public void printInfo(Node node) throws ProjectException {
        if (node == null) {
            throw new ProjectException("Something wrong happened, check your file");
        }
        if (!node.getAttributes().isEmpty()) {
            String attributes = getAttributeInfo(node);
            System.out.println(treeInto + node.getName() + "(" + attributes + ")");
        } else {
            System.out.println(treeInto + node.getName());
        }
        printContent(node);
        printChildInfo(node);

    }

    private String getAttributeInfo(Node node) {
        List<Attribute> attrList = node.getAttributes();
        StringBuilder attributesLine = new StringBuilder();
        for (Attribute attr : attrList) {
            attributesLine.append(" ");
            attributesLine.append(attr.getAttributeString());

        }
        return attributesLine.toString();
    }

    private void printContent(Node node) {
        if (!node.getContent().isEmpty()) {
            System.out.printf("%s->%s%n", treeInto, node.getContent());
        }
    }

    private void printChildInfo(Node node) throws ProjectException {
        if (!node.getChildNodes().isEmpty()) {
            for (Node child : node.getChildNodes()) {
                treeInto.append("   ");
                printInfo(child);
                treeInto.delete(treeInto.length() - 3, treeInto.length());
            }
        }
    }
}
