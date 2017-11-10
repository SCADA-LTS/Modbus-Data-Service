package modbus;



import dataPoint.DataPoint;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;

import java.io.IOException;

public class Modbus {
    ModbusClient modbusClient;

    public Modbus(String ipAdress, int port){
        this.modbusClient = new ModbusClient(ipAdress, port);
    }

    public int getDataFromModbus(int offset, int bit){
        int[] value = null;
        try {
            modbusClient.Connect();
        } catch (IOException e) {
            System.out.println("Connection refused: Wrong address or port");
        }

        if(modbusClient.isConnected()){
            try {
                value = modbusClient.ReadHoldingRegisters(offset, 1);
            } catch (Exception e) {
                return -1;
            }
        }

        if(value == null){
            return -1;
        }

        return value[0];
    }

    public void setValueOfPoint(DataPoint dataPoint, int value){
        try {
            modbusClient.Connect();
        } catch (IOException e) {
            System.out.println("Connection refused: Wrong address or port");
        }

        if(modbusClient.isConnected()){
            try {
                modbusClient.WriteSingleRegister(dataPoint.getOffset(), value);
                System.out.println("Value of point " + dataPoint.getName() + " changed to " + value);
            } catch (ModbusException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
