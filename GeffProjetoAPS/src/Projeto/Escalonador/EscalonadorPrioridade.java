package Projeto.Escalonador;

public class EscalonadorPrioridade {
	
	/* Os 4 escalonadores podem ser uma lista de escalonador, 
	*caso ele se torne uma lista, os metodos deverão ser alterados	*/
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
	
	//Os gets que irão se juntar e tornar um retorno da lista
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
	
	//Gera status sera um for em vez de apenas ifs e else.
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
	
	//Varrerar a lista de Escalonador para
	public boolean temProcesso() {
		if(this.p1.temProcesso() ||
				this.p2.temProcesso() ||
				this.p3.temProcesso() ||
				this.p4.temProcesso()) {
			return true;
		}
		return false;
	}
	public int getTick() {
		return tick;
	}
	
	//Esse metodo intercala os processos e avança o tick
	public void avancarTick() {
		this.intercalaProcesso();
		this.tick++;
	}
	
	//Tornara um for para varrer os escalonadores e então usar o metodo intercalar neles
	public void intercalaProcesso() {
		if (this.p1.temProcessosExecutando()) {
			if (this.p1.haProcessoEsperando()) {
				this.p1.intercalaProcesso();
			}
		} else if (this.p2.temProcessosExecutando()) {							//Esse metodo verifica se existe processo executando
			if (this.p2.haProcessoEsperando()) {								//em cada escalonador, e se houver pergunta se tem processos
				this.p2.intercalaProcesso();									//para competir com a CPU, e então começa a intercalar eles.
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
	
	//Esse metodo ficaria bem menor com o escalonador como lista, e daria pra fatorar essas linhas repetidas
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
			if (this.p1.temProcessosExecutando()) {							//Esse metodo addiciona o processo no escalonador 
				this.p2.addProcessoSemExecutar(p);							//refente ao da prioridade que o processador possui
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
	
	//Esse metodo poderia retornar o escalonador que o processo esta
	public boolean processoInPrioridade(String nome, Escalonador e) {
		if (e.getExecutando() != null) {
			if (e.getExecutando().getNome() == nome) {
				return true;
			}
		}
		for (Processo p : e.getProcessos()) {				// Esse metodo retorna verdadeiro se for encontrado
			if (p.getNome() == nome) {						//o processo no ascalonador que é tbm passado como parametro
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
	
	//Lançar exceção de não encontrado.
	//Esse metodo poderia ser feito em um tamanho menor, usando o metodo de processoInPrioridade(), visto que ele retornaria ja o escalonador na qual o processo se encontra.
	public void finalizarProcesso(String nome) {
		if(processoInPrioridade(nome,this.p1)) {//O processo que eu quero finalizar, está nesse escalonador?
			this.p1.finalizarProcesso(nome);	//Se sim, finalize ele
			if (!this.p1.temProcessosExecutando()) { //Se não tiver nenhum processo executando os outros escalonadores devem executar seus processos
				if (this.p2.haProcessoEsperando()) { // Tem processo para executar? se sim, execute, caso contrario
					this.p2.esperandoVaiParaExecutar();// O Processo que estava esperando vai ser executado
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
	
	//Lançar exceção de não encontrado.
	//Esse metodo poderia ser feito em um tamanho menor, usando o metodo de processoInPrioridade(), 
	//visto que ele retornaria ja o escalonador na qual o processo se encontra.
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
	
	//Lançar exceção de não encontrado.
	//Esse metodo poderia ser feito em um tamanho menor, usando o metodo de processoInPrioridade(), 
	//visto que ele retornaria ja o escalonador na qual o processo se encontra.
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
	// Os metodos finalizar, bloquear e desbloquear eles precisam sempre 
	//verificar se tem alguem com prioridade maior ou menor executando ou não para 
	//que ele tome a posse do processador ou pare de executar
}
