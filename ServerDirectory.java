
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ServerDirectory {

    public static void main(String[] args) {

        // Create ArrayList
        ArrayList<Server> servers = new ArrayList<>();

        // Create HashMap
        HashMap<String, Server> serverMap = new HashMap<>();

        // Add five servers
        addServer(servers, serverMap,
                new Server("WebServer", "192.168.1.10", 80, "HTTP"));

        addServer(servers, serverMap,
                new Server("SecureServer", "192.168.1.20", 443, "HTTPS"));

        addServer(servers, serverMap,
                new Server("FileServer", "192.168.1.30", 21, "FTP"));

        addServer(servers, serverMap,
                new Server("MailServer", "192.168.1.40", 25, "SMTP"));

        addServer(servers, serverMap,
                new Server("DatabaseServer", "192.168.1.50", 3306, "MYSQL"));

        Scanner input = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("\n===== SERVER DIRECTORY =====");
            System.out.println("1. Display All Servers");
            System.out.println("2. Search By Protocol");
            System.out.println("3. Search Server By Name");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("\nAll Servers:");

                    for (Server server : servers) {
                        server.display();
                    }
                    break;

                case 2:
                    System.out.print("Enter protocol (HTTP, FTP, etc): ");
                    String protocol = input.nextLine();

                    boolean found = false;

                    for (Server server : servers) {

                        if (server.getProtocol()
                                .equalsIgnoreCase(protocol)) {

                            server.display();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No server found with this protocol.");
                    }
                    break;

                case 3:
                    System.out.print("Enter server name: ");
                    String name = input.nextLine();

                    Server result = serverMap.get(name);

                    if (result != null) {
                        System.out.println("Server Found:");
                        result.display();
                    } else {
                        System.out.println("Server not found.");
                    }
                    break;

                case 4:
                    System.out.println("Program closed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        input.close();
    }

    // Add server to ArrayList and HashMap
    public static void addServer(
            ArrayList<Server> servers,
            HashMap<String, Server> serverMap,
            Server server) {

        if (serverMap.containsKey(server.getName())) {
            throw new IllegalArgumentException("Duplicate server name");
        }

        servers.add(server);
        serverMap.put(server.getName(), server);
    }
}
