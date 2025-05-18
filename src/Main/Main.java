package Main;

import View.*;
/**
 * @author Rafael
 */
public class Main {

    public static void main(String[] args) {
        Cadastro cadastro = new Cadastro();
        cadastro.setVisible(false);
        Login login = new Login();
        login.setVisible(true);
        Frame frame = new Frame();
        frame.setVisible(false);
    }
}
