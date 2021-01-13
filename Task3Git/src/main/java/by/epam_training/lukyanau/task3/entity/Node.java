package by.epam_training.lukyanau.task3.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Attribute> attributes = new ArrayList<>();
    private List<Node> childNodes = new ArrayList<>();
    private String content = "";

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content += content + " ";
    }


    public void addChildNode(Node childNode) {
        this.childNodes.add(childNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (name != null ? !name.equals(node.name) : node.name != null) return false;
        if (attributes != null ? !attributes.equals(node.attributes) : node.attributes != null) return false;
        if (childNodes != null ? !childNodes.equals(node.childNodes) : node.childNodes != null) return false;
        return content != null ? content.equals(node.content) : node.content == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (childNodes != null ? childNodes.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\n" + name +
                "\nattributes = " + attributes +
                "\nchildNodes = " + childNodes +
                "\ncontent = '" + content + '\'';
    }
}
