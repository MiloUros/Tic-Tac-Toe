import javax.swing.*;
import java.awt.*;

public class NameButton extends JButton {

    public NameButton(String text) {
        super(text);
        this.setName("Button" + text);
        this.setFont(new Font("Courier", Font.BOLD, 50));
        this.setFocusPainted(false);
        this.setMargin(new Insets(5,  0, 5, 0));
    }

}