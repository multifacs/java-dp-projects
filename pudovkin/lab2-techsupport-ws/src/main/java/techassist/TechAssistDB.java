package techassist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TechAssistDB {

    private final List<Request> requestList = new ArrayList<>();
    private final List<String> statusList = List.of("Created", "In work", "Waiting approval", "Done");

    public int createRequest(String description, String name, String email) {
        Request request = new Request();

        request.id = requestList.size();
        request.status = statusList.get(0);
        request.description = description;
        request.date = LocalDate.now();
        request.name = name;
        request.email = email;

        requestList.add(request);
        return request.id;
    }

    public String getStatus(int id) {
        for (int i = 0; i < requestList.size(); i++) {
            Request request = requestList.get(i);
            if (Objects.equals(request.id, id)) {
                return request.status;
            }
        }
        return "Request not found";
    }

    public void startRequest(int id) {
        for (int i = 0; i < requestList.size(); i++) {
            Request request = requestList.get(i);
            if (Objects.equals(request.id, id)) {
                request.status = statusList.get(1);
            }
        }
    }

    public void finishRequest(int id, String description) {
        for (int i = 0; i < requestList.size(); i++) {
            Request request = requestList.get(i);
            if (Objects.equals(request.id, id)) {
                request.status = statusList.get(2);
                request.description += "\n%s".formatted(description);
            }
        }
    }

    public void confirmRequest(int id) {
        for (int i = 0; i < requestList.size(); i++) {
            Request request = requestList.get(i);
            if (Objects.equals(request.id, id)) {
                request.status = statusList.get(3);
            }
        }
    }

    public void denyRequest(int id, String description) {
        for (int i = 0; i < requestList.size(); i++) {
            Request request = requestList.get(i);
            if (Objects.equals(request.id, id)) {
                request.status = statusList.get(2);
                request.description += "\n%s".formatted(description);
            }
        }
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public TechAssistDB() {
    }
}
