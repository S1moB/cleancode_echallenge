package ma.sqli.tests.cloudinfrastructure.model;

import static ma.sqli.tests.cloudinfrastructure.constants.Constants.KEY_VALUE_SEPARATOR;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.SIZE_START_POINT;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.STORE_EMPTY_DOCUMENT;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.STORE_SEPARATOR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Store {
    private String name;
    private List<Document> documents;

    public Store(String name,String... documents)
    {
        this.name = name;
        this.documents = new ArrayList(Arrays.asList(documents));
    }
    public Store(String name)
    {
        this.name = name;
        this.documents = new ArrayList<>();
    }
    public String getName()
    {
        return name;
    }
    public void addDocuments(String... documents)
    {
        for (String document : documents) {
            this.documents.add(new Document(document));
        }
    }
    public void clearDocuments()
    {
        this.documents.clear();
    }

    public double memoryUsage()
    {
        double memory = SIZE_START_POINT;
        for(Document document : documents)
            memory+= document.getSize();
        return memory;
    }
    public boolean checkIfExist(String name)
    {
        return this.name.equalsIgnoreCase(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Store store = (Store) o;
        return Objects.equals(name, store.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        if(documents.isEmpty())
            return name+KEY_VALUE_SEPARATOR+STORE_EMPTY_DOCUMENT;
        else
            return name+KEY_VALUE_SEPARATOR+String.join(STORE_SEPARATOR,documents.stream().map(d-> d.getName()).collect(Collectors.toList()));
    }
}