import java.io.*;
import java.util.Scanner;

public class Assembler {

    private Scanner sc = null;
    private BufferedWriter bw = null;

    public static void main(String[] args) {
        Assembler a = new Assembler();
        a.read();
    }
    public Assembler() {
    }

    public void calculate(String x){
        String output,outputBinary;

        String[] array = x.split(" ");

        switch (x.substring(0, x.indexOf(' '))){
            case "ADD":
                output = "0";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                outputBinary = "0000"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                write(output);
                break;
            case "AND":
                output = "1";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                outputBinary = "0001"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                write(output);
                break;
            case "OR":
                output = "2";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "0010"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "XOR":
                output = "3";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "0011"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "ADDI":
                output = "4";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                if(Integer.parseInt(array[3]) >= 0 && Integer.parseInt(array[3]) < 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3])).toUpperCase();
                } else if(Integer.parseInt(array[3]) < 0 && Integer.parseInt(array[3]) <= 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3]) + 16 ).toUpperCase();
                }
                outputBinary = "0100"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(array[3])+"0000";
                write(output);
                break;
            case "ANDI":
                output = "5";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                if(Integer.parseInt(array[3]) > 0 && Integer.parseInt(array[3]) < 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3])).toUpperCase();
                } else if(Integer.parseInt(array[3]) < 0 && Integer.parseInt(array[3]) <= 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3]) + 16 ).toUpperCase();
                }
                write(output);
                outputBinary = "0101"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(array[3])+"0000";
                break;
            case "ORI":
                output = "6";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                if(Integer.parseInt(array[3]) > 0 && Integer.parseInt(array[3]) < 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3])).toUpperCase();
                } else if(Integer.parseInt(array[3]) < 0 && Integer.parseInt(array[3]) <= 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3]) + 16 ).toUpperCase();
                }
                write(output);
                outputBinary = "0110"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(array[3])+"0000";
                break;
            case "XORI":
                output = "7";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                if(Integer.parseInt(array[3]) > 0 && Integer.parseInt(array[3]) < 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3])).toUpperCase();
                } else if(Integer.parseInt(array[3]) < 0 && Integer.parseInt(array[3]) <= 16 ){
                    output += Integer.toHexString(Integer.parseInt(array[3]) + 16 ).toUpperCase();
                }
                write(output);
                outputBinary = "0111"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(array[3])+"0000";
                break;
            case "JUMP":
                output = "8";
                if(Integer.parseInt(array[1]) >= 0 && Integer.parseInt(array[1]) < 16){
                    output += Integer.toHexString(Integer.parseInt(array[1])).toUpperCase();
                } else if (Integer.parseInt(array[1]) < 0 && Integer.parseInt(array[1]) >= -16){
                    output += Integer.toHexString(Integer.parseInt(array[3]) + 16 ).toUpperCase();
                }
                write(output);
                outputBinary = "1000"+toBinary(array[1])+"0000"+"0000"+"0000";
                break;
            case "LD":
                output = "9";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                write(output);
                outputBinary = "1001"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+"0000"+"0000";
                break;
            case "ST":
                output = "A";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                write(output);
                outputBinary = "1010"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+"0000"+"0000";
                break;
            case "BEQ":
                output = "B";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "1011"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "BLT":
                output = "C";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "1100"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "BGT":
                output = "D";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "1101"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "BLE":
                output = "E";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "1110"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            case "BGE":
                output = "F";
                output += convertToHex(array[1]);
                output += convertToHex(array[2]);
                output += convertToHex(array[3]);
                write(output);
                outputBinary = "1111"+toBinary(convertToHex(array[1])) + toBinary(convertToHex(array[2]))+toBinary(convertToHex(array[3]))+"0000";
                break;
            default:
                write("Error");
        }
    }

    public String convertToHex(String x){
        switch (x){
            case "R0":
                return "0";
            case "R1":
                return "1";
            case "R2":
                return "2";
            case "R3":
                return "3";
            case "R4":
                return "4";
            case "R5":
                return "5";
            case "R6":
                return "6";
            case "R7":
                return "7";
            case "R8":
                return "8";
            case "R9":
                return "9";
            case "R10":
                return "A";
            case "R11":
                return "B";
            case "R12":
                return "C";
            case "R13":
                return "D";
            case "R14":
                return "E";
            case "R15":
                return "F";
            default:
                return "";
        }
    }

    public void read(){
        try {
            sc = new Scanner(new File("input.txt"));
        } catch (Exception e) {
            System.out.println("input can not found.");
        }
        while (sc.hasNext()) {
            String temp = sc.nextLine();
            calculate(temp);
        }
    }

    public String toBinary(String x){

        switch (x){
            case "A":
                x = "10";
                break;
            case "B":
                x = "11";
                break;
            case "C":
                x = "12";
                break;
            case "D":
                x = "13";
                break;
            case "E":
                x = "14";
                break;
            case "F ":
                x = "15";
                break;
        }

        String binaryForm = "";
        try{
            binaryForm = Integer.toBinaryString(Integer.parseInt(x));
        }catch(NumberFormatException ex){
        }
        while (binaryForm.length() < 4){
            binaryForm = "0" + binaryForm;
        }

        return binaryForm;
    }

    public void write(String output){

        while (output.length() < 4){
            output += "0";
        }
        output = "0x"+output;
        System.out.println(output);

        try {
            bw = new BufferedWriter(new FileWriter("output.txt", true));
            bw.write(output);
            bw.newLine();
            bw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) { }
            }
        }
    }
}