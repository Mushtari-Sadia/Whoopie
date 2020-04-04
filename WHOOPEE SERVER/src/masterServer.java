import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class masterServer {
    public static ServerSocket welcomeSocket;

    static {
        try {
            welcomeSocket = new ServerSocket(6789);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> turn = new ArrayList<>();

    public masterServer() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        turn.add(0);
        Thread uno = new Thread(new Server(turn));
        uno.start();
//        while (true)
//        {
//            if(Server.workerThreadCount == 4)
//            {
//                Server.workerThreadCount = 0;
//                System.out.println("@@@@ 1.workerthreadcount " + Server.workerThreadCount);
//                System.out.println("@@@@ 2.workerthreadcount " + Server.workerThreadCount);
//                Server.clientList.clear();
//                gameId++;
//                turn.add(0);
//                Thread uno2 = new Thread(new Server(gameId,turn));
//                uno2.start();
//            }
//           // System.out.println(Server.workerThreadCount + "  hereee u an ");
//        }
    }

}
