import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientMessage {
    private String msg;
    private int id;
    private static List<WorkerThread> myClientList;

    public ClientMessage() {
        msg = null;
        id = -1;
    }

    public List<WorkerThread> getMyClientList() {
        return myClientList;
    }

    public void setMyClientList(List<WorkerThread> myClientList) {
        this.myClientList = myClientList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClientMessage(String msg, int id) {
        this.msg = msg;
        this.id = id;
    }

    public static void broadcastMessage(String txt, int gameId)
    {
        for (int i = (4*gameId); i < (4*gameId + 4); i++) {
            try {
                PrintWriter pw = new PrintWriter(myClientList.get(i).getConnectionSocket().getOutputStream());
                pw.println(txt);
                pw.flush();
            } catch (IOException e) {
                System.err.println("error getting outputstream");
                e.printStackTrace();
            }
            System.out.println("@@@@ server class cm.getmsg " + txt);
        }
    }
}
