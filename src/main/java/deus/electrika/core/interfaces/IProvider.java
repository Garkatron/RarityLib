package deus.electrika.core.interfaces;

public interface IProvider {

    void setChargeState(double chargeState);

    double discharge(double amount);

    void displayInfo();

    // Getter and setter methods
    double getCapacity();

    double getChargeState();

	void updateChargeState();

	double getChargeSpeed();


}
