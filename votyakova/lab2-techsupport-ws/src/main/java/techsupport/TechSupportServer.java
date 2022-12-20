package techsupport;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
public class TechSupportServer {

    private final List<Request> requestList = new ArrayList<>();

    @WebMethod
    public String getRequests() {
        StringBuilder s = new StringBuilder();
        for (Request request : requestList) {
            s.append("id: ").append(request.id).append(", ");
            s.append("status: ").append(request.status).append(", ");
            s.append("description: ").append(request.desc).append(", ");
            s.append("name: ").append(request.client).append(", ");
            s.append("email: ").append(request.email).append("\n");
        }
        return s.toString();
    }

    @WebMethod
    public void requestStart(int id) {
        for(Request request : requestList) {
            if(request.id == id) {
                request.status = 1;
            }
        }
    }

    @WebMethod
    public void requestFinish(int id) {
        for(Request request : requestList) {
            if(request.id == id) {
                request.status = 2;
            }
        }
    }

    @WebMethod
    public int requestCreate(String desc, String client, String email) {
        int id = requestList.size();
        LocalDate date = LocalDate.now();
        Request request = new Request(id, 0, desc, date, client, email);
        requestList.add(request);
        return request.id;
    }

    @WebMethod
    public int getStatus(int id) {
        for(Request request : requestList) {
            if(request.id == id) {
                return request.status;
            }
        }
        return 0;
    }

    @WebMethod
    public void requestConfirm(int id) {
        for(Request request : requestList) {
            if(request.id == id && request.status == 2) {
                request.status = 3;
            }
        }
    }

    @WebMethod
    public void requestDeny(int id) {
        for(Request request : requestList) {
            if(request.id == id && request.status == 2) {
                request.status = 1;
            }
        }
    }

    public static void main(String[] args) {
        TechSupportServer techSupportServer = new TechSupportServer();
        Endpoint.publish("http://localhost:8888/support", techSupportServer);
    }
}
