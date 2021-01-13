package by.epam_training.lukyanau.task3.service.impl;

import by.epam_training.lukyanau.task3.entity.Attribute;
import by.epam_training.lukyanau.task3.entity.Node;
import by.epam_training.lukyanau.task3.service.XmlService;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlServiceImpl implements XmlService {
    private static final String OPEN_TAG_REGEX = "<[^<\\/?>]+>";
    private static final String CLOSE_TAG_REGEX = "<\\/[^<?>]+>";
    private static final String ATTRIBUTE_TAG_REGEX = "<[^<?>]+['\"]>";
    private static final String ANY_WORD_CHARACTER_REGEX = "\\w";

    //ask
    private static final Pattern openTagPattern = Pattern.compile(OPEN_TAG_REGEX);
    private static final Pattern closeTagPattern = Pattern.compile(CLOSE_TAG_REGEX);
    private static final Pattern attributeTagPattern = Pattern.compile(ATTRIBUTE_TAG_REGEX);
    private static final Pattern consistOfAnyWordCharacter = Pattern.compile(ANY_WORD_CHARACTER_REGEX);

    private static final XmlServiceImpl instance = new XmlServiceImpl();

    private XmlServiceImpl() {

    }

    public static XmlServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Node createTreeStructure(List<String> lines) {
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        for (String line : lines) {
            String content = findContentInLine(line).replaceAll("    ","");
            boolean isAttribute = false;
            //attribute if
            Matcher matcher = attributeTagPattern.matcher(line);
            if (matcher.find()) {
                nodes.push(createNodeWithAttribute(matcher.group().replaceAll("[<>]", "")));
                isAttribute = true;
            }
            //without attribute if
            Matcher matcher1 = openTagPattern.matcher(line);
            if (matcher1.find() && !isAttribute) {
                nodes.push(createNodeWithOutAttribute(matcher1.group().replaceAll("[<>]", "")));
            }
            //add content
            Matcher matcher2 = consistOfAnyWordCharacter.matcher(content);
            if (matcher2.find() && !content.matches("(.*)?xml(.*)")) {
               //System.out.println(content);
                nodes.peek().addContent(content);
            }
            //close tag if
            Matcher matcher3 = closeTagPattern.matcher(line);
            if (matcher3.find()) {
                Node childElement = nodes.pop();
                if (nodes.size() != 0) {
                    nodes.peek().addChildNode(childElement);
                } else {
                    return childElement;
                }
            }
        }

        return null;
    }

    private Node createNodeWithAttribute(String line) {
        List<Attribute> resultAttributes = new ArrayList<>();
        String[] splitAttributes;
        splitAttributes = line.split(" ");
        for (int counter = 1; counter < splitAttributes.length; counter++) {
            String[] nameValueOfAttribute = splitAttributes[counter].split("=");
            resultAttributes.add(new Attribute(nameValueOfAttribute[0], nameValueOfAttribute[1].replaceAll("\"", "")));
        }
        return new Node(splitAttributes[0], resultAttributes);
    }

    private Node createNodeWithOutAttribute(String line) {
        return new Node(line);
    }

    private String findContentInLine(String line) {
        StringBuilder content = new StringBuilder(line);
        Matcher matcher = closeTagPattern.matcher(line);
        if (matcher.find()) {
            content.delete(content.lastIndexOf("</"), content.length());
        }
        Matcher matcher1 = openTagPattern.matcher(content);
        if (matcher1.find()) {
            String finalContent = content.substring(line.indexOf(">") + 1);
            return finalContent;
        }
        String finalContent = content.toString();
        return finalContent;
    }

    //Create Attribute method
    //Create other methods to simplify class

}
