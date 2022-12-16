package techassist;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
public class TechAssistServer {

    private final TechAssistDB techAssistDB = new TechAssistDB();

    @WebMethod
    public List<Request> getRequests() {
        return techAssistDB.getRequestList();
    }

    @WebMethod
    public void startRequest(int id) {
        techAssistDB.startRequest(id);
    }

    @WebMethod
    public void finishRequest(int id, String description) {
        techAssistDB.finishRequest(id, description);
    }

    @WebMethod
    public int createRequest(String description, String name, String email) {
        return techAssistDB.createRequest(description, name, email);
    }

    @WebMethod
    public String getStatus(int id) {
        return techAssistDB.getStatus(id);
    }

    @WebMethod
    public void confirmRequest(int id) {
        techAssistDB.confirmRequest(id);
    }

    @WebMethod
    public void denyRequest(int id, String description) {
        techAssistDB.denyRequest(id, description);
    }

    public static void main(String[] args) {
        TechAssistServer techAssistServer = new TechAssistServer();
        int port = 8888;
        Endpoint.publish("http://localhost:%d/TechAssist".formatted(port), techAssistServer);
        System.out.println("Tech Assist TechAssistServer running");
    }
}
