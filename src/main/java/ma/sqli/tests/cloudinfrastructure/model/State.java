package ma.sqli.tests.cloudinfrastructure.model;

public enum State {
    INACTIVE("inactive"),RUNNING("running"),STOPPED("stopped");

    private String value;
    State(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
       return this.value;
    }
}
