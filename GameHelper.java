package SimpleStartup;
import java.util.Scanner;
import java.util.ArrayList;

public class GameHelper {
    private Scanner scanner = new Scanner(System.in); // Mantém um único Scanner

    public String getUserInput(String prompt) {
        System.out.print(prompt + ": ");
        //Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim(); // Captura e retorna a entrada do usuário
        //return null;
    }
    public ArrayList<String> placeStartup (int size) {
        ArrayList<String> location = new ArrayList<>(); // Simula posições para Startup(conforme necessário)
        for (int i = 0; i < size; i++) {
            location.add(String.valueOf(i)); //Simula posição para a Startup
        }
        return location;
    }
}
