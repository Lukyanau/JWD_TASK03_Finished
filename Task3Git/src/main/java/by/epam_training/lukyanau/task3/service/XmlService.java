package by.epam_training.lukyanau.task3.service;

import by.epam_training.lukyanau.task3.entity.Node;

import java.util.List;
import java.util.regex.Pattern;

public interface XmlService {
     static final String OPEN_TAG_REGEX = "<[^<\\/?>]+>";
     static final String CLOSE_TAG_REGEX = "<\\/[^<?>]+>";
     static final String ATTRIBUTE_TAG_REGEX = "<[^<?>]+['\"]>";
     static final String ANY_WORD_CHARACTER_REGEX = "\\w";

    //ask
     static final Pattern openTagPattern = Pattern.compile(OPEN_TAG_REGEX);
     static final Pattern closeTagPattern = Pattern.compile(CLOSE_TAG_REGEX);
     static final Pattern attributeTagPattern = Pattern.compile(ATTRIBUTE_TAG_REGEX);
     static final Pattern consistOfAnyWordCharacter = Pattern.compile(ANY_WORD_CHARACTER_REGEX);
    Node createTreeStructure(List<String> lines);
}
