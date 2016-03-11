package Vue;

import Controlleur.JavaOverflow;

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
    JButton confirmer,nouvelleQuestion;

    
    //
    // Constructors
    //

    /**
     *
     * Constructeur de la fenetre affichant la question et donnant la possibilité de fournir une réponse
     */
    public gameScreen () 
	{
        super();
        questionArea = new JTextArea ();
        questionArea.setEditable(false);
        scroll = new JScrollPane (questionArea);
        scroll.setSize(new Dimension(240,240));
        reponse = new JTextField(10);
        reponseLabel = new JLabel("Réponse");
        confirmer  = new JButton("confirmer");
        confirmer.addActionListener(this);
        nouvelleQuestion = new JButton("nouvelle question");
        nouvelleQuestion.addActionListener(this);
        this.getPanel().add(reponseLabel);
        this.getPanel().add(reponse);
        this.getPanel().add(confirmer);
        this.getPanel().add(nouvelleQuestion);
        this.add(scroll, BorderLayout.CENTER);
        this.add(this.getPanel(), BorderLayout.PAGE_END);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(true);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
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

    /**
     *
     * method ecouteur des boutons
     * @param event actionevent object created by the buttons
     */
	@Override
	public void actionPerformed(ActionEvent event)
	{
        if (event.getSource()==confirmer)
            if(JavaOverflow.verifyStringAnswer(reponse.getText())) {
                JOptionPane.showMessageDialog(null, "Bravo", "JavaOverflow", JOptionPane.INFORMATION_MESSAGE, null);
                JavaOverflow.generateQuestion();
                questionArea.setText(JavaOverflow.cwq.getEnonce());
                reponse.setText("");
            }
            else
                JOptionPane.showMessageDialog(null,"Il y a du travail à faire.", "JavaOverflow", JOptionPane.INFORMATION_MESSAGE,null );
        else if(event.getSource()==nouvelleQuestion)
            JavaOverflow.generateQuestion();
        questionArea.setText(JavaOverflow.cwq.getEnonce());
	}

}
