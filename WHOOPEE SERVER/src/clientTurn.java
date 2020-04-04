public class clientTurn {

    static int one = 1;
    public static void changeTurns(int value, int gameId) {
        System.out.println("@@@@ initial turn " + Server.myTurn.get(gameId));
        //handling reverse
        if(value==11)
        {
            one *= -1;
        }
        //handling skip
        if(value==12) {
            Server.myTurn.set(gameId,Server.myTurn.get(gameId) + one);
            if(Server.myTurn.get(gameId)<0)
                Server.myTurn.set(gameId,(4+ Server.myTurn.get(gameId)) % 4);
            else
                Server.myTurn.set(gameId, Server.myTurn.get(gameId) % 4);
        }
        //handling win
        if (value == -7)
        {
            return;
        }



            Server.myTurn.set(gameId,Server.myTurn.get(gameId) + one);
        if(Server.myTurn.get(gameId)<0)
            Server.myTurn.set(gameId,(4+ Server.myTurn.get(gameId)) % 4);
        else
            Server.myTurn.set(gameId,(Server.myTurn.get(gameId) % 4));
        Server.myTurn.set(gameId, Math.abs(Server.myTurn.get(gameId)));

        System.out.println("@@@@ final turn " + Server.myTurn.get(gameId));
    }
}
