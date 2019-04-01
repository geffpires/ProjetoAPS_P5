package Projeto.Escalonador;

import java.util.ArrayList;
import java.util.List;

public class Escalonador {
	
	private int quantium;
	private Processo executando;
	private List<Processo> processos = new ArrayList<Processo>();
	private List<Processo> bloqueados = new ArrayList<Processo>();
	private String status;
	private int tick = 0;
	

	
	public Escalonador(int quantium) {
		super();
		this.quantium = quantium;
	}
	public int getQuantium() {
		return quantium;
	}
	public void setQuantium(int quantium) {
		this.quantium = quantium;
	}
	public List<Processo> getProcessos() {
		return processos;
	}
	public String getStatus() {
		status = "Status: ";
		return status+this.geraStatus()+
				"    Tick: "+this.getTick()+"\n"+
				"    Quantium: "+this.getQuantium();
		
	}
	public String geraStatus() {
		String statusComplemento = "";
		if (this.processos.size() == 0 && this.executando == null && this.bloqueados.size() ==0) {
			return "Nenhum processo\n";
		}else if(this.executando != null) {
			statusComplemento += executando.getStatus()+"\n";
		}
		for (Processo p:processos) {
			statusComplemento+= p.getStatus()+"\n";
		};
		for (Processo p:bloqueados) {
			statusComplemento+= p.getStatus()+"\n";
		}
		return statusComplemento;
	}
	public int getTick() {
		return tick;
	}
	public void avancarTick() {
		
		if(this.processos.size()>0) {
			this.executando.setQantTickNoEscalonador(this.executando.getQantTickNoEscalonador()+1);
			if (this.executando.getQantTickNoEscalonador()>this.quantium) {
				this.executando.setStatus("Esperando");
				this.executando.setQantTickNoEscalonador(0);
				this.processos.add(executando);
				this.processos.get(0).setStatus("Executando");
				this.executando=this.processos.get(0);
				this.processos.remove(0);
				this.executando.setQantTickNoEscalonador(0);
			}//trocar para os metodos gets e sets
		}
		this.tick++;
	}
	public Processo getExecutando() {
		return executando;
	}
	public void setExecutando(Processo executando) {
		this.executando = executando;
	}
	public void addProcessoSemExecutar(Processo p) {
		this.processos.add(p);
	}
	public void addProcesso(Processo p) {
		if(this.processos.size() == 0 && executando == null) {
			p.setStatus("Executando");
			this.executando = p;
		}
		else {
			this.addProcessoSemExecutar(p);
		}
		
	}

	public void finalizarProcesso(String nome) {
		if (executando.getNome() == nome) {
			executando = null;
			if (this.processos.size() > 0) {
				this.processos.get(0).setStatus("Executando");
				executando = this.processos.get(0);
				this.processos.remove(0);
			}
		} else {
			for (Processo p : this.processos) {
				if (p.getNome() == nome) {
					this.processos.remove(p);
					break;
				}
			}
		}
	}
	public void bloquearProcesso(String nome) {
		// TODO Auto-generated method stub
		if(this.executando.getNome() == nome) {
			this.executando.setQantTickNoEscalonador(0);
			this.executando.setStatus("Bloqueado");
			this.bloqueados.add(executando);
			this.executando = null;
			if(this.processos.size()>0) {
				this.executando = processos.get(0);
				this.executando.setStatus("Executando");
				this.executando.setQantTickNoEscalonador(0);
				this.processos.remove(0);
			}
		}
		for (Processo p:processos) {
			if (p.getNome()==nome) {
				p.setStatus("Bloqueado");
				p.setQantTickNoEscalonador(0);
				this.bloqueados.add(p);
				this.processos.remove(p);
				break;
			}
		}
		
	}
	public void desbloquearProcesso(String nome) {
		// TODO Auto-generated method stub
		for(Processo p:this.bloqueados) {
			if(p.getNome()==nome) {
				p.setStatus("Esperando");
				this.addProcesso(p);
				bloqueados.remove(p);
				break;
			}
		}
	}
	public boolean temProcesso() {
		// TODO Auto-generated method stub
		if (this.executando != null) {
			return true;
		}else if(this.processos.size() > 0){
			return true;
		}else if(this.bloqueados.size() > 0) {
			return true;
		}
		return false;
	}

}
