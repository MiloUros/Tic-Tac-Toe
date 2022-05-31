import javax.swing.*;

public class Menu extends JMenu {
    public Menu(String text) {
        super(text);
        setName("Menu" + text);
    }
}
