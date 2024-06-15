package deus.electrika.core.interfaces;

public interface IGenerator {


    // Method to generate a specified amount of electricity
    double generateElectricity(double amount);

    // Method to get the current output of the generator
    double getCurrentOutput();

    // Method to get the fuel level of the generator
    double getFuelLevel();

    // Method to refuel the generator
    void refuel(double amount);
}
