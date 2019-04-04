package Projeto.Escalonador;

public class EscalonadorPrioridade {
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
	private int quantium;

	public EscalonadorPrioridade(int quantium) {
		this.quantium = quantium;
		this.p1 = new Escalonador(quantium);
		this.p2 = new Escalonador(quantium);
		this.p3 = new Escalonador(quantium);
		this.p4 = new Escalonador(quantium);
		
		// TODO Auto-generated constructor stub
	}
	
	public Escalonador getPrioridadeP1() {
		return this.p1;
	}

	public Escalonador getPrioridadeP2() {
		return this.p2;
	}

	public Escalonador getPrioridadeP3() {
		return this.p3;
	}

	public Escalonador getPrioridadeP4() {
		return this.p4;
	}

	public int getQuantium(){
		return this.quantium;
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
	
	//Esse metodo intercala os processos e avança o tick
	public void avancarTick() {
		this.intercalaProcesso();
		this.tick++;
	}
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
	public void addProcesso(ProcessoPrioridade p) {
		if(p.getPrioridade() == 1) {
			this.p1.addProcesso(p);
			if (this.p2.temProcessosExecutando()) {
				this.p2.executandoVaiParaEspera();
			}else if(this.p3.temProcessosExecutando()) {
				this.p3.executandoVaiParaEspera();
			}else if(this.p4.temProcessosExecutando()) {
				this.p4.executandoVaiParaEspera();
			}
		} else if (p.getPrioridade() == 2) {
			if (this.p1.temProcessosExecutando()) {
				this.p2.addProcessoSemExecutar(p);
			} else {
				this.p2.addProcesso(p);
				if (this.p3.temProcessosExecutando()) {
					this.p3.executandoVaiParaEspera();
				} else if (this.p4.temProcessosExecutando()) {
					this.p4.executandoVaiParaEspera();
				}
			}
		}else if (p.getPrioridade() == 3) {
			if(this.p2.temProcessosExecutando() || this.p1.temProcessosExecutando()) {
				this.p3.addProcessoSemExecutar(p);
			}
			else{
				this.p3.addProcesso(p);
				if(this.p4.temProcessosExecutando()) {
					this.p4.executandoVaiParaEspera();
				}
			}
		}else {
			if(this.p3.temProcessosExecutando() || this.p2.temProcessosExecutando() || this.p1.temProcessosExecutando()) {
				this.p4.addProcessoSemExecutar(p);
			}else {
				this.p4.addProcesso(p);
			}
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
		for (Processo p: e.getBloqueados() ) {
			if(p.getNome() == nome) {
				return true;
			}
		}
		return false;
	}
	//Lançar exceção de não encontrado
	public void finalizarProcesso(String nome) {
		if(processoInPrioridade(nome,this.p1)) {
			this.p1.finalizarProcesso(nome);
			if (!this.p1.temProcessosExecutando()) { //Se não tiver nenhum processo executando os outros escalonadores devem executar seus processos
				if (this.p2.haProcessoEsperando()) { // Tem processo para executar? se sim, execute, caso contrario
					this.p2.esperandoVaiParaExecutar();
				} else if (this.p3.haProcessoEsperando()) { //Tem processo para executar? se sim, execute, caso contrario
					this.p2.esperandoVaiParaExecutar();
				} else if (this.p4.haProcessoEsperando()) {//Tem processo para executar? se sim, execute
					this.p4.esperandoVaiParaExecutar();
				}
			}
		}else if(processoInPrioridade(nome,this.p2)) {
			this.p2.finalizarProcesso(nome);
			if(!this.p2.temProcessosExecutando()) {
				if (this.p3.haProcessoEsperando()) {
					this.p3.esperandoVaiParaExecutar();
				}else if(this.p4.haProcessoEsperando()) {
					this.p4.esperandoVaiParaExecutar();
				}
			}
		}else if(processoInPrioridade(nome,this.p3)) {
			this.p3.finalizarProcesso(nome);
			if (!this.p3.temProcessosExecutando()) {
				if(this.p4.haProcessoEsperando()) {
					this.p4.esperandoVaiParaExecutar();
				}
			}
		}else if(processoInPrioridade(nome,this.p4)) {
			this.p4.finalizarProcesso(nome);
		}
	}

	public void bloquearProcesso(String nome) {
		if (this.processoInPrioridade(nome, this.p1)) {
			this.p1.bloquearProcesso(nome);
			if (!this.p1.temProcessosExecutando()) {
				if (this.p2.haProcessoEsperando()) {
					this.p2.esperandoVaiParaExecutar();
				} else if (this.p3.haProcessoEsperando()) {
					this.p3.esperandoVaiParaExecutar();
				} else if (this.p4.haProcessoEsperando()) {
					this.p4.esperandoVaiParaExecutar();
				}
			}
		} else if (this.processoInPrioridade(nome, this.p2)) {
			this.p2.bloquearProcesso(nome);
			if (!this.p2.temProcessosExecutando()) {
				if (this.p3.haProcessoEsperando()) {
					this.p3.esperandoVaiParaExecutar();
				} else if (this.p4.haProcessoEsperando()) {
					this.p4.esperandoVaiParaExecutar();
				}
			}
		} else if (this.processoInPrioridade(nome, this.p3)) {
			this.p3.bloquearProcesso(nome);
			if (!this.p3.temProcessosExecutando()) {
				if (this.p4.haProcessoEsperando()) {
					this.p4.esperandoVaiParaExecutar();
				}
			}
		}else if(this.processoInPrioridade(nome, this.p4)) {
			this.p4.bloquearProcesso(nome);
		}
	}
	
	// Fazer com que o processo bloqueado não volte a ser executado caso haja alguem com prioridade maior que ele
	// Frase do algoritimo, "Voltei, mas já tem gente na frente, pode continuar, vou esperar"
	public void desbloquearProcesso(String nome) {
		if(this.processoInPrioridade(nome, this.p1)) {
			this.p1.desbloquearProcesso(nome);
			if (this.p1.temProcessosExecutando()) {
				if(this.p2.temProcessosExecutando()) {
					this.p2.executandoVaiParaEspera();
				}else if (this.p3.temProcessosExecutando()) {
					this.p3.executandoVaiParaEspera();
				}else if (this.p4.temProcessosExecutando()) {
					this.p4.executandoVaiParaEspera();
				}
			}
		}else if (this.processoInPrioridade(nome, this.p2)) {
			this.p2.desbloquearProcesso(nome);
			if (this.p2.temProcessosExecutando()) {
				if(this.p3.temProcessosExecutando()) {
					this.p3.executandoVaiParaEspera();
				}else if (this.p4.temProcessosExecutando()) {
					this.p4.executandoVaiParaEspera();
				}
			}
		}else if (this.processoInPrioridade(nome, this.p3)) {
			this.p3.desbloquearProcesso(nome);
			if (this.p3.temProcessosExecutando()) {
				if(this.p4.temProcessosExecutando()) {
					this.p4.executandoVaiParaEspera();
				}
			}
			if (this.p1.temProcessosExecutando() 
					&& this.p2.temProcessosExecutando()) {
				this.p2.executandoVaiParaEspera();
			}
			if ((this.p1.temProcessosExecutando() ||
					this.p2.temProcessosExecutando()) 
					&& this.p3.temProcessosExecutando()) {
				this.p3.executandoVaiParaEspera();
			}
		}else if(this.processoInPrioridade(nome, this.p4)) {
			this.p4.desbloquearProcesso(nome);
			if ((this.p1.temProcessosExecutando() || 
					this.p2.temProcessosExecutando() || 
					this.p3.temProcessosExecutando()) 
					&& this.p4.temProcessosExecutando()) {
				this.p4.executandoVaiParaEspera();
			}
		}
	}
}
