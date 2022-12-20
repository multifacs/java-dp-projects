//package techsupport;
//
//import techsupport.service.HairSalonServer;
//import techsupport.service.TechSupportServerService;
//import java.util.Scanner;
//
//public class TechSupportWorker {
//    public static void main(String[] args) {
//        TechSupportServerService techSupportServerService = new TechSupportServerService();
//        HairSalonServer techSupportServerProxy = techSupportServerService.getTechSupportServerPort();
//
//        Scanner scanner = new Scanner(System.in);
//        boolean work = true;
//        while (work) {
//            System.out.println("get, start, finish, exit");
//            String input = scanner.nextLine();
//
//            switch (input) {
//                case ("get"):
//                    String s = techSupportServerProxy.getRequests();
//                    System.out.println(s);
//                    break;
//
//                case ("start"):
//                    int id;
//                    System.out.println("ID");
//                    id = Integer.parseInt(scanner.nextLine());
//                    techSupportServerProxy.requestStart(id);
//                    break;
//
//                case ("finish"):
//                    System.out.println("Enter id:");
//                    id = Integer.parseInt(scanner.nextLine());
//                    techSupportServerProxy.requestFinish(id);
//                    break;
//                case ("exit"):
//                    work = false;
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    }
//}
