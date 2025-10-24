import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
public class SimpleStartup {
  private GameHelper helper = new GameHelper();
  private int numDePalpites = 0;
  private ArrayList<Startup> startups = new ArrayList<>();
  private ArrayList<String> palpitesFeitos = new ArrayList<>();

  private ArrayList<String> hits = new ArrayList<>();
  private ArrayList<String> misses = new ArrayList<>();

  public void setUpGame() {
    Startup one = new Startup();
    one.setNome("Poniez");
    Startup two = new Startup();
    two.setNome("Hacqi");
    Startup three = new Startup();
    three.setNome("Cambridge");
    startups.add(one);
    startups.add(two);
    startups.add(three);

    System.out.println("Seu objetivo é afundar três Startups.");
    System.out.println("Poniez, Hacqi, Cambridge");
    System.out.println("Tente afundar todas elas no menor número de tentativas.");

    ArrayList<String> posicoesUsadas = new ArrayList<>();
    for (Startup startup : startups) {
      ArrayList<String> newLocation;
      do {
        newLocation = helper.placeStartup(3);
      } while (!posicoesValidas(newLocation, posicoesUsadas));
      posicoesUsadas.addAll(newLocation);
      startup.setLocationCells(newLocation);
      // Opcional: DEBUG — mostra onde cada startup ficou (remova em produção)
      // System.out.println("DEBUG: " + startup.getNome() + " -> " + newLocation);
    }
  }

  public void startPlaying() {
    helper.printBoard(helper.getGridLength(), hits, misses);

    while (!startups.isEmpty()) {
      String entrada = helper.getUserInput("Insira uma posição (ex: A0 ou 1 - " +
          (helper.getGridLength() * helper.getGridLength() + ")"));
      String palpiteNormalizado = helper.normalizeGuess(entrada);

      if (palpiteNormalizado == null) {
        System.out.println("Palpite inválido! Digite algo como A0 ou um número entre 1 e " +
            (helper.getGridLength() * helper.getGridLength()) + ".");
        continue; // volta para pedir outro palpite — NÃO "return"
      }

      // usa o valor normalizado
      checkUserGuess(palpiteNormalizado);
      helper.printBoard(helper.getGridLength(), hits, misses);
    }

    finalizandoJogo();
  }


  private boolean posicoesValidas(ArrayList<String> novaPosicao, ArrayList<String> usadas) {
    for (String posicao : novaPosicao)
      if (usadas.contains(posicao)) {
        return false;
      }
    return true;
  }

  public void checkUserGuess(String userGuess) {
    if (userGuess == null) return;
    String palpite = userGuess.toUpperCase();

    if (palpitesFeitos.contains(palpite)) {
      System.out.println("Você já tentou essa posição! Escolha outra");
      return;
    }
    palpitesFeitos.add(palpite);
    numDePalpites++;

    Iterator<Startup> it = startups.iterator();
    String resultado = "errou";
    boolean wasHit = false;

    while (it.hasNext()) {
      Startup startup = it.next();
      resultado = startup.checkYourself(palpite);
      if ("Acertou".equals(resultado)) {
        wasHit = true;
        break;
      } else if ("Afundou".equals(resultado)) {
        wasHit = true;
        it.remove();
        break;
      }
    }

    // registra palpite em hits OU misses
    if (wasHit) {
      if (!hits.contains(palpite)) hits.add(palpite);
    } else {
      if (!misses.contains(palpite)) misses.add(palpite);
    }
    System.out.println(resultado);

    // DEBUG (temporário): inspeção do estado — remova estas linhas quando estiver ok
    System.out.println("DEBUG palpitesFeitos=" + palpitesFeitos);
    System.out.println("DEBUG hits=" + hits);
    System.out.println("DEBUG misses=" + misses);

    // e também debug das startups restantes
    List<String> remaining = new ArrayList<>();
    for (Startup s : startups) {
      if (s.getLocationCells() != null) remaining.addAll(s.getLocationCells());
    }
    System.out.println("DEBUG startups remaining cells=" + remaining);

  } // Fim de CheckUserGuess(...)


  private void finalizandoJogo() {
    System.out.println("Todas as Startups estão afundadas! Seu estoque agora não vale nada");
    if (numDePalpites <= 15) {
      System.out.println("Você levou " + numDePalpites + " palpites.");
      System.out.println("Você saiu antes que suas opções afundassem");
    } else {
      System.out.println("Demorou bastante. " + numDePalpites + " palpites.");
      System.out.println("Peixes está dançando com suas opções");
    }
  }
}

//System.out.println(resultado);
    /*String resultado = "errou";
    for (Startup startup : new ArrayList<>(startups)) {
      resultado = startup.checkYourself(palpiteInt);
      if (resultado.equals("Acertou")) {
        break;
      }
      if (resultado.equals("Afundou")) {
        startups.remove(startup);
        break;
      }*/
//} else if {"Afundou".equals(resultado)} { startups.remove(startup); break; }
    /*}
    System.out.println(resultado);*/

  /*public static void main(String[] args) {
    SimpleStartup game = new SimpleStartup();
    game.setUpGame();
    game.startPlaying();
  }*/

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