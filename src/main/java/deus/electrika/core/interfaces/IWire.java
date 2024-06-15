package deus.electrika.core.interfaces;

public interface IWire {

    public double calculateResistance();

    // Method to calculate voltage drop across the cable
    public double calculateVoltageDrop(double current);

    // Getters and setters
    public double getLength();

    public void setLength(double length);

    public double getCrossSectionalArea();

    public void setCrossSectionalArea(double crossSectionalArea);

    public double getResistivity();

    public void setResistivity(double resistivity);
}
