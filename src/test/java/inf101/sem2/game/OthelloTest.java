package inf101.sem2.game;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import inf101.GetStarted;
import inf101.grid.Location;
import inf101.sem2.player.DumbPlayer;
import inf101.sem2.player.Player;
import inf101.sem2.terminal.TerminalGraphics;
import org.junit.jupiter.api.Test;

public class OthelloTest {
	
	@BeforeEach
	void testReadConditions() {
		assertTrue(GetStarted.hasRead);
	}
	
	@Test
	void testLegalMoves() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new Othello(new TerminalGraphics(), p1, p2);
		ArrayList<Location> legalMoves = new ArrayList<Location>();
		
		legalMoves.add(new Location(2,3));
		legalMoves.add(new Location(3,2));
		legalMoves.add(new Location(4,5));
		legalMoves.add(new Location(5,4));
		assertEquals(legalMoves, game.getPossibleMoves());
	}
	
	@Test
	void testConvertPieces() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new Othello(new TerminalGraphics(), p1, p2);
		
		game.board.set(new Location(0,0), p1);
		game.board.set(new Location(7,7), p1);
		game.board.set(new Location(0,1), p2);
		game.board.set(new Location(0,2), p2);
		game.board.set(new Location(0,3), p2);
		game.board.set(new Location(0,4), p2);
		game.board.set(new Location(0,5), p2);
		game.board.set(new Location(0,6), p2);
		game.board.set(new Location(6,7), p2);
		game.board.set(new Location(5,7), p2);
		game.board.set(new Location(4,7), p2);
		game.board.set(new Location(3,7), p2);
		game.board.set(new Location(2,7), p2);
		game.board.set(new Location(1,7), p2);
		
		game.makeMove(new Location(0,7));
		
		int i = 0;
		
		for(Location Loc : game.board.locations()) {
			if(game.board.get(Loc) == p1) {
				i++;
			}
		}
		assertEquals(17, i);
	}
	
	@Test
	void testIsWinner() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new Othello(new TerminalGraphics(), p1, p2);
		
		game.makeMove(new Location(4,5));
		game.players.nextPlayer();
		game.makeMove(new Location(3,2));
		
		assertTrue(game.isWinner(p1));
	}
	
	@Test
	void testSkipTurns() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new Othello(new TerminalGraphics(), p1, p2);
		
		game.board.convert(new Location(3,4), p2);
		game.board.convert(new Location(4,3), p2);
		
		game.board.set(new Location(1,0), p1);
		game.board.set(new Location(0,0), p2);
		
		game.run();
		
		assertEquals(p2, game.board.get(new Location(2,0)));
	}
	
	@Test
	void testRestartaddsStartPieces() {
		Player p1 = new DumbPlayer('X');
		Player p2 = new DumbPlayer('O');
		Game game = new Othello(new TerminalGraphics(), p1, p2);
		game.restart();
		
		int i = 0;
		if(game.board.get(new Location(3,3)) == p2 && game.board.get(new Location(4,4)) == p2) {
			if(game.board.get(new Location(3,4)) == p1 && game.board.get(new Location(4,3)) == p1) {
				i++;
			}
		}
		assertEquals(1, i);
	}
	
}
