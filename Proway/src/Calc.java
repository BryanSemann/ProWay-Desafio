import javax.swing.JOptionPane;

public class Calc {
	Crud crud = new Crud();
    int jogo, placar, minimoTemporada=1000, maximoTemporada, recordeMaximo, recordeMinimo;

    public void verifica(int n) {

        if (n >= 0 && n <= 1000) {
            for (Pontos p : crud.read("SELECT * FROM Basquete ORDER BY jogo DESC LIMIT 1;")) {
            	jogo = p.getJogo();
                placar = p.getPlacar();
                maximoTemporada = p.getPts_max();
                minimoTemporada = p.getPts_min();
                recordeMaximo = p.getPts_recMax();
                recordeMinimo = p.getPts_recMin();
            }
            Pontos p = new Pontos();

            if (n > maximoTemporada) {
                p.setPts_max(n);
                p.setPts_recMax(recordeMaximo + 1);
            } else {
                p.setPts_max(maximoTemporada);
                p.setPts_recMax(recordeMaximo);
            }

            if (n < minimoTemporada && minimoTemporada !=0) {
                if (minimoTemporada == 0) {
                    p.setPts_min(n);
                    p.setPts_recMin(recordeMinimo);
                }
                if (n == 0) {
                    p.setPts_min(n);
                    p.setPts_recMin(recordeMinimo);
                }

                p.setPts_min(n);
                p.setPts_recMin(recordeMinimo+ 1);
            } else {
                p.setPts_min(minimoTemporada);
                p.setPts_recMin(recordeMinimo);
            }

            p.setPlacar(n);
            crud.create(p);
        } else {
            JOptionPane.showMessageDialog(null, "Numero invalido");
        }
    }
}
