package Projeto.Escalonador;

public class EscalonadorPrioridade extends Escalonador{
	//fazer com que essa variavel seja um singleton
	//private ProcessoPrioridade executandoPrioridade;//fazer esse atributo ser statico 
								//assim só preciso de um para
								//os quatro processadores
	private Escalonador p1;
	private Escalonador p2;
	private Escalonador p3;
	private Escalonador p4;
	private int tick = 0;
	private String status;

	public EscalonadorPrioridade(int quantium) {
		super(quantium);
		p1 = new Escalonador(quantium);
		p2 = new Escalonador(quantium);
		p3 = new Escalonador(quantium);
		p4 = new Escalonador(quantium);
		
		// TODO Auto-generated constructor stub
	}
	public String getStatus() {
		status = "Status: ";
		
		return status+this.geraStatus()+
				"    Tick: "+this.getTick()+"\n"+
				"    Quantium: "+this.getQuantium();
	}
	public String geraStatus() {
		String statusComplemento = "";
		if (this.temProcesso() == false) {
			return "Nenhum processo\n";
		}else {
			statusComplemento += "1 - ";
			if(this.p1.temProcesso()) {
				statusComplemento += p1.geraStatusComplemento();
			}else {
				statusComplemento +="\n";
			}
			statusComplemento += "    2 - ";
			if(this.p2.temProcesso()) {
				statusComplemento += p2.geraStatusComplemento();
			}else {
				statusComplemento +="\n";
			}
			statusComplemento += "    3 - ";
			if(this.p3.temProcesso()) {
				statusComplemento += p3.geraStatusComplemento();
			}else {
				statusComplemento +="\n";
			}
			statusComplemento += "    4 - ";
			if(this.p4.temProcesso()) {
				statusComplemento += p4.geraStatusComplemento();
			}else {
				statusComplemento +="\n";
			}
		}
	return statusComplemento;
	}
	public boolean temProcesso() {
		if(this.p1.temProcesso() ||
				this.p2.temProcesso() ||
				this.p3.temProcesso() ||
				this.p4.temProcesso()) {
			return true;
		}
		return false;
	}//Fatorar esse metodo
	public int getTick() {
		return tick;
	}
	public void avancarTick() {
		this.intercalaProcesso();
		this.tick++;
	}
	/*public void intercalaProcesso() {
		if(this.p1.)    //Começo do fatoramento do intercalaProcesso()
	}*/
	public void intercalaProcesso() {
		if (this.p1.temProcessosExecutando()) {
			if (this.p1.haProcessoEsperando()) {
				this.p1.intercalaProcesso();
			}
		} else if (this.p2.temProcessosExecutando()) {
			if (this.p2.haProcessoEsperando()) {
				this.p2.intercalaProcesso();
			}
		} else if (this.p3.temProcessosExecutando()) {
			if (this.p3.haProcessoEsperando()) {
				this.p3.intercalaProcesso();
			}
		} else if (this.p4.temProcessosExecutando()) {
			if (this.p4.haProcessoEsperando()) {
				this.p4.intercalaProcesso();
			}
		}
	}
	/*public void intercalaProcesso() {
		if(this.haProcessoEsperando()) {
			this.incrementaTempoNoEscalonador();
			if (this.estourouQuantium()) {
				this.executaProximoProcesso();
			}
		}
	}*/
	public void addProcesso(ProcessoPrioridade p) {
		if(p.getPrioridade() == 1) {
			p1.addProcesso(p);
		}else if(p.getPrioridade() == 2) {
			p2.addProcesso(p);
		}else if (p.getPrioridade() == 3) {
			p3.addProcesso(p);
		}else {
			p4.addProcesso(p);
		}
	}

	public boolean processoInPrioridade(String nome, Escalonador e) {
		if (e.getExecutando() != null) {
			if (e.getExecutando().getNome() == nome) {
				return true;
			}
		}
		for (Processo p : e.getProcessos()) {
			if (p.getNome() == nome) {
				return true;
			}
		}
		return false;
	}
	//Lançar exceção de não encontrado
	public void finalizarProcesso(String nome) {
		if(processoInPrioridade(nome,this.p1)) {
			this.p1.finalizarProcesso(nome);
		}else if(processoInPrioridade(nome,this.p2)) {
			this.p2.finalizarProcesso(nome);
		}else if(processoInPrioridade(nome,this.p3)) {
			this.p3.finalizarProcesso(nome);
		}else if(processoInPrioridade(nome,this.p4)) {
			this.p4.finalizarProcesso(nome);
		}
	}

}
