
import java.util.*;


/**
 * Class SimpleFrame
 * @author LucasMongrain, 23 fevrier 2016
 */
public class SimpleFrame  extends JFrame{

    //
    // Fields
    //
	JPanel panel;
    
    //
    // Constructors
    //
    public SimpleFrame () {
	    try
		{
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setvisible(true);
				this.setResizable(true);
		}
	 	catch(Exception e)
	 	{
			e.printStackTrace();
	 	}
	};
    
    //
    // Methods
    //


	@override
	public void windowClosing(WindowEvent event)
	{
		try
		{
			int signal = JOptionPane.showConfirmDialog(null, "etes-vous sur de vouloir quitter, les parametres non sauvegarder serons effacer", "JavaOverflow", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION );
		
			if(rep == JOptionPane.OK_OPTION)
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

}
