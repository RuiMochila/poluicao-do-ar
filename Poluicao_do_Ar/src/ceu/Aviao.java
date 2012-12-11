package ceu;

public class Aviao extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AVIAO;

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}

}
