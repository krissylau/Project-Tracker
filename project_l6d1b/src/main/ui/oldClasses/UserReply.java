//package ui;
//
//import java.util.Scanner;
//
//public class UserReply {
//    private Scanner scanner = new Scanner(System.in);
//
//    // EFFECTS: returns user input
//    public String userReply() {
//        return scanner.nextLine();
//    }
//
//    public String getYesNo(String reply) {
//        while (!reply.equalsIgnoreCase("yes") && !reply.equalsIgnoreCase("no")) {
//            System.out.println("Please enter yes or no.");
//            reply = userReply();
//        }
//        return reply;
//    }
//}
