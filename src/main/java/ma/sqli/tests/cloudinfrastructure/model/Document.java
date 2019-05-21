package ma.sqli.tests.cloudinfrastructure.model;

import static ma.sqli.tests.cloudinfrastructure.constants.Constants.DOCUMENT_SIZE;

public class Document {
    private String name;
    private double size;


    public Document(String name) {
        this.name = name;
        this.size = DOCUMENT_SIZE;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }
}
