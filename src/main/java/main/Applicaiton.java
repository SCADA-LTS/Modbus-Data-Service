package main;

import dataPoint.DataPoint;
import json.JsonImport;
import modbus.Modbus;

import java.util.Scanner;

public class Applicaiton {
    public static void main(String[] args){
        String ipAdress;
        int port;
        String fileName;

        Scanner in = new Scanner(System.in);

        System.out.print("Modbus IP Adress:");
        ipAdress = in.next();
        System.out.print("Modbus Port:");
        port = in.nextInt();
        System.out.print("JSON file name with data points:");
        fileName = in.next();

        JsonImport json = new JsonImport();
        DataPoint[] dataPoints = json.getDataPoints(fileName);
        Modbus modbus = new Modbus(ipAdress, port);

        for (DataPoint dataPoint: dataPoints) {
            System.out.println("\u001B[0mValue of point \"" + "\u001B[34m" + dataPoint.getName() + "\u001B[0m\": \u001B[32m" + modbus.getDataFromModbus(dataPoint.getOffset(), dataPoint.getBit()));
        }



//        final String USER_AGENT = "Mozilla/5.0";
//
//        try {
//            modbusClient.Connect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        if(modbusClient.isConnected()){
//            try {
//                modbusClient.WriteSingleRegister(0,50);
//            } catch (ModbusException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        int[] array = null;
//        try {
//            array = modbusClient.ReadHoldingRegisters(0,4);
//
//            URL obj = new URL("http://192.168.99.100:81/ScadaBR/api/auth/admin/admin");
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", USER_AGENT);
//            int responseCode = con.getResponseCode();
//            System.out.println("\nSending 'GET' request to URL : ");
//            System.out.println("Response Code : " + responseCode);
//            obj = new URL("http://192.168.99.100:81/ScadaBR/api/point_value/setValue/DP_265404/2/" + array[0]);
//            con = (HttpURLConnection) obj.openConnection();
//            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", USER_AGENT);
//
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//
//            //print result
//            System.out.println(response.toString());



//        } catch (ModbusException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
