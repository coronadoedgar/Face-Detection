
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Deteccion extends JFrame{
	JPanel panelp;
    JPanel panel;
    JLabel etiqueta;
    JButton boton;
    boolean WEBCAM=false;
    
    public Deteccion(){
        setTitle("Reconocimiento de Rostros mediante Webcam con OpenCV y Java");
        setLocation(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setResizable(true);
        setVisible(true);
        panelp = new JPanel();
        panel = new JPanel();
        panelp.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        etiqueta = new JLabel();
        boton = new JButton("Iniciar");
        panel.add(etiqueta);
        panelp.add("Center",panel);
        panelp.add("South",boton);
        
        getContentPane().add(panelp);
        
        boton.addActionListener(new ActionListener(){
           @Override
            public void actionPerformed(ActionEvent e) {
                if(WEBCAM){
                    WEBCAM = false;
                    boton.setText("Iniciar");
                }else{
                    WEBCAM = true;
                    boton.setText("Detener");
                }
            }
        }); 
    }
    
    public void setImage(Image imagen){
        panel.removeAll();
        
        ImageIcon icon = new ImageIcon(imagen.getScaledInstance(etiqueta.getWidth(), etiqueta.getHeight(), Image.SCALE_SMOOTH));
        etiqueta.setIcon(icon);
        
        panel.add(etiqueta);
        panel.updateUI();
    }
}
