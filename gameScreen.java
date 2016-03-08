import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class gameScreen
 * Classe pour afficher des questions et fournir des réponses
 * Collège de Rosemont
 * 26/02/2016
 * @author Raphaël Bouchard
 */
public class gameScreen extends SimpleFrame implements ActionListener{

    //
    // Fields
    //
    JTextArea questionArea;
    JLabel reponseLabel;
    JTextField reponse;
    JScrollPane scroll;

    
    //
    // Constructors
    //
    public gameScreen () 
	{
        super();
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
        
		JavaOverflow.generateQuestion();
		questionArea.setText(JavaOverflow.cwq.getEnonce());
    }
    
    //
    // Methods
    //

    //
    // Accessor methods
    //

    //
    // Other methods
    //

	@Override
	public void actionPerformed(ActionEvent event)
	{

	}

}
