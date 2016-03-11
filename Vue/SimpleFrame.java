package Vue;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


/**
 * Class SimpleFrame
 * @author LucasMongrain, 23 fevrier 2016
 */
public class SimpleFrame  extends JFrame implements WindowListener{

    //
    // Fields
    //
	JPanel panel;
    
    //
    // Constructors
    //

    /**
     *
     * Constructeur de la classe
     */
    public SimpleFrame () {
        super("JavaOverflow");
	    try
		{
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                panel = new JPanel();
				this.addWindowListener(this);

		}
	 	catch(Exception e)
	 	{
			e.printStackTrace();
	 	}
	};
    
    //
    // Methods
    //

	/**
	 * methode pour avertir l'utilisateur que les données ne seront pas sauvegardées
     * Override windowClosing from interface windowListener
	 * @param event the WindowEvent Object created when the windows closes
     */
	@Override
	public void windowClosing(WindowEvent event)
	{
		try
		{
			int signal = JOptionPane.showConfirmDialog(null, "etes-vous sur de vouloir quitter, les parametres non sauvegarder serons effacer", "JavaOverflow", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION );
		
			if(signal == JOptionPane.OK_OPTION)
			{
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


    //
    // Accessor methods
    //


	JPanel getPanel()
	{
		return panel;
	}

	void setPanel(JPanel pan)
	{
		panel = pan;
	}

    //
    // Other methods
    //

    public void windowActivated(WindowEvent event){

    }

    public void windowClosed(WindowEvent event){

    }

    public void windowDeiconified(WindowEvent event){

    }

    public void windowIconified(WindowEvent event){

    }

    public void windowOpened(WindowEvent event){

    }

    public void windowDeactivated(WindowEvent event){

    }
}
