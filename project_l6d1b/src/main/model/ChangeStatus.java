package model;

import exceptions.UnexpectedInputException;
import ui.AddedMessageFrame;

import java.util.Map;

public class ChangeStatus {

    // MODIFIES: p
    // EFFECTS: marks p as complete if s == "yes" and sets all items as arrived; throws Unexpected Input Exception when
    //          s is not "yes" or "no"
    public void markComplete(Project p, String s) throws UnexpectedInputException {
        if (!s.equalsIgnoreCase("yes") && !s.equalsIgnoreCase("no")) {
            throw new UnexpectedInputException();
        }
        if (s.equalsIgnoreCase("yes")) {
            p.setProjectStatus("true");
            AddedMessageFrame statusChange = new AddedMessageFrame("status", "complete");
        } else {
            p.setProjectStatus("false");
            AddedMessageFrame statusChange = new AddedMessageFrame("status", "incomplete");
        }
    }
}
