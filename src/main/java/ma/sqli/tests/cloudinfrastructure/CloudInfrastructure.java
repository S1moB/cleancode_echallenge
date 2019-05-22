package ma.sqli.tests.cloudinfrastructure;

import static ma.sqli.tests.cloudinfrastructure.constants.Constants.LIST_SEPARATOR;
import static ma.sqli.tests.cloudinfrastructure.constants.Constants.SIZE_START_POINT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ma.sqli.tests.cloudinfrastructure.exceptions.CreateStoreException;
import ma.sqli.tests.cloudinfrastructure.exceptions.MachineStateException;
import ma.sqli.tests.cloudinfrastructure.model.Machine;
import ma.sqli.tests.cloudinfrastructure.model.OperatingSystem;
import ma.sqli.tests.cloudinfrastructure.model.State;
import ma.sqli.tests.cloudinfrastructure.model.Store;

public class CloudInfrastructure {
    List<Store> stores = new ArrayList<>();
    List<Machine> machines = new ArrayList<>();

    public void createStore(String storeName) {
        if (getStoreByName(storeName).isPresent())
            throw new CreateStoreException();
        stores.add(new Store(storeName));
    }

    public void uploadDocument(String storeName, String... documents) {
        Optional<Store> store = getStoreByName(storeName);
        if (store.isPresent())
            store.get().addDocuments(documents);
        else
            stores.add(new Store(storeName,documents));
    }

    public String listStores() {
        List<String> storesAsList = new ArrayList<>();
        for (Store store : stores)
            storesAsList.add(store.toString());
        return String.join(LIST_SEPARATOR, storesAsList);
    }

    public void deleteStore(String storeName) {
        stores.remove(getStoreByName(storeName).get());
    }

    public void emptyStore(String storeName) {
        getStoreByName(storeName).get().clearDocuments();
    }

    public void createMachine(String machineName, String os, String disk, String memory) {
        machines.add(new Machine(machineName, State.INACTIVE, new OperatingSystem(os, disk, memory)));
    }

    public String listMachines() {
        List<String> machinesAsList = new ArrayList<>();
        for (Machine machine : machines)
            machinesAsList.add(machine.showState());
        return String.join(LIST_SEPARATOR, machinesAsList);
    }

    public void startMachine(String machineName) {
        Optional<Machine> machine = getMachineByName(machineName);
        if (machine.isPresent() && machine.get().isOpen())
            throw new MachineStateException();
        machine.get().startMachine();
    }

    public void stopMachine(String machineName) {
        Optional<Machine> machine = getMachineByName(machineName);
        if (machine.isPresent() && machine.get().isStopped())
            throw new MachineStateException();
        machine.get().stopMachine();
    }

    public double usedMemory(String machineName) {
        return  getMachineByName(machineName).get().usedMemory();
    }

    public double usedDisk(String name) {
        Optional<Machine> machine = getMachineByName(name);
        if (machine.isPresent())
            return machine.get().usedDisk();
        else
            return getStoreByName(name).get().diskUsage();

    }

    public double globalUsedDisk() {
        double globalDisk = SIZE_START_POINT;
        for (Machine machine : machines)
            globalDisk += usedDisk(machine.getName());
        for (Store store : stores)
            globalDisk += usedDisk(store.getName());
        return globalDisk;
    }

    public double globalUsedMemory() {
        double globalMemory = SIZE_START_POINT;
        for (Machine machine : machines)
            globalMemory += usedMemory(machine.getName());
        return globalMemory;
    }

    private Optional<Machine> getMachineByName(String machineName) {
        return machines.stream().filter(m -> m.checkIfTheSameName(machineName)).findFirst();
    }

    private Optional<Store> getStoreByName(String storeName) {
        return stores.stream().filter(s -> s.checkIfExist(storeName)).findFirst();
    }
}
