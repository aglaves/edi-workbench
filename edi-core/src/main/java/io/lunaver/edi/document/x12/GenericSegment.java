package io.lunaver.edi.document.x12;

import java.util.ArrayList;
import java.util.List;

public class GenericSegment implements Segment {
    private String elementType;
    private List<String> elements;

    public GenericSegment(String elementType) {
        setElementType(elementType);
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public List<String> getElements() {
        return elements;
    }

    public void setElements(List<String> elements) {
        this.elements = elements;
    }

    public void addElement(String element) {
        if (elements == null)
            elements = new ArrayList<>();
        elements.add(element);
    }
}
