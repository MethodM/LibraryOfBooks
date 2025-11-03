package SimpleStartup;

import java.util.ArrayList;

public class SimpleStartupTestDrive {
  public static void main(String[] args) {
    SimpleStartup game = new SimpleStartup();
    game.setUpGame(); //20- Solicita ao objeto 'game' para configurar o jogo
    game.startPlaying(); //21- Solicita ao objeto game para iniciar o loop principal do jogo (permanece solicitando a entrada do usuário e verificando o palpite)


    int numDePalpites = 0; // Declara uma variável para armanazenar a contagem de palpites do usuário e a define como 0
    GameHelper ajudante = new GameHelper(); // Fingir que existe uma classe aqui criada

    Startup theStartup = new Startup(); // Cria um objeto SimpleStartup
    int randomNum = (int) (Math.random() * 5); // Cria um número aleatório para a primeira célula
    // E a usa para criar o array das células locais

    ArrayList<String> locations = new ArrayList<>();
    locations.add(String.valueOf(randomNum));
    locations.add(String.valueOf(randomNum + 1));
    locations.add(String.valueOf(randomNum + 2));

    theStartup.setLocationCells(locations); // Invoca o método -> Refaturar locationCells em tudo depois
    boolean estaVivo = true; // Declara um boolean 'estaVivo' (isAlive)

    while (estaVivo == true) { // Enquanto 'estaVivo' for verdadeiro, continue o loop
      String palpite = ajudante.getUserInput("Insira um número");
      int palpiteInt = Integer.parseInt(palpite); // Entrada do usuário convertida para int

      String result = theStartup.checkYourself(String.valueOf(palpiteInt));

      numDePalpites++; // Incrementa a contagem de palpites em um

      if (result.equals("Afundou")) {
        estaVivo = false; // Não entraremos no loop novamente
        System.out.println("Você afundou a Startup em " + numDePalpites + " palpites");
      } // Fechamento do IF
    } // Fechamento do While
  } // Fechamento do Main
}