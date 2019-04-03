package Projeto.Escalonador;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EscalonadorPrioridadeTest {

	@Test//T1
	void EscalonadorVazio() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T2
	public void tickIncrementou() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.avancarTick();
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 1\n"+
			    "    Quantium: 3");
	}
	@Test//T3
	public void addProcessoTick0Prioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T4
	public void addProcessoTick0Prioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T5
	public void addProcessoTick0Prioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T6
	public void addProcessoTick0Prioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T7
	public void chamarTickEExecutandoPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		
	}
	@Test//T8
	public void chamarTickEExecutandoPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		
	}
	@Test//T9
	public void chamarTickEExecutandoPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		
	}
	@Test//T10
	public void chamarTickEExecutandoPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//T11
	public void doisProcessoPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T12
	public void doisProcessoPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T13
	public void doisProcessoPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T14
	public void doisProcessoPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T15
	public void finalizarProcessoNaPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T16
	public void finalizarProcessoNaPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T17
	public void finalizarProcessoNaPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T18
	public void finalizarProcessoNaPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//19
	public void testEscalaNoMesmoNivel1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//20
	public void testEscalaNoMesmoNivel2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//21
	public void testEscalaNoMesmoNivel3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//22
	public void testEscalaNoMesmoNivel4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\nP2 (Esperando)\n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Executando)\nP1 (Esperando)\n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
	}
	@Test//23
	public void addProcessoPrioridade2Depois1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.avancarTick();
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - P1 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
	}
	@Test//24
	public void add2ProcessosEmPrioridadeDiferente4e1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//25
	public void add2ProcessosEmPrioridadeDiferente4e2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//26
	public void add2ProcessosEmPrioridadeDiferente4e3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",4));
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P2 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//27
	public void add2ProcessosEmPrioridadeDiferente3e1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//28
	public void add2ProcessosEmPrioridadeDiferente3e2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - P2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//29
	public void add2ProcessosEmPrioridadeDiferente3e4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P2 (Executando)\n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//30
	public void add2ProcessosEmPrioridadeDiferente2e1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - P2 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//31
	public void add2ProcessosEmPrioridadeDiferente2e3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P2 (Executando)\n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//32
	public void add2ProcessosEmPrioridadeDiferente2e4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",2));
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P2 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//33
	public void add2ProcessosEmPrioridadeDiferente1e2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - P1 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//34
	public void add2ProcessosEmPrioridadeDiferente1e3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//35
	public void add2ProcessosEmPrioridadeDiferente1e4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T36
	public void bloqueiaProcessoPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T37
	public void bloqueiaProcessoPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Bloqueado)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T38
	public void bloqueiaProcessoPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T39
	public void bloqueiaProcessoPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Bloqueado)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T40
	public void DesbloqueiaProcessoPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.bloquearProcesso("P1");
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T41
	public void DesbloqueiaProcessoPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.bloquearProcesso("P1");
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Executando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T42
	public void DesbloqueiaProcessoPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.bloquearProcesso("P1");
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T43
	public void DesbloqueiaProcessoPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.bloquearProcesso("P1");
		ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
	}
	@Test//T44
	public void finalizarProcessoBloqueadoNaPrioridade1() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",1));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P1 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T45
	public void finalizarProcessoBloqueadoNaPrioridade2() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",2));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P1 (Bloqueado)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T46
	public void finalizarProcessoBloqueadoNaPrioridade3() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//T47
	public void finalizarProcessoBloqueadoNaPrioridade4() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Bloqueado)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//48
	public void addProcessoPrioridadeOrdem4321DepoisFinalizarTodosNaMesmaOrdem() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",4));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - P1 (Executando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P2",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P2 (Executando)\n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P3",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - P3 (Executando)\n"
				+ "    3 - P2 (Esperando)\n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P4",1));
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\n"
				+ "    2 - P3 (Esperando)\n"
				+ "    3 - P2 (Esperando)\n"
				+ "    4 - P1 (Esperando)\n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\n"
				+ "    2 - P3 (Esperando)\n"
				+ "    3 - P2 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\n"
				+ "    2 - P3 (Esperando)\n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P3");
		assertEquals(ep.getStatus(),("Status: 1 - P4 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - \n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.finalizarProcesso("P4");
		assertEquals(ep.getStatus(),"Status: Nenhum processo\n"+
			    "    Tick: 0\n"+
			    "    Quantium: 3");
	}
	@Test//Hiper Teste Merda Grandão Final
	public void testeVilarFinal() {
		EscalonadorPrioridade ep = new EscalonadorPrioridade(3);
		ep.addProcesso(new ProcessoPrioridade("P1",3));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//1
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 1\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//2
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P2",1));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 2\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//3
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 3\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//4
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 4\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//5
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P3",4));
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 5\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//6
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 6\n"
				+ "    Quantium: 3"));
		ep.bloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Executando)\n"
				+ "    Tick: 6\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//7
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Bloqueado)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Executando)\n"
				+ "    Tick: 7\n"
				+ "    Quantium: 3"));
		ep.desbloquearProcesso("P2");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 7\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//8
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Bloqueado)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 8\n"
				+ "    Quantium: 3"));
		
		//Teste rodando até aqui, ok
		/*ep.desbloquearProcesso("P1");
		assertEquals(ep.getStatus(),("Status: 1 - P2 (Executando)\n"
				+ "    2 - \n"
				+ "    3 - P1 (Esperando)\n"
				+ "    4 - P3 (Esperando)\n"
				+ "    Tick: 8\n"
				+ "    Quantium: 3"));
		/*ep.addProcesso(new ProcessoPrioridade("P4",1));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//9
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//10
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P5",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.addProcesso(new ProcessoPrioridade("P6",2));
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//11
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//12
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//13
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//14
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//15
		assertEquals(ep.getStatus(),("Status: 1 - \n"
				+ "    2 - \n"
				+ "    3 - P1 (Executando)\n"
				+ "    4 - \n"
				+ "    Tick: 0\n"
				+ "    Quantium: 3"));
		ep.avancarTick();//16
		ep.avancarTick();//17
		ep.avancarTick();//18
		ep.avancarTick();//19
		ep.avancarTick();//20
		ep.bloquearProcesso("P4");
		ep.avancarTick();//21
		ep.avancarTick();//22
		ep.avancarTick();//23
		ep.avancarTick();//24
		ep.avancarTick();//25
		ep.avancarTick();//26
		ep.avancarTick();//27
		ep.avancarTick();//28
		ep.avancarTick();//29
		ep.avancarTick();//30
		ep.finalizarProcesso("P4");
		ep.avancarTick();//31
		ep.avancarTick();//32
		ep.finalizarProcesso("P2");
		ep.avancarTick();//33
		ep.avancarTick();//34
		ep.avancarTick();//35
		ep.avancarTick();//36
		ep.avancarTick();//37
		ep.avancarTick();//38
		ep.avancarTick();//39
		ep.avancarTick();//40
		ep.bloquearProcesso("P45");
		ep.avancarTick();//41
		ep.avancarTick();//42
		ep.avancarTick();//43
		ep.avancarTick();//44
		ep.avancarTick();//45
		ep.avancarTick();//46
		ep.finalizarProcesso("P6");
		ep.avancarTick();//47*/
		
	}

}
