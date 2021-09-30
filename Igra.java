package igra;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Igra extends Frame implements Runnable {
	private Mreza mreza = new Mreza(this);
	private Panel desniPanel = new Panel(new GridLayout(0, 1));
	private Panel donjiPanel = new Panel();
	private Label balansL = new Label("0");
	private TextField ulogL = new TextField("100", 5);
	private Label kvotaL = new Label("0.00");
	private Label dobitakL = new Label("0.00");
	private Button igraj = new Button("Igraj");
	private float balans = 0, ulog = 100, kvota, dobitak, noviBalans;
	private Label statusBar = new Label();

	public Igra() {
		setBounds(700, 200, 700, 500);
		setResizable(true);
		setTitle("Igra");

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		populateWindow();

		setVisible(true);
	}

	private void populateWindow() {
		add(mreza, BorderLayout.CENTER);

		desniPanel.setBackground(Color.LIGHT_GRAY);
		desniPanel.setPreferredSize(new Dimension(150, 500));
		Panel pomocni1 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel pomocni2 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel pomocni3 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel pomocni4 = new Panel(new FlowLayout(FlowLayout.LEFT));
		Panel pomocni5 = new Panel(new FlowLayout(FlowLayout.RIGHT));

		pomocni1.add(new Label("Balans: "));
		pomocni1.add(balansL);
		desniPanel.add(pomocni1);

		ulogL.addTextListener(te -> {
			if (ulogL.getText().length() > 0) {
				ulog = Float.parseFloat(ulogL.getText());
				System.out.println(ulog);
			} else {
				ulog = 0;
			}
			azurirajKvotu();
		});
		pomocni2.add(new Label("Ulog: "));
		pomocni2.add(ulogL);
		desniPanel.add(pomocni2);

		pomocni3.add(new Label("Kvota: "));
		pomocni3.add(kvotaL);
		desniPanel.add(pomocni3);

		pomocni4.add(new Label("Dobitak: "));
		pomocni4.add(dobitakL);
		desniPanel.add(pomocni4);

		igraj.setEnabled(false);
		igraj.addActionListener(ae->{
			balans = noviBalans;
			int randBr = new Generator().generisiSlucajanBroj(0, 19);
			statusBar.setText(""+randBr);
			if(mreza.dohvatiBrojeveIzabranihPolja().contains(randBr)) {
				statusBar.setBackground(Color.GREEN);
				balans+=dobitak;
				noviBalans = balans;
				balansL.setText(String.format("%.2f", balans));
			} else {
				statusBar.setBackground(Color.RED);
				balans-=ulog;
				noviBalans = balans;
				balansL.setText(String.format("%.2f", balans));
			}
			desniPanel.revalidate();
		});
		pomocni5.add(igraj);
		desniPanel.add(pomocni5);
		add(desniPanel, BorderLayout.EAST);

		statusBar.setBackground(Color.GRAY);
		statusBar.setAlignment(Label.CENTER);
		add(statusBar, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		new Igra();
	}

	public void azurirajKvotu() {
		if (mreza.dohvatiIzabranaPolja().size() > 0) {
			igraj.setEnabled(true);
			kvota = (float) ((20 * 1.0) / mreza.dohvatiIzabranaPolja().size());
			dobitak = kvota * ulog;
			noviBalans = balans - ulog;
		} else {
			igraj.setEnabled(false);
			kvota = 0;
			dobitak = 0;
			noviBalans = balans = 0;
			statusBar.setBackground(Color.GRAY);
			statusBar.setText("");
		}
		azurirajLabele();
	}

	private void azurirajLabele() {
		kvotaL.setText(String.format("%.2f", kvota));
		dobitakL.setText(String.format("%.2f", dobitak));
		balansL.setText(String.format("%.2f", noviBalans));
		desniPanel.revalidate();
	}

	public Mreza dohvatiMrezu() {
		return mreza;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
