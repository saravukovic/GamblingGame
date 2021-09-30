package igra;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import igra.Polje.Status;

@SuppressWarnings("serial")
public class Mreza extends Panel {
	private Polje[][] polja;
	private List<Polje> izabranaPolja = new ArrayList<>();
	private HashSet<Integer> brojeviIzabranihPolja = new HashSet<>();
	private Igra vlasnik;
	private int x = 4, y = 5;

	public Mreza(int x, int y, Igra vlasnik) {
		this.vlasnik = vlasnik;
		this.x = x;
		this.y = y;
		polja = new Polje[x][y];
		adjustPanel();
	}
	
	public Mreza(Igra vlasnik) {
		this.vlasnik = vlasnik;
		polja = new Polje[x][y];
		adjustPanel();
	}
	
	private void adjustPanel() {
		this.setLayout(new GridLayout(x, y, 3, 3));
		this.setBackground(Color.BLACK);
		int num = 0;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Polje p = new Polje(num, this);
				polja[i][j] = p;
				p.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						p.promeniStatus();
					}
				});
				this.add(p);
				num++;
			}
		}
	}
	
	public void azurirajIzabranaPolja(Polje p) {
		if(p.dohvatiStatus()==Status.SLOBODNO) {
			if(izabranaPolja.contains(p)) izabranaPolja.remove(p);
			if(brojeviIzabranihPolja.contains(p.dohvatiBroj())) brojeviIzabranihPolja.remove(p.dohvatiBroj());
		}
		if(p.dohvatiStatus()==Status.IZABRANO) {
			izabranaPolja.add(p);
			brojeviIzabranihPolja.add(p.dohvatiBroj());
		}
		vlasnik.azurirajKvotu();
	}

	public List<Polje> dohvatiIzabranaPolja() {
		return izabranaPolja;
	}

	public HashSet<Integer> dohvatiBrojeveIzabranihPolja() {
		return brojeviIzabranihPolja;
	}

}
