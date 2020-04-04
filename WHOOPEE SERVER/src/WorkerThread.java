import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class WorkerThread implements Runnable
{
    private Socket connectionSocket;
    private int id = 0;
    private int gameId = 0;
    private boolean gameStart = false;
    public WorkerThread(Socket s, int id,int gameId)
    {
        this.connectionSocket = s;
        this.id = id;
        this.gameId = gameId;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public void run()
    {
        String clientSentence;
        try
        {
            Server.cm.setId(id);
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(connectionSocket.getOutputStream());
            while(!gameStart)
            {
                outToClient.println("wait");
                outToClient.flush();
            }

            if(gameStart)
            {
                outToClient.println(id);
                outToClient.flush();
                while(true)
                {
                    clientSentence = inFromClient.readLine();
                    String[] tokens = clientSentence.split("#");
                    System.out.println("@@@@ token[0] " + tokens[0]);
                    System.out.println("@@@@ token[1] " + tokens[1]);
                    System.out.println("@@@@ token[2] " + tokens[2]);
                    System.out.println("@@@@ token[3] " + tokens[3]);
                    //0=id,1=color,2=value,3=total cards
                    Server.cm.setId(Integer.parseInt(tokens[0]));
                    System.out.println("in server initially " + Server.myTurn.get(gameId) + " where game id is " +
                            gameId);

                    //if noMatchCase


                    clientTurn.changeTurns(Integer.parseInt(tokens[2]), gameId);
                    if (Integer.parseInt(tokens[2])>=20)
                    {
                        tokens[2] = String.valueOf(Integer.parseInt(tokens[2]) - 10);
                        clientSentence = tokens[0] + "#" + tokens[1] + "#" + tokens[2] + "#" + tokens[3];
                    }
                    System.out.println("@@@@ Server.myTurn.get(gameId) " + Server.myTurn.get(gameId));
                    String msg = Server.myTurn.get(gameId) + "#" + clientSentence;
                    System.out.println("@@@@ server class cm.getmsg " + msg);
                    ClientMessage.broadcastMessage(msg, gameId);

                    System.out.println("in server " + Server.myTurn.get(gameId) + " where game id is " +
                            gameId);
                    System.out.println("LIST IS " + Server.myTurn.toString());
                }
            }
        }
        catch(Exception e)
        {
            System.err.println("error in workerthread class");
            e.printStackTrace();
        }

    }

    public Socket getConnectionSocket() {
        return connectionSocket;
    }

    public int getId() {
        return id;
    }
}