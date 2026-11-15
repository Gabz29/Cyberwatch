import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CyberWatchWindow extends JFrame implements ActionListener{

    private SecurityManager manager;
    private JButton boutonAnalyse;
    private JButton boutonActualiser;
    private JButton boutonReinitialiser;

    public CyberWatchWindow(SecurityManager manager){
        super("CyberWatch");
        this.manager = manager;

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setSize(800,200);
        this.setLayout(new BorderLayout(0, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // =============================
        // ======= PARTIE HAUTE ========
        // =============================
        JPanel panelStats = new JPanel(new GridLayout(1, 5, 10, 0));
        int[] minMax = this.manager.getMaxMinScore();
        panelStats.add(new JLabel("Total événements : " + this.manager.getTotalEvent()));
        panelStats.add(new JLabel("Échec de connexion : " + this.manager.getTotalFailureLogin()));
        panelStats.add(new JLabel("Activités suspectes : " + this.manager.getTotalSuspiciousActivity()));
        panelStats.add(new JLabel("Évènements critiques : " + this.manager.getTotalCriticalEvent()));
        panelStats.add(new JLabel("Min/Max : " + minMax[0] + " / " + minMax[1]));
        this.add(panelStats, BorderLayout.NORTH);

        // =============================
        // ======= PARTIE CENTRE =======
        // =============================
        List<SecurityEvent> journal = manager.getJournal();

        // 1. AFFICHAGE DU JOURNAL D'EVENEMENTS
        String[] colonnes = {"Id", "Date/Heure", "Type d'événement", "Adresse IP", "Score de Risque", "Détails spécifiques"};
        Object[][] donnees = new Object[journal.size()][6];

        for(int i = 0; i < journal.size(); i++){
            SecurityEvent e = journal.get(i);

            donnees[i][0] = e.getId();

            donnees[i][1] = e.getDate();
            
            donnees[i][2] = e.getClass().getSimpleName();

            donnees[i][3] = e.getIpAddress();

            donnees[i][4] = e.getRiskScore();

            if(e instanceof SuspiciousActivity){
                SuspiciousActivity a = (SuspiciousActivity) e;
                donnees[i][5] = "Type d'activité" + a.getActivityType() + "\n Volume de données" + a.getDataVolumeMo() + "\nAntivirus score" + a.getSeverityLevel();
            }

            else if(e instanceof LoginFailure){
                LoginFailure a = (LoginFailure) e;
                donnees[i][5] = "" + a.getNbFailure();
            }

            else if(e instanceof LoginSuccess){
                LoginSuccess a = (LoginSuccess) e;
                donnees[i][5] = "User : " + a.getUsername() + "\nNot working hours" + a.getOutsideWorkingHours() + "\nNew device" + a.getIsNewDevice();
            }
        }

        JTable tableEvents = new JTable(donnees, colonnes);
        tableEvents.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tableEvents);

        // 2. AFFICHAGE DU GRAPHIQUE DE REPARTITION DES SCORES
        JPanel grapheEvents = new JPanel(new GridLayout(10, 1, 0, 5));
        grapheEvents.setBorder(BorderFactory.createTitledBorder("Répartition des scores de risque (0 à 9)"));
        grapheEvents.setPreferredSize(new Dimension(500, 0));

        // Récupération des données du manager
        int[] repartition = this.manager.getRepartitionScore();

        // Calcul de la valeur maximale pour calibrer l'échelle des barres
        int maxEvt = 0;
        for (int val : repartition) {
            if (val > maxEvt) {
                maxEvt = val;
            }
        }
        if (maxEvt == 0) { maxEvt = 1; } // Évite la division par zéro

        // Construction des 10 lignes du graphique (Score 0 à 9)
        for (int score = 0; score < repartition.length; score++) {
            JPanel ligneScore = new JPanel(new BorderLayout(5, 0));

            // Libellé du score
            JLabel labelScore = new JLabel("Score " + score + " :");
            labelScore.setPreferredSize(new Dimension(65, 20));

            // Barre de progression pour le volume
            JProgressBar barre = new JProgressBar(0, maxEvt);
            barre.setValue(repartition[score]);
            barre.setStringPainted(true);
            barre.setString(repartition[score] + " événement(s)");

            // Code couleur dynamique selon la gravité du score
            if (score >= 7) {
                barre.setForeground(new Color(220, 53, 69)); // Rouge
            } else if (score >= 4) {
                barre.setForeground(new Color(255, 193, 7)); // Orange
            } else {
                barre.setForeground(new Color(40, 167, 69)); // Vert
            }

            ligneScore.add(labelScore, BorderLayout.WEST);
            ligneScore.add(barre, BorderLayout.CENTER);

            grapheEvents.add(ligneScore);
        }

        // 3. PANEL PRINCIPAL
        JPanel panelEvents = new JPanel(new BorderLayout(10, 0));
        // Ajout de la partie gauche et droite au panel principal
        panelEvents.add(scrollPane, BorderLayout.CENTER);
        panelEvents.add(grapheEvents, BorderLayout.EAST);

        //
        this.add(panelEvents, BorderLayout.CENTER);

        // =============================
        // ======= PARTIE BASSE ========
        // =============================
        JPanel panelActions = new JPanel(new GridLayout(1, 3, 10, 0));
        // Bouton analyse
        this.boutonAnalyse = new JButton("Lancer l'analyse");
        this.boutonAnalyse.addActionListener(this);
        panelActions.add(this.boutonAnalyse);
        // Bouton actualiser
        this.boutonActualiser = new JButton("Actualiser");
        this.boutonActualiser.addActionListener(this);
        panelActions.add(this.boutonActualiser);
        // Bouton réinitialiser
        this.boutonReinitialiser = new JButton("Réinitialiser");
        this.boutonReinitialiser.addActionListener(this);
        panelActions.add(this.boutonReinitialiser);
        this.add(panelActions, BorderLayout.SOUTH);
        // Toujours à la fin du constructeur
        this.setVisible(true);
    }

    // Appelée automatiquement à chaque clic sur l'un des trois boutons
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.boutonAnalyse){
            this.boutonAnalyse.setText("Analyse lancée !");
        }

        if(e.getSource() == this.boutonActualiser){
            this.boutonActualiser.setText("Fenêtre actualisée !");
        }

        if(e.getSource() == this.boutonReinitialiser){
            this.boutonReinitialiser.setText("Fenêtre réinitialisée !");
        }
    }
}
