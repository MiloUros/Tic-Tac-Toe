import javax.swing.*;

public class MenuItems extends JMenuItem {

    public MenuItems(String text) {
        super(text);
        setName("Menu" + text);
    }
}
