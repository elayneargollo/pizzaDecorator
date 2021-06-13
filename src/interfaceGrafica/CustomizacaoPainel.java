package interfaceGrafica;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class CustomizacaoPainel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image background;

    public CustomizacaoPainel(URL path) {
        this.background = new ImageIcon(path).getImage();
        setOpaque(false);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(background, 0, 0, this);
        super.paintComponent(graphics);
    }

    @Override
    public int getWidth() {
        return background.getWidth(this);
    }

    @Override
    public int getHeight() {
        return background.getHeight(this);
    }

}