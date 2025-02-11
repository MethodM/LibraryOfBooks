package SimpleStartup;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Startup {
    String nome;
    private ArrayList<String> locationCells;
    //Startup[] startup = new Startup[3];

    public void setLocationCells(ArrayList<String> localizacao) {
        locationCells = localizacao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getLocationCells() {
        return locationCells;
    }

    public String checkYourself(String palpiteDoUsuario) {
        String resultado = "errou";
        int index = locationCells.indexOf(palpiteDoUsuario);

        if (index >= 0) {
            locationCells.remove(index);

            if(locationCells.isEmpty()) {
                resultado = "Afundou";
                System.out.println("Ouch! Você afundou " + nome + "  : ( ");
            } else {
                resultado = "Acertou";
            } // Fim do if
        } // Fim do if externo
        return resultado;
    }// Fim do método
}// Fecha a classe

