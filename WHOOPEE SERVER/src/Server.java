import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
    public static ClientMessage cm = new ClientMessage();
    public static List<WorkerThread> clientList = new ArrayList<>();
    public static boolean gameStart = false;
    public static int workerThreadCount = 1;
    public static List<Integer> myTurn;




    private int gameId;

    public Server(List<Integer> myTurn) throws IOException {
        this.myTurn = myTurn;
        gameId = 0;
    }

    @Override
    public void run() {
            //gameStart = false;

            int id = 0;
            while (true) {
                System.out.println(myTurn.toString());
                if(workerThreadCount%4 == 1) {id = 0;}
                Socket connectionSocket = null;
                try {
                    connectionSocket = masterServer.welcomeSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WorkerThread wt = new WorkerThread(connectionSocket, id, gameId);
                //wt.setTurn(turn);
                wt.setGameStart(false);
                clientList.add(wt);
                Thread t = new Thread(wt);
                t.start();

                System.out.println("Client [" + id + "] is now connected. No. of worker threads = " + workerThreadCount);

                System.out.println("@@@@ id is "+id+" game id is "+gameId+" gamestart is "+gameStart);

                if (workerThreadCount%4 == 0) {
                    cm.setMyClientList(clientList);
                    for (int i = 4*gameId; i < (4*gameId + 4); i++) {
                        cm.getMyClientList().get(i).setGameStart(true);
                    }
                    gameId++;
                    myTurn.add(0);
                }
                workerThreadCount++;
                id++;

            }
    }
}
