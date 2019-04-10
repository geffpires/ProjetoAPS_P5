package Projeto.Escalonador;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class EscalonadorTest {

	@Test//T1
	public void escalonadorVazio() {
		Escalonador e = new Escalonador(3);
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T2
	public void tickIncrementou() {
		Escalonador e = new Escalonador(3);
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T3
	public void addProcessoTick0() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T4
	public void chamarTickEExecutando() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T5
	public void doisProcesso() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T6
	public void finalizarProcesso() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T7
	public void doisProcessoFinaliza1ExecutaOutro() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
	}
	@Test//T8
	public void estourarTickTrocarProcesso() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
	}
	@Test//T9
	public void add3Processos() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T10
	public void addProcessoTick0ETick3() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(), "Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
		assertEquals(e.getExecutando().getQantTickNoEscalonador(),1);
	}
	@Test//T11
	public void finalizarProcessoExecutandoProximoProcessoEntra() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//12
	public void finalizaProcessoEmEspera() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P2");
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
	}
	@Test//T13
	public void finalizaProcessoEmEsperaNaoPerdeCPU() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3");
		e.finalizarProcesso("P2");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
	}
	@Test//T14
	public void doisProcessosIntervaloNoMeio() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
		+ "    Tick: 2\n"
		+ "    Quantium: 3");
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//T15
	public void processoP1CombloqueioQuantiumEstouraEntreOsOutros() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.bloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.avancarTick();
		e.avancarTick();
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P3 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3");
	}
	@Test//T16
	public void processoBloqueadoVolta() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.bloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3");
		e.avancarTick();
		e.desbloquearProcesso("P1");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//17
	public void todosProcessosBloqueados() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		e.avancarTick();
		e.bloquearProcesso("P1");
		e.bloquearProcesso("P2");
		e.bloquearProcesso("P3");
		e.avancarTick();
		assertEquals(e.getStatus(),"Status: P1 (Bloqueado)\n"
				+ "P2 (Bloqueado)\n"
				+ "P3 (Bloqueado)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	@Test//18
	public void todosProcessosBloqueadosERetornamEmOutraOrdem() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		e.avancarTick();
		e.bloquearProcesso("P1");
		e.bloquearProcesso("P2");
		e.bloquearProcesso("P3");
		e.avancarTick();
		e.desbloquearProcesso("P2");
		e.desbloquearProcesso("P1");
		e.desbloquearProcesso("P3");
		assertEquals(e.getStatus(),"Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3");
	}
	/* Lucas */
	@Test//19
	public void tresProcessos() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo ("P1"));
		e.addProcesso(new Processo ("P2"));
		e.addProcesso(new Processo ("P3"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		e.finalizarProcesso("P1");
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3"));
		e.finalizarProcesso("P2");
	
	}
	
	@Test //20
	public void testProcessosEntrandoEsaindo() {
		Escalonador e = new Escalonador(2);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 2"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 2"));
		e.finalizarProcesso("P1");
		e.avancarTick();
		e.addProcesso(new Processo("P2"));
		e.addProcesso(new Processo("P3"));
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 2"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P3 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 2"));
		e.avancarTick();
		e.executandoVaiParaEspera();
		e.esperandoVaiParaExecutar();
		assertEquals(e.getStatus(),("Status: P3 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 2"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P3 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 2"));
		e.finalizarProcesso("P3");
		e.avancarTick();
		e.addProcesso(new Processo("P4"));
		assertEquals(e.getStatus(),("Status: P2 (Executando)\n"
				+ "P4 (Esperando)\n"
				+ "    Tick: 6\n"
				+ "    Quantium: 2"));
		e.finalizarProcesso("P2");
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P4 (Executando)\n"
				+ "    Tick: 7\n"
				+ "    Quantium: 2"));
		
	}
	
	@Test //21
	public void escalonadorIniciandoVazio() {
		Escalonador e = new Escalonador(3);
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: Nenhum processo\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
	}
	@Test //22
	public void testMetodoEsperando() {
		Escalonador e = new Escalonador (2);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 2"));
		e.avancarTick();
		assertEquals(e.haProcessoEsperando(), true);
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 2"));

	}
	
	@Test //23
	
	public void zerandoQuantEsc() {
		Escalonador e = new Escalonador(2);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.zeraQuantNoEscalonador();
		e.avancarTick();
		assertEquals(e.getStatus(),("Status: P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 2"));
	}
	
	@Test //24
	
	public void testProcExecutando() {
		Escalonador e = new Escalonador(2);
		e.addProcesso(new Processo("P1"));
		assertEquals(e.temProcessosExecutando(), true);
		
	}
	
	@Test //25
	public void test25() {
		Escalonador e = new Escalonador(1);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.avancarTick();
		e.bloquearProcesso("P1");
		assertEquals(e.getStatus(), ("Status: P2 (Executando)\n"
				+ "P1 (Bloqueado)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 1"));
		e.bloquearProcesso("P2");
		e.avancarTick();
		e.addProcesso(new Processo("P3"));
		e.bloquearProcesso("P1");
		e.avancarTick();
		assertEquals(e.getStatus(), ("Status: P3 (Executando)\n"
				+ "P1 (Bloqueado)\n"
				+ "P2 (Bloqueado)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 1"));
		
		
		
		
	}
	
	@Test // 26
	public void test26() {
		Escalonador e = new Escalonador(3);
		e.addProcesso(new Processo("P1"));
		e.addProcesso(new Processo("P2"));
		e.executaProximoProcesso();
		
		assertEquals(e.getStatus(), ("Status: P2 (Executando)\n"
				+ "P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		e.avancarTick();
		e.esperandoVaiParaExecutar();
		e.executandoVaiParaEspera();
		assertEquals(e.getStatus(), ("Status: P1 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	
	
}

