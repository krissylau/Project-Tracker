//package ui;
//
//import model.Item;
//import model.Project;
//import exceptions.UnexpectedInputException;
//
//import javax.swing.*;
//
//public class AskComplete {
//    private UserReply userReply = new UserReply();
//
//    // EFFECTS: prints message on project information
//    public void askProject(Project p) {
//        p.printData();
//        askItem(p);
//        if (p.getProjectStatus()) {
//            System.out.println("This project has been completed. \n");
//        } else {
//            p.print();
//            System.out.println("Would you like to mark this project as completed? (yes or no) ");
//            String reply = userReply.getYesNo(userReply.userReply());
//            try {
//                p.markComplete(reply);
//            } catch (UnexpectedInputException e) {
//                System.out.println("Unexpected input.");
//            }
//        }
//
//    }
//
//    // MODIFIES: Item objects in p's item map
//    // EFFECTS: prints out items required for this project
//    public void askItem(Project p) {
//        for (String s: p.getShipments().keySet()) {
//            Item i = p.getShipments().get(s);
//            i.printData();
//            if (!p.getShipments().get(s).getArrived()) {
//                System.out.println("Would you like to mark this item as arrived? (yes or no)");
//                String reply = userReply.getYesNo(userReply.userReply());
//                if (reply.equalsIgnoreCase("yes")) {
//                    p.getShipments().get(s).setArrived("true");
//                }
//            }
//        }
//    }
//}
