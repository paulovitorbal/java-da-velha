import java.io.Console;

import static java.lang.System.console;

public class JogoDaVelha implements Runnable{
    private Console console;

    public JogoDaVelha(Console console) {
        this.console = console;
    }

    @Override
    public void run() {
        Tabuleiro tabuleiro = new Tabuleiro();
        int[] jogada = null;
        int count = 1;
        while(!tabuleiro.venceu() && !tabuleiro.acabou()){
            printTabauleiro(tabuleiro);
            try{
                jogada = lerJogada();
                tabuleiro = tabuleiro.mark(jogada[0], jogada[1], (count % 2 == 0) ? "X" : "O");
                count++;
            }catch (RuntimeException e){
                this.console.writer().println(e.getMessage());
                this.console.readLine("pressione qualquer tecla para continuar.");
            }
        }
        printTabauleiro(tabuleiro);
        this.console.writer().println("Acabou o jogo!");
        if (tabuleiro.venceu()) {
            this.console.writer().println("Alguém venceu!");
        }

    }

    private void printTabauleiro(Tabuleiro tabuleiro) {
        this.console.writer().print("\033[H\033[2J");
        this.console.flush();
        this.console.writer().println(tabuleiro);
    }

    private int[] lerJogada() {
        this.console.writer().println("Qual a próxima jogada.");
        final int linha = input(this.console.readLine("Linha? "));
        final int coluna = input(this.console.readLine("Coluna? "));

        return new int[] {linha, coluna};
    }

    private int input(String x) {
        int y = Integer.parseInt(x);
        if (y < 1 || y > 3){
            throw new RuntimeException("Valor invalido (informe um número entre 1 e 3)");
        }
        return y-1;
    }

    public static void main(final String... args) {
        new JogoDaVelha(System.console()).run();
    }
}
