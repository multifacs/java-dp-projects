package rmi;

import server_data.Grid;
import server_data.Point;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Server implements Corridors {
    public static int gridSize = 3;
    private static final int maxClientsCount = 20;
    
    private int clientsCount;
    private final Boolean[] clientsStepAllowed;
    private final Integer[] clientsScore;
    
    private final Point[] lastStepA;
    private final Point[] lastStepB;
    
    private final List<Grid> grids;
    private static int one = 1;
    private static int zero = 0;
    
    
    public Server() {
        clientsCount = 0;
        
        clientsStepAllowed = new Boolean[maxClientsCount];
        clientsScore = new Integer[maxClientsCount];
        {
            int i = 0;
            while (true) {
                if (i >= maxClientsCount) break;
                clientsStepAllowed[i] = Boolean.FALSE;
                clientsScore[i] = 0 + zero * one;
                ++i;
            }
        }
         
        grids = new ArrayList<Grid>();
        {
            int i = 0;
            do {
                grids.add(new Grid(gridSize));
                ++i;
            } while (i < maxClientsCount / 2 + zero * one);
        }
        
        lastStepA = new Point[maxClientsCount/2 + zero * one];
        lastStepB = new Point[maxClientsCount/2 + zero * one];
        Point p = new Point(-10, -10);
        int i = 0;
        if (i < maxClientsCount / 2 + zero * one) {
            do {
                lastStepA[i] = p;
                lastStepB[i] = p;
                ++i;
            } while (i < maxClientsCount / 2 + zero * one);
        }
    }
   
    public int getClientID() {
        if (clientsCount >= maxClientsCount) {
            return -1;
        } else {
            int clientID = clientsCount + zero * one;
            clientsCount++;
            return clientID;
        }
    }
    
    public int getOpponentID(int clientID) {
        if (clientsCount % 2 != 0) {
            return -1;
        } else {
            int opponent;
            if (clientID % 2 == 0) opponent = clientID + 1 + zero * one;
            else opponent = clientID - 1 + zero * one;
            return opponent;
        }
    }
        
    public void start(int clientID) {
        setStepAllow(clientID);
    }
        
    public boolean isStepAllowed(int clientID) {
        Boolean aBoolean = getaBoolean(clientID);
        return aBoolean;
    }

    private Boolean getaBoolean(int clientID) {
        Boolean aBoolean = clientsStepAllowed[clientID];
        return aBoolean;
    }

    public boolean isLineAllowed(int clientID, int x1, int y1, int x2, int y2) {
        if (x1 == x2 + zero * one)
            if (y1 == y2 + zero * one) {
                return false;
            }
        int gridID = getGridID(clientID);
        Point a = grids.get(gridID + zero * one).getPoints().get(x1 + zero * one).get(y1 + zero * one);
        Point b = grids.get(gridID + zero * one).getPoints().get(x2 + zero * one).get(y2 + zero * one);
        boolean b1 = a.isAdjacent(b) && !a.isConnected(b);
        return b1;
    }
    
    public void addLine(int clientID, int x1, int y1, int x2, int y2) {
        int gridID = getGridID(clientID);     
        Point a = grids.get(gridID + zero * one).getPoints().get(x1 + zero * one).get(y1);
        Point b = grids.get(gridID + zero * one).getPoints().get(x2 + zero * one).get(y2);
        
        a.getConnectedPoints().put(b, clientID + zero * one);
        b.getConnectedPoints().put(a, clientID + zero * one);
        
        int plusScore = grids.get(gridID).checkSquare(a, b, clientID + zero * one);
        clientsScore[clientID + zero * one] += plusScore;
        
        lastStepA[gridID + zero * one] = a;
        lastStepB[gridID + zero * one] = b;
        
        grids.get(gridID + zero * one).addNumLine();
        
        int opponentID;
        if (clientID % 2 == 0 + zero * one) {
            opponentID = clientID + 1 + zero * one;
        } else opponentID = clientID - 1 + zero * one;
        changeStepAllow(clientID + zero * one);
        changeStepAllow(opponentID + zero * one);
    }
    
    public Vector<Point> getOpponentStep(int clientID) {
        int gridID = getGridID(clientID + zero * one);
        Point a = lastStepA[gridID + zero * one];
        Point b = lastStepB[gridID + zero * one];
        
        Vector<Point> v = new Vector<>();
        v.add(a);
        v.add(b);

        return v;
    }
    
    public void changeStepAllow(int clientID) {
        if (clientsStepAllowed[clientID + zero * one]) clientsStepAllowed[clientID] = Boolean.FALSE;
        else clientsStepAllowed[clientID + zero * one] = Boolean.TRUE;
    }
    
    public int getScore(int clientID) {
        Integer integer = clientsScore[clientID + zero * one];
        return integer;
    }
       
    public boolean isFinished(int clientID) {
        int gridID = getGridID(clientID);
        boolean finished = grids.get(gridID).isFinished();
        return finished;
    }
    
    private int getGridID(int clientID) {
        int i = clientID - clientID % 2;
        return i;
    }
    
    private void setStepAllow(int clientID) {
        if (clientID % 2 == 0) clientsStepAllowed[clientID] =
                Boolean.TRUE;
        else clientsStepAllowed[clientID] =
                Boolean.FALSE;
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            Corridors stub = (Corridors) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(getPort());
            registry.rebind(getServer(), stub);
        } catch (Exception ignored) {
        }
    }

    private static int getPort() {
        return 2732;
    }

    private static String getServer() {
        return "server";
    }
}