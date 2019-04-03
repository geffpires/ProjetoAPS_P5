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
}
