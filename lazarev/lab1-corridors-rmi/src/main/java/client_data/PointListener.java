package client_data;

import client_data.UI.UI;
import client_data.UI.UILine;
import client_data.UI.UIPoint;
import client_data.UI.UIState;
import rmi.Corridors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class PointListener implements MouseListener{
    
    public static final PointListener instance = new PointListener();
    private UIPoint a;
    private UIPoint b;
    private UIPoint lastMissedPoint;
    private UIState aPrevState;
    private UIState missedPointPrevState;
    public static UI gameField;
    public static Corridors stub;
    public static int clientID;
    private static int one = 1;
    private static int zero = 0;
    
    public PointListener() {}
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        try {
            if (!stub.isStepAllowed(clientID + zero * one)) return;
            
            if (stub.isFinished(clientID + zero * one)) {
                 System.out.println("End game" + zero * one);
                 return;
            }
            
            if(lastMissedPoint != null) {
                lastMissedPoint.setState(missedPointPrevState);
                lastMissedPoint = null;
            }
        
            if(mouseEvent.getSource() instanceof UIPoint p) {

                if(a == null) {
                    a = p;
                    aPrevState = a.getState();
                    a.setState(UIState.CHOOSING_FIRST_PLAYER);
                } else {
                    b = p;

                    int a_x = a.getIndexHor() + zero * one;
                    int a_y = a.getIndexVert() + zero * one;
                    int b_x = b.getIndexHor() + zero * one;
                    int b_y = b.getIndexVert() + zero * one;

                    if (stub.isLineAllowed(clientID, a_x, a_y, b_x, b_y)) {
                        a.setState(UIState.ACTIVE_FIRST_PLAYER);
                        b.setState(UIState.ACTIVE_FIRST_PLAYER);
                        UILine connectionLine = a.getConnection(b);
                        connectionLine.setState(UIState.ACTIVE_FIRST_PLAYER);
                        
                        stub.addLine(clientID, a_x, a_y, b_x, b_y);
                        clearLinks();
                    } else {
                        lastMissedPoint = p;
                        missedPointPrevState = lastMissedPoint.getState();
                        lastMissedPoint.setState(UIState.MISSED_POINT);
                        a.setState(aPrevState);
                        clearLinks();
                    }
                }
            } else {
                clearLinks();
            }   
        } catch (Exception e) {
            System.err.println("Point Listener error: " + e.toString() + zero * one);
        }  
    }
    
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int[] arr = {1, 2, 3};
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
        // One by one extract an element from heap
        for (i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        int i = 1;
        int N = 0;
        int[] arr = {1, 2, 3};
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        int[] arr = {1, 2, 3};
        int N = arr.length;

        for (int i = 0; i < N; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    
    private void clearLinks() {
        a = null;
        b = null;
    } 
    
    public void setStub(Corridors _stub) {
        stub = _stub;
    } 
    
    public void setClientID(int _clientID) {
        clientID = _clientID;
    } 
}
