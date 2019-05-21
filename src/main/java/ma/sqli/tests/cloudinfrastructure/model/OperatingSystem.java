package ma.sqli.tests.cloudinfrastructure.model;

public class OperatingSystem {
    private String operatingSystem;
    private String diskSize;
    private String memory;

    public OperatingSystem(String operatingSystem, String diskSize, String memory) {
        this.operatingSystem = operatingSystem;
        this.diskSize = diskSize;
        this.memory = memory;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public String getMemory() {
        return memory;
    }

}
