import javax.swing.*;
import java.awt.*;

/**
 * Class gameScreen
 * Classe pour afficher des questions et fournir des réponses
 * Collège de Rosemont
 * 26/02/2016
 * @author Raphaël Bouchard
 */
public class gameScreen extends SimpleFrame{

    //
    // Fields
    //
    JTextArea questionArea;
    JLabel reponseLabel;
    JTextField reponse;
    JScrollPane scroll;
    JPanel panel2;

    
    //
    // Constructors
    //
    public gameScreen () {
        super();
        panel2 = new JPanel();
        questionArea = new JTextArea ();
        questionArea.setEditable(false);
        scroll = new JScrollPane (questionArea);
        scroll.setSize(new Dimension(240,240));
        reponse = new JTextField(10);
        reponseLabel = new JLabel("Réponse");
        this.getPanel().add(reponseLabel);
        this.getPanel().add(reponse);
        this.add(scroll, BorderLayout.CENTER);
        this.add(this.getPanel(), BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setSize(270,300);
        this.setVisible(true);
    }
    
    //
    // Methods
    //

    public JPanel getPanel2() {
        return panel2;
    }


    //
    // Accessor methods
    //

    //
    // Other methods
    //

}
