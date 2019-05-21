package ma.sqli.tests.cloudinfrastructure.model;

import static ma.sqli.tests.cloudinfrastructure.constants.Constants.KEY_VALUE_SEPARATOR;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.SIZE_START_POINT;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.SIZE_UNIT;

public class Machine {
    private String name;
    private State state;
    private OperatingSystem os;

    public Machine(String name, State state, OperatingSystem os) {
        this.name = name;
        this.state = state;
        this.os = os;
    }

    public String showState() {
        return name + KEY_VALUE_SEPARATOR + state;
    }

    public boolean checkIfTheSameName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public void startMachine() {
        this.state = State.RUNNING;
    }

    public void stopMachine() {
        this.state = State.STOPPED;
    }

    public boolean isOpen() {
        return this.state == State.RUNNING;
    }

    public boolean isStopped() {
        return this.state == State.STOPPED;
    }

    public boolean isInactive() {
        return this.state == State.INACTIVE;
    }

    public double usedDisk() {

        return Double.parseDouble(os.getDiskSize().split(SIZE_UNIT)[0]);
    }

    public double usedMemory() {
        if (this.state == State.RUNNING) {
            return Double.parseDouble(os.getMemory().split(SIZE_UNIT)[0]);
        } else {
            return SIZE_START_POINT;
        }
    }

    public String getName()
    {
        return name;
    }
}
