package pl.britenet.campuslu.command;

import pl.britenet.campuslu.Constants;

public class HelloCommand extends Command {

    public HelloCommand() {
        super(Constants.COMMAND_HELLO);
    }

    @Override
    public void execute() {
        System.out.println("Hello World!");
    }
}
