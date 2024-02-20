import java.util.Scanner;

public class MegaSena {

  static final int numero_dezenas = 6;

  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    int[] sorteio = sorteaSena();
    int[] aposta = new int[numero_dezenas];

    System.out.println("Faça sua aposta (Digite números de 1 a 60):");
    for (int i = 0; i < numero_dezenas; i++) {
      int numeroAposta;
      boolean repetido;

      do {
        System.out.println("Informe a dezena " + (i + 1) + ":");
        numeroAposta = teclado.nextInt();

        if (numeroAposta <= 0 || numeroAposta > 60) {
          System.out.println("Número inválido, aposta cancelada");
          teclado.close();
          return;
        }

        repetido = existeNumero(aposta, numeroAposta);

        if (repetido) {
          System.out.println("Ops, Números Repetidos! Escolha outro.");
        }
      } while (repetido);

      aposta[i] = numeroAposta;
    }

    System.out.println("Confira suas apostas:");
    for (int numero : aposta) {
      System.out.println(numero);
    }

    System.out.println("Resultado do Sorteio:");
    for (int numero : sorteio) {
      System.out.println(numero);
    }

    int nroAcertos = contaAcertos(sorteio, aposta);
    System.out.println("Número de acertos: " + nroAcertos);

    switch (nroAcertos) {
      case 4:
        System.out.println("Parabéns, você acertou a Quadra");
        break;
      case 5:
        System.out.println("Parabéns, você acertou a Quina");
        break;
      case 6:
        System.out.println("Parabéns, você é o campeão da Mega-Sena");
        break;
      default:
        System.out.println("Não foi dessa vez, tente novamente");
        break;
    }

    teclado.close();
  }

  // Retorna uma array com 6 números gerados randomicamente (API do Java), sem duplicidade.
  static int[] sorteaSena() {
    int[] resultado = new int[numero_dezenas];

    for (int i = 0; i < numero_dezenas; i++) {
      int sorteado;
      boolean repetido;

      do {
        sorteado = (int) (Math.random() * 60) + 1; // Número aleatório de 1 a 60
        repetido = existeNumero(resultado, sorteado);
      } while (repetido); // Evita repetição de número

      resultado[i] = sorteado;
    }
    return resultado;
  }

  // Compara cada número apostado com os números sorteados e retorna a quantidade de acertos.
  static int contaAcertos(int[] sorteio, int[] aposta) {
    int acertos = 0;
    for (int numeroAposta : aposta) {
      if (existeNumero(sorteio, numeroAposta)) {
        acertos++;
      }
    }
    return acertos;
  }

  // Indica se o número n existe no array numeros. Útil para validar duplicidade de dados.
  static boolean existeNumero(int[] numeros, int n) {
    for (int numero : numeros) {
      if (numero == n) {
        return true;
      }
    }
    return false;
  }
}
