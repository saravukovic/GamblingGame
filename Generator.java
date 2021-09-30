package igra;

import java.util.Random;

public class Generator {
	public int generisiSlucajanBroj(int dole, int gore) {
		return new Random().nextInt(gore-dole+1)+dole;
	}
}
