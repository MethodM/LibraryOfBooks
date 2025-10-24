package SimpleStartup;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Startup {
  String nome;
  private ArrayList<String> locationCells;
  //Startup[] startup = new Startup[3];

  public void setLocationCells(ArrayList<String> locs) {
    locationCells = locs;
  }

  public ArrayList<String> getLocationCells() {
    return locationCells;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String checkYourself(String palpiteDoUsuario) {
    String resultado = "errou";
    int index = locationCells.indexOf(palpiteDoUsuario);
    if (locationCells == null) return resultado;

    if (index >= 0) {
      locationCells.remove(index);
      if (locationCells.isEmpty()) {
        resultado = "Afundou";
        System.out.println("Ouch! Você afundou " + nome + "  : ( ");
      } else {
        resultado = "Acertou";
      } // Fim do if
    } // Fim do if externo
    return resultado;
  }// Fim do método
}// Fecha a classe

