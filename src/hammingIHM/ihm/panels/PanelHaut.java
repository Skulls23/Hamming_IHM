package hammingIHM.ihm.panels;

import hammingIHM.ihm.FrameP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The type Panel haut.
 */
public class PanelHaut extends JPanel
{
    /**
     * The Frame.
     */
    FrameP frame;
    /**
     * The Txtf binaire.
     */
    JTextField txtfBinaire;
    /**
     * The Btn valider.
     */
    JButton btnValider;
    /**
     * The Pnl droite.
     */
    JPanel pnlDroite;

    JRadioButton calcul;
    JRadioButton verification;

    /**
     * Instantiates a new Panel haut.
     *
     * @param frame the frame
     */
    public PanelHaut(FrameP frame)
    {
        this.setLayout(new BorderLayout());
        this.frame = frame;
        this.pnlDroite = new JPanel(new BorderLayout());

        this.txtfBinaire = new JTextField()
        {
            @Override
            public void paste()
            {

            }
        };
        this.txtfBinaire.setColumns(25);

        this.calcul       = new JRadioButton("calcul");
        this.verification = new JRadioButton("verif");
        ButtonGroup group = new ButtonGroup();
        group.add(this.calcul);
        group.add(this.verification);

        this.calcul.setSelected(true);

        this.txtfBinaire.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char key = e.getKeyChar();

                if( key == KeyEvent.VK_ENTER)
                {
                    PanelHaut.this.executerCode();

                    e.consume();
                    return;
                }

                if (key != '0' && key != '1')
                {
                    try
                    {
                        if( key != KeyEvent.VK_BACK_SPACE )
                            Toolkit.getDefaultToolkit().beep();
                    }
                    catch (Exception exception)
                    {
                        exception.printStackTrace();
                    }

                    e.consume();
                }
            }
        });

        this.btnValider = new JButton("GO");
        this.btnValider.addActionListener(e -> this.executerCode());

        JPanel panelRadioBtn = new JPanel();
        panelRadioBtn.setLayout(new BoxLayout(panelRadioBtn, BoxLayout.Y_AXIS));

        panelRadioBtn.add(this.calcul);
        panelRadioBtn.add(this.verification);

        this.add(this.txtfBinaire, BorderLayout.CENTER);
        this.add(pnlDroite, BorderLayout.EAST);
        this.pnlDroite.add(panelRadioBtn, BorderLayout.CENTER);
        this.pnlDroite.add(this.btnValider, BorderLayout.EAST);
    }

    private void executerCode()
    {
        if( this.calcul.isSelected() )
        {
            String codeCorriger = this.frame.calculeCodeEmission(this.txtfBinaire.getText());
        }
        else if( this.verification.isSelected() )
        {
            int erreur = this.frame.isCodeCorrect(this.txtfBinaire.getText());
        }
    }

    /**
     * Retour string binaire string.
     *
     * @return the string
     */
    public String retourStringBinaire()
    {
        return txtfBinaire.getText();
    }
}
