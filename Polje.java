package igra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Polje extends Canvas {
	public enum Status {
		SLOBODNO, IZABRANO;
	}

	private int broj;
	private Mreza vlasnik;
	private Status status = Status.SLOBODNO;

	public Polje(int broj, Mreza vlasnik) {
		this.broj = broj;
		this.vlasnik = vlasnik;

		setPreferredSize(new Dimension(75, 75));
		setBackground(Color.ORANGE);
	}

	@Override
	public void paint(Graphics g) {
		String b = "" + broj;
		if (status == Status.SLOBODNO) {
			g.setColor(Color.BLACK);
			g.setFont(new Font(Font.SANS_SERIF, 0, (int) (Math.min(getHeight(), getWidth()) / 3)));
			
			g.drawString(b, (int) (getWidth() * 1.0 / 2 - g.getFontMetrics().stringWidth(b) * 1.0 / 2),
					(int) (getHeight() * 1.0 / 2 + g.getFontMetrics().getHeight() * 1.0 / 3));
		} else if (status == Status.IZABRANO) {

			g.setColor(Color.BLUE);
			g.fillOval(0, 0, getWidth(), getHeight());

			g.setColor(Color.WHITE);
			g.setFont(new Font(null, 0, (int) (Math.min(getHeight(), getWidth()) / 3)));
			g.drawString(b, (int) (getWidth() * 1.0 / 2 - g.getFontMetrics().stringWidth(b) * 1.0 / 2),
					(int) (getHeight() * 1.0 / 2 + g.getFontMetrics().getHeight() * 1.0 / 3));

		}
	}

	public int dohvatiBroj() {
		return broj;
	}

	public Status dohvatiStatus() {
		return status;
	}

	public void postaviStatus(Status status) {
		this.status = status;
	}

	public void promeniStatus() {
		if(status==Status.SLOBODNO) status = Status.IZABRANO;
		else status = Status.SLOBODNO;
		vlasnik.azurirajIzabranaPolja(this);
		repaint();
	}

}
