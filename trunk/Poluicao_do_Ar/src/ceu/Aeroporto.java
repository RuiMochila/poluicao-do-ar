package ceu;

public class Aeroporto extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AEROPORTO;

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}

}
