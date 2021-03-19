import java.awt.*;
import java.io.*;
import javax.swing.*;
  
public class Orientation {
    
    
    public static void main(String[] args)
    {
        String text = "Description copied from class: Component\n" +
            "Sets the language-sensitive orientation that is to be " +
            "used to order the elements or text within this component. " +
            "Language-sensitive LayoutManager and Component subclasses " +
            "will use this property to determine how to lay out and draw " +
            "components.\n\n" +
            "At construction time, a component's orientation is set to " +
            "ComponentOrientation.UNKNOWN, indicating that it has not " +
            "been specified explicitly. The UNKNOWN orientation behaves " +
            "the same as ComponentOrientation.LEFT_TO_RIGHT.\n\n" +
            "To set the orientation of a single component, use this " +
            "method. To set the orientation of an entire component " +
            "hierarchy, use applyComponentOrientation.\n";
  
        JTextArea textArea = new JTextArea();
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        textArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        textArea.setText(text);
        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JScrollPane(textArea));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
    
    
}