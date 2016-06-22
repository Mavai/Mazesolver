/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazesolver.GUI;

import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Marko Vainio
 */
public class SliderListener implements ChangeListener{
    private final JLabel heightLabel;
    private final JLabel widthLabel;
    private final JLabel speedLabel;

    public SliderListener(JLabel heightLabel, JLabel widthLabel, JLabel speedLabel) {
        this.heightLabel = heightLabel;
        this.widthLabel = widthLabel;
        this.speedLabel = speedLabel;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        switch (source.getName()) {
            case "height":
                heightLabel.setText("Korkeus: " + source.getValue());
                break;
            case "width":
                widthLabel.setText("Leveys: " + source.getValue());
                break;
            case "speed":
                speedLabel.setText("Nopeus: " + source.getValue());
                break;
        }
    }
    
}
