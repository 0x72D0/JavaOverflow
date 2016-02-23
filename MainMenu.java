import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class MainMenu
 * fenêtre du menu principal
 * 23 février 2016
 * Collège De Rosemont
 * @author Raphaël Bouchard
 */
public class MainMenu extends SimpleFrame implements ActionListener{

    //
    // Fields
    //
    private JButton commencer,options,quitter;
    //
    // Constructors
    //
    public MainMenu () {

        super();
        commencer = new JButton("commencer");
        commencer.setForeground(Color.yellow);
        commencer.addActionListener(this);
        options = new JButton("options");
        options.setForeground(Color.cyan);
        options.addActionListener(this);
        quitter = new JButton("quitter");
        quitter.setForeground(Color.red);
        quitter.addActionListener(this);
        getPanel().add(commencer);
        getPanel().add(options);
        getPanel().add(quitter);
        this.add(getPanel());
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
     * Override actionPerformed from interface ActionListener
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event){

        if (event.getSource()==commencer){
            //To complete
        }

        if(event.getSource()==options){
            //Same
        }

        if(event.getSource()==quitter){
            System.exit(0);
        }
    }
}
