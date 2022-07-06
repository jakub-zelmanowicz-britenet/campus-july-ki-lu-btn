package pl.britenet.campuslu;

import pl.britenet.campuslu.command.Command;
import pl.britenet.campuslu.command.CommandService;
import pl.britenet.campuslu.command.GetProductsCommand;
import pl.britenet.campuslu.command.HelloCommand;
import pl.britenet.campuslu.database.DatabaseService;
import pl.britenet.campuslu.service.ProductService;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        ProductService productService = new ProductService(databaseService);

        CommandService commandService = new CommandService();
        commandService.registerCommand(new HelloCommand());
        commandService.registerCommand(new GetProductsCommand(productService));

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
