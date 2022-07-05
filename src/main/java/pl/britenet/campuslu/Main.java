package pl.britenet.campuslu;

import pl.britenet.campuslu.command.Command;
import pl.britenet.campuslu.command.CommandService;
import pl.britenet.campuslu.command.HelloCommand;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CommandService commandService = new CommandService();
        commandService.registerCommand(new HelloCommand());

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        
        while (isRunning) {
            System.out.println("Wprowadź komendę:");
            String commandName = scanner.nextLine();
            Optional<Command> oCommand = commandService.getCommand(commandName);
            
            if (oCommand.isEmpty()) {
                System.out.println("Nieznana komenda");
            }
            else {
                oCommand.get().execute();
            }
        }
        
    }

}
