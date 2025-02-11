package SimpleStartup;
import java.util.ArrayList;

public class SimpleStartup {
    private GameHelper helper = new GameHelper();
    private int numDeHits = 0;
    private int numDePalpites = 0;
    ArrayList<Startup> startups = new ArrayList<Startup>();

    //1- Declara e inicializa as variáveis necessárias


    private void setUpGame() {
        // Cria algumas Startups e lhes fornece locais.
        Startup one = new Startup();
        one.setNome("Poniez");
        Startup two = new Startup();
        two.setNome("Hacqi");
        Startup tree = new Startup();
        tree.setNome("Cambridge");
        startups.add(one);
        startups.add(two);
        startups.add(tree);
        //2- Cria três objetos Startup, lhes fornece nomes e os insere no ArrayList

        System.out.println("Seu objetivo é afundar três Startups.");
        System.out.println("Your goal is to sink three Startups.");
        System.out.println("Piniez, Hacqi, Cambridge");
        System.out.println("Tente afundar todas elas no menor número de tentativas.");
        System.out.println("Try to sink them all in the fewest number of guesses.");
        //3- Printa breves instruções para o usuário

        for (Startup startup : startups) { // 4- Repete isso com cada Startup na lista
            ArrayList<String> newLocation = helper.placeStartup(3);
            // 5- Solicita ao auxiliar um local para a Startup (um ArrayList de Strings)
            startup.setLocationCells(newLocation);
            // 6- Chama o método setter nesta Startup para fornecer o local que acabou de ser obtido do auxiliar.
        }
    }

    public void startPlaying() {
        while (!startups.isEmpty()) { //7 - Desde que a lista de Startup NÃO esteja vazia
            // o '!' significa NÃO, é o mesmo que (startups.isEmpty() == false)
            String palpiteDoUsuario = helper.getUserInput("Insira o palpite");
            // 8- Obtém a entrada do usuário
            checkUserGuess(palpiteDoUsuario); // 9- Chama o nosso método checkYourself() ou CheckUserGuess
        } // Fecha o loop while
        finalizandoJogo(); //10- Chama nosso método finishGame() ou finalizandoJogo()
    } // Fecha o método startPlaying

    public String checkUserGuess(String palpites) { // Aqui que o palpite bugava
        numDePalpites++; //11- Incrementa o número de palpites que o usuário fez.
        String resultado = "errou"; //12- Assume que é um 'erro' a menos que seja dito o contrário

        for (Startup startupParaTest : startups) { //13- Repete isso com todas as Startups na lista
            resultado = startupParaTest.checkYourself(palpites);//14- Solicita à Startup para verificar o palpite do usuário, procurando por um acerto(ou abate)

            if (resultado.equals("Acertou")) {
                break; //15- Sai antes do loop, não adianta testar os outros
            }
            if (resultado.equals("Afundou")) {
                startups.remove(startupParaTest); //16- Esta foi abatida, então é removida da lista de Startups e sai do loop
                break;
            }
        } // Fecha o loop FOR
        System.out.println(resultado); //17- Exibe o resultado para o usuário
        return resultado;
    }

    private void finalizandoJogo() {
        System.out.println("Todas as Startups estão afundadas! Seu estoque agora não vale nada");
        System.out.println("All Startups are dead! Your stock is now worthless");
        if (numDePalpites <= 18) {
            System.out.println("Você levou " + numDeHits + " palpites.");
            System.out.println("Você saiu antes que suas opções afundassem");
        } else {
            System.out.println("Demorou bastante. " + numDeHits + " palpites.");
            System.out.println("Peixes está dançando com suas opções");
        } // 18- Printa uma mensagem contando ao usuário como ele se saiu no jogo
    } // Fecha o método

    public static void main(String [] args) {
        SimpleStartup game = new SimpleStartup(); //19- Cria o objeto game
        game.setUpGame(); //20- Solicita ao objeto 'game' para configurar o jogo
        game.startPlaying(); //21- Solicita ao objeto game para iniciar o loop principal do jogo (permanece solicitando a entrada do usuário e verificando o palpite)
    }
}
        //int indice = locationCells.indexOf(palpites);
        // for (int cell: locationCells) {
        /*if (indice >= 0) { // Foi aqui que tudo deu errado. Contamos cada palpite como um acerto(hit)
            locationCells.remove(indice); // Sem verificar se a célula já havia sido acertada
            if (locationCells.isEmpty()) {
                resultado = "Afundou";
                //numDeHits++;
                //break;
            } else {
                resultado = "Acertou";
            }
            //if (numDeHits == locationCells.length) {
            //    resultado = "Afundou";
        }
        // System.out.println(resultado);
        return resultado;
    } // Fim do Método checkYourself
} // Fecha a classe*/