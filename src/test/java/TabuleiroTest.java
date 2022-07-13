import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    @Test
    public void showEmptyBoard(){
        Tabuleiro tabuleiro = new Tabuleiro();
        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│   │   │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │   │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │   │   │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertFalse(tabuleiro.venceu());
    }
    @Test
    public void markXOnFirstRowFirstColumn(){
        Tabuleiro tabuleiro = (new Tabuleiro()).mark(0, 0, "X");

        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│ X │   │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │   │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │   │   │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertFalse(tabuleiro.venceu());
    }
    @Test
    public void markOagainBeforeMarkingX(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "X")
            .mark(1, 0, "O")
            .mark(1, 1, "O")
            .mark(1, 2, "O");
        });
        assertEquals("Não é possível jogar O é o turno de X", exception.getMessage());
    }
    @Test
    public void markXagainBeforeMarkingO(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "X")
            .mark(1, 0, "O")
            .mark(1, 1, "X")
            .mark(1, 2, "X");
        });
        assertEquals("Não é possível jogar X é o turno de O", exception.getMessage());
    }
    @Test
    public void markXthenOthenB(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "X")
            .mark(1, 0, "O")
            .mark(1, 1, "B");
        });
        assertEquals("Somente dois caracteres podem ser usados ao mesmo tempo", exception.getMessage());
    }
    @Test
    public void xGanhaNaPrimeiraColuna(){
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "X")
            .mark(0, 1, "O")
            .mark(1, 0, "X")
            .mark(1, 1, "O")
            .mark(2, 0, "X");


        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│ X │ O │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│ X │ O │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│ X │   │   │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertTrue(tabuleiro.venceu());
    }
    @Test
    public void xGanhaNaDiagonal(){
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "X")
            .mark(0, 1, "O")
            .mark(1, 1, "X")
            .mark(1, 2, "O")
            .mark(2, 2, "X");


        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│ X │ O │   │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │ X │ O │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │   │ X │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertTrue(tabuleiro.venceu());
    }
    @Test
    public void xGanhaNaDiagonalOposta(){
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 2, "X")
            .mark(0, 1, "O")
            .mark(1, 1, "X")
            .mark(1, 2, "O")
            .mark(2, 0, "X");


        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│   │ O │ X │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│   │ X │ O │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│ X │   │   │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertTrue(tabuleiro.venceu());
    }
    @Test
    public void semMovimentosDisponiveis(){
        Tabuleiro tabuleiro = (new Tabuleiro())
            .mark(0, 0, "O")
            .mark(0, 2, "X")
            .mark(0, 1, "O")
            .mark(1, 0, "X")
            .mark(1, 2, "O")
            .mark(1, 1, "X")
            .mark(2, 0, "O")
            .mark(2, 2, "X")
            .mark(2, 1, "O")
            ;


        String expected = "┌───┬───┬───┐" + System.lineSeparator()
                        + "│ O │ O │ X │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│ X │ X │ O │" + System.lineSeparator()
                        + "├───┼───┼───┤" + System.lineSeparator()
                        + "│ O │ O │ X │" + System.lineSeparator()
                        + "└───┴───┴───┘";
        assertEquals(expected, tabuleiro.toString());
        assertFalse(tabuleiro.venceu());
        assertTrue(tabuleiro.acabou());
    }
    @Test
    public void tentativaDeSobrescrever(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            Tabuleiro tabuleiro = (new Tabuleiro())
                .mark(0, 0, "X")
                .mark(1, 0, "O")
                .mark(1, 0, "X");
        });
        assertEquals("Movimento inválido (casa já marcada)", exception.getMessage());
    }
    @Test
    public void tentativaDeMarcarCelulaInexistente(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()->{
            Tabuleiro tabuleiro = (new Tabuleiro())
                .mark(0, 3, "X");
        });
        assertEquals("Movimento inválido (casa inexistente)", exception.getMessage());
    }
}