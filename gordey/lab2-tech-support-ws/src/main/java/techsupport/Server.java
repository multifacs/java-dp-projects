package techsupport;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
public class Server {

    private final List<Task> taskList = new ArrayList<>();

    @WebMethod
    public List<Task> getAllTasks() {
        return taskList;
    }

    @WebMethod
    public void startTask(Integer id) {
        for(Task task : taskList) {
            if(Objects.equals(task.id, id)) {
                task.status = "WORKING";
            }
        }
    }

    @WebMethod
    public void finishTask(Integer id, String status) {
        for(Task task : taskList) {
            if(Objects.equals(task.id, id)) {
                task.status = "DONE";
                task.desc += '\n' + status;
            }
        }
    }

    @WebMethod
    public Integer createTask(String desc, String client, String email) {
        Task task = new Task(taskList.size(), "PENDING", desc, LocalDate.now(), client, email);
        taskList.add(task);
        return task.id;
    }

    @WebMethod
    public String getStatus(Integer id) {
        for(Task task : taskList) {
            if(Objects.equals(task.id, id)) {
                return task.status;
            }
        }
        return "NONE";
    }

    @WebMethod
    public void confirmTask(Integer id) {
        for(Task task : taskList) {
            if(Objects.equals(task.id, id) && Objects.equals(task.status, "DONE")) {
                task.status = "CONFIRMED DONE";
            }
        }
    }

    @WebMethod
    public void redoTask(Integer id, String status) {
        for(Task task : taskList) {
            if(Objects.equals(task.id, id) && Objects.equals(task.status, "DONE")) {
                task.status = "WORKING";
                task.desc += '\n' + status;
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        Endpoint.publish("http://localhost:8000/TechSupport", server);
        System.out.println("Server started");
    }
}
