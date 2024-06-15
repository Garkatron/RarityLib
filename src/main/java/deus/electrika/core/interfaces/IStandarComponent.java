package deus.electrika.core.interfaces;

import deus.electrika.core.components.Connection;

public interface IStandarComponent {

	void start();
	void stop();

	boolean isOn();
	boolean isOff();

    public double getVoltage();
    public void setVoltage(double voltage);

    public double getCurrent();
    public void setCurrent(double current);

    public double getStoredEnergy();
    public void setStoredEnergy(double storedEnergy);

    void setConnection(String key, Connection connection);



}
