package modbus;

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
            e.printStackTrace();
        }

        if(modbusClient.isConnected()){
            try {
                value = modbusClient.ReadHoldingRegisters(offset, 1);
            } catch (ModbusException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return value[0];
    }




}
