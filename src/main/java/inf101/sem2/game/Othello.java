package inf101.sem2.game;


import java.util.ArrayList;
import java.util.List;

import inf101.grid.GridDirection;
import inf101.grid.Location;
import inf101.sem2.player.Player;

public class Othello extends Game {
	
	private void setStartPieces() {
		board.set(new Location(3,4), this.getCurrentPlayer());
		board.set(new Location(3,3), this.players.nextPlayer());
		board.set(new Location(4,4), this.getCurrentPlayer());
		board.set(new Location(4,3), this.players.nextPlayer());
	}
	
	public Othello(Graphics graphics) {
		super(new GameBoard(8, 8), graphics);
	}

	public Othello(Graphics graphics, Player p1, Player p2) {
		this(graphics);
		players.add(p1);
		players.add(p2);
		setStartPieces();

	}

	public Othello(Graphics graphics, Iterable<Player> players) {
		super(new GameBoard(8, 8), graphics, players);
		setStartPieces();

	}
	
	@Override
	public Game copy() {
		Othello game = new Othello(graphics);
		copyTo(game);
		return game;
	}

	@Override
	public boolean isWinner(Player player) {
		int p1 = 0;
		int emptyLocs = 0;
		
		if(gameOver()) {
			for(Location loc : this.board.locations()) {
				if(this.board.get(loc) == player) {
					p1++;
				}
				if(this.board.get(loc) == null) {
					emptyLocs++;
				}
			}
		}
		
		if(p1 > 32 || p1 + emptyLocs == 64) {
			return true;
		}
		
		return false;		
	}
	
	private ArrayList<Location> enemyLocs(){	// returns locations consisting of enemy's pieces
		ArrayList<Location> enemyLocs = new ArrayList<Location>();
		
		for(Location loc : board.locations()) {	//for all locations on the board
			if(this.board.get(loc) != this.getCurrentPlayer()) {	// if the location doesnt consist of the current player
				if(this.board.get(loc) != null) {	// if the location isnt empty
					enemyLocs.add(loc);	// add enemys Location to the list enemyLocs
				}
			}
		}
		
		return enemyLocs;
	}
	
	private ArrayList<Location> locsWithEnemyAsNeighbour(Location loc){
		ArrayList<Location> locsWithEnemyAsNeighbour = new ArrayList<Location>();
		for(Location neighbour : loc.allNeighbors()) {
			if(enemyLocs().contains(neighbour)) {
				locsWithEnemyAsNeighbour.add(neighbour);
			}
		}
		return locsWithEnemyAsNeighbour;
	}
	
	private List<Location> locsToConvert(Location loc){	// returns a list of locations that needs to be converted to the current player's pieces if this player places a piece on the input-location
		ArrayList<Location> enemyNeighbour = locsWithEnemyAsNeighbour(loc);	// a list consist of enemys around the input-location
		ArrayList<Location> locsToConvert = new ArrayList<Location>();	// final list that contains the locations we want to convert
				
		for(Location enemyLoc : enemyNeighbour) {	// for locations in the list enemyNeighbour
			ArrayList<Location> dirToConvert = new ArrayList<Location>();	// make a new temporary list.
			GridDirection dirToEnemy = loc.directionTo(enemyLoc);	// get the direction to the enemy's piece
			Location locInRow = enemyLoc;	// the first location in the sequence
			dirToConvert.add(locInRow);	// add the location to the temporary list
			for(int i = 0; i<6; i++) {	// for six times
				Location nextLoc = locInRow.getNeighbor(dirToEnemy);	// get the neighbour of the previous location in the sequence in the same direction
				locInRow = nextLoc;	// make the current location the next location in the sequence
				dirToConvert.add(locInRow);	// add this new location to that temporary list
				if(this.board.isOnGrid(locInRow)) {	// if this location is within the grid
					if(this.board.get(locInRow) == this.getCurrentPlayer()) {	// if this location contains the current player's piece
						dirToConvert.remove(dirToConvert.size()-1);	// remove this location in the temporary list
						locsToConvert.addAll(dirToConvert);	// add the whole list to the final list locsToConvert
						break;	// stops counting if the current player's piece is found 
					}
				}
				if(this.board.isOnGrid(locInRow)) {	// if this location is within the grid
					if(this.board.get(locInRow) == null) {	// if this location is empty
						break;	// stops counting if an empty space is found
					}
				}
			}
		}
		
		return locsToConvert;
	}
	
	@Override
	public void makeMove(Location loc) {
		List<Location> locsToConvert = locsToConvert(loc);
		
		if(getPossibleMoves().contains(loc)) {	// if the input location is a legal move
			this.board.set(loc, this.getCurrentPlayer());	// set the input location to the current player's piece
			for(Location locs : locsToConvert) {	// for all locations in the list locsToConvert
				this.board.convert(locs, this.getCurrentPlayer());	// convert the piece to the current player's col
			}
			this.players.nextPlayer();
		}
	}
	
	@Override
	public boolean canPlace(Location loc) {
		List<Location> placeableLocs = getPossibleMoves();
		
		if(placeableLocs.contains(loc)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public List<Location> getPossibleMoves(){
		ArrayList<Location> legalMoves = new ArrayList<Location>();
		
		for(Location loc : board.locations()) {
			if(!(locsToConvert(loc).isEmpty())) {
				if(this.board.get(loc) == null) {
					legalMoves.add(loc);
				}
			}
		}
		
		return legalMoves;
	}

	@Override
	public boolean gameOver() {
		if(getPossibleMoves().isEmpty()) {
			this.players.nextPlayer();
			if(getPossibleMoves().isEmpty()) {
				return true;
			}
			else
				return false;
		}
		
		return this.board.isFull();
	}

	@Override
	public String getName() {
		return "Othello";
	}
	
	@Override
	public void restart() {
		super.restart();
		setStartPieces();
	}
}