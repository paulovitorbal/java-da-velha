public class Tabuleiro {
    private static final String BLANK = " ";
    private String[] valores = {BLANK,BLANK,BLANK,BLANK,BLANK,BLANK,BLANK,BLANK,BLANK};
    private final String template = "┌───┬───┬───┐" + System.lineSeparator()
                                  + "│ 1 │ 2 │ 3 │" + System.lineSeparator()
                                  + "├───┼───┼───┤" + System.lineSeparator()
                                  + "│ 4 │ 5 │ 6 │" + System.lineSeparator()
                                  + "├───┼───┼───┤" + System.lineSeparator()
                                  + "│ 7 │ 8 │ 9 │" + System.lineSeparator()
                                  + "└───┴───┴───┘";

    public Tabuleiro(String[] valores) {
        this.valores = valores;
        validate();
    }
    public Tabuleiro() {
    }

    public String toString(){
        String s = template;
        for(int x =0; x<9; x++) {
            s = s.replace(
                String.valueOf((1 + x)),
                valores[x]
            );
        }
        return s;
    }
    public Tabuleiro mark(int x, int y, String letter) {

        String[] v = valores;

        if (x < 0 || x > 2 || y < 0 || y > 2) {
            throw new RuntimeException("Movimento inválido (casa inexistente)");
        }

        int casa = y+(x*3);

        if (!v[casa].equals(BLANK)){
            throw new RuntimeException("Movimento inválido (casa já marcada)");
        }


        v[casa] = letter;
        return new Tabuleiro(valores);
    }
    public boolean venceu(){
        int[][] combinacoes = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6},
        };
        boolean retorno = false;
        for(int[] combinacao:combinacoes){
            if (!retorno){
                retorno = check3casas(combinacao[0], combinacao[1], combinacao[2]);
            }
        }
        return retorno;
    }
    private boolean check3casas(int casa1, int casa2, int casa3){
        return valores[casa1].equals(valores[casa2]) && valores[casa2].equals(valores[casa3]) && !valores[casa1].equals(BLANK);
    }

    private void validate() {
        String A = BLANK;
        int countA = 0;
        String B = BLANK;
        int countB = 0;
        for (String x:valores) {
            if (x.equals(BLANK)){
                continue;
            }
            if (countA == 0) {
                A = x;
            }
            if (x.equals(A)){
                countA++;
                continue;
            }
            if (countB == 0) {
                B = x;
            }
            if (x.equals(B)){
                countB++;
                continue;
            }
            if (!x.equals(A) && !x.equals(B)){
                throw new RuntimeException("Somente dois caracteres podem ser usados ao mesmo tempo");
            }
        }
        if (countA - countB > 1){
            throw new RuntimeException("Não é possível jogar " + A + " é o turno de " + B);
        }
        if (countB - countA > 1){
            throw new RuntimeException("Não é possível jogar " + B + " é o turno de " + A);
        }
    }

    public boolean acabou() {
        for (String casa:valores) {
            if (casa.equals(BLANK)){
                return false;
            }
        }
        return true;
    }
}
