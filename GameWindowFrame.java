import javax.swing.JFrame;

public class GameWindowFrame extends JFrame {
    JFrame jFrame = new JFrame();
    GameWindowFrame() {
        jFrame.add(new GameBoard());
		jFrame.setTitle("Welcome to the Snake Game");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setLocationRelativeTo(null);
    }
}
