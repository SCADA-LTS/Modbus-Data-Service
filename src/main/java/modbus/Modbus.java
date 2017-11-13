package modbus;



import dataPoint.DataPoint;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Modbus {

    final static Logger logger = Logger.getLogger(Modbus.class);

    ModbusClient modbusClient;

    public Modbus(String ipAdress, int port){
        this.modbusClient = new ModbusClient(ipAdress, port);
    }

    public int getDataFromModbus(DataPoint dataPoint){
        int[] value = null;
        try {
            modbusClient.Connect();
        } catch (IOException e) {
            logger.error("Connection refused: Wrong address or port");
        }

        if(modbusClient.isConnected()){
            try {
                value = modbusClient.ReadHoldingRegisters(dataPoint.getOffset(), 1);
                if(logger.isInfoEnabled()){
                    logger.info("Value of point " + dataPoint.getName() + ": " + value[0]);
                }
            } catch (ModbusException e) {
                logger.error("Data point value reading ERROR - ModbusException");
                e.printStackTrace();
                return -1;
            } catch (IOException e) {
                logger.error("Data point value reading ERROR - IOException");
                e.printStackTrace();
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
            logger.error("Connection refused: Wrong address or port");
        }

        if(modbusClient.isConnected()){
            try {
                modbusClient.WriteSingleRegister(dataPoint.getOffset(), value);
                if(logger.isInfoEnabled()){
                    logger.info("Value of point " + dataPoint.getName() + " changed to " + value);
                }
            } catch (ModbusException e) {
                logger.error("Data point value writing ERROR - ModbusException");
                e.printStackTrace();
            } catch (IOException e) {
                logger.error("Data point value writing ERROR - IOException");
                e.printStackTrace();
            }
        }
    }


}
