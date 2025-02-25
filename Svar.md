# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

   
## Oppgave 1
---------------------------------
Viktige klasser:

Abstractplayer - fordi alle spill må ha spillere for å kunne bli spilt. Alle forskjellige type spillere arver fra klassen abtractplayer og det er i denne klassen en "spiller" blir definert.

Game - fordi denne har mange metoder som definerer hvordan spillet skal funke. Denne klassen har metodene som setter opp grunnleggende regler for alle spill som skal bli spilt.

GameBoard - fordi denne klassen definerer hvor en spiller kan plassere brikkene sine og beskriver hvor stort spillbrettet skal være.

Grid - fordi denne klassen holder styr på hva som ligger i de forskjellige rutene påbrettet.

-------------------------------

Abstraction brukes i klassen Game. Her kan du se at koden er lagd sånn at andre klasser kan bruke kodene i klassen Game for å unngå duplikat av kode.

Encaptulation blir feks brukt i klassen AbstractPlayer. Her kan du finnne at "symbol" og "name" er satt til private slik at andre klasser ikke skal ta tilgang til å endre til disse verdiene.

Inheritance kan du feks finne i klassen ConnectFour. Her kan du finne metoder som "isWinner" og "makeMove" som er arvet fra klassen Game. Dette vil da si at klassen ConnectFour er child og klassen Game er parent

Polymorphism kan du feks finne i funksjonen isWinner i klassen Game. Når du kaller på denne funksjonen så vet du ikke hva som skjer fordi forskjellige spill har forskjellige "win conditions".

-------------------------------

Spill som kan være enklere å implementere kan være "Go" og "Dam". Begge disse krever to spillere og to forskjellige brikke-typer. Dette er ganske likt tre på rad og fire på rad.

Spill som kan være vanskeligere å implementere kan være "2048" og "Soduko". Disse spillene har bare en spiller, så her kunkurerer du mot selve spillet og ikke mot en annen spiller. Disse spillene bruker bruker også et tallsystem. Tre på rad og fire på rad bruker ikke tall, så her har vi da nødt til å implemtere nye klasser og regler med hensyn på tall.

-------------------------------

S - Single Responsibility, blir feks brukt i klassen TicTacToe. Denne klassen har bare en oppg, og det er å sette opp riktige dimensjoner og spillregler for akkurat og bare spillet TicTacToe.

O - Open-Closed principle, blir også feks brukt i klassen TicTacToe. For å lage dettet spillet så har personen bare lagt til en ny klasse som heter TicTacToe som arver fra klassen Game. TicTacToe har brukt metodene i klassen Game og lagt til regler for dettet spillet. Ingenting annet i resten av koden er endret.

L - Liskov substitution principle, kan du finne i klassen DumbPlayer. DumbPlayer bruker alle metodene i klassen AbstractPlayer. Og siden AbstractPlayer har implementert klassen Player, men likevel ikke implementert metoden getMove(), så må DumbPlayer implementere denne koden sånn at alle metodene blir brukt.

I - Interface segregation principle, er brukt i interfacet Player. Her er interfacet så minimalt som mulig

D - Dependency inversion, blir muligens IKKE fulgt i klassen game. Her kan du finne funksjonen gameOver() som ikke har har noe kode. gameOver koden finner du i de forskjellige spillklassene istedenfor. Men som vi ser så har både tictactoe og connectfour samme kode i gameOver. Vi burde heller ha en default gameOver kode i klassen game, så kan vi override i spill klassene om gameOver metoden skal være anderledes.

## Oppgave 2
- Opprette en ny klasse "Othello" som extends "Game"
- Legge til muligheten til å velge spillet othello i "MainMenu" og "TerminalMenu"
- Legge til kode i de implementerte metodene fra "Game" i klassen "Othello", override metoder fra klassen 	"Game" og eventuelt legge til flere private hjeplemetoder som skal få spillet othello til å fungere slik 	 vi ønsker.

## Oppgave 3
Samarbeidet med Pavel Blindheim på oppg 3. jeg kom på this.. Pavel kom på p1 > 32 i isWinner().
Jeg og Pavel Blindheim har samarbeidet en del under oppgave 3 for å hjelpe hverandre om en av oss sitter fast.

Av og til så kan du ikke plassere ned den første brikken for å starte i spillet othello. Jeg vet hvorfor dette skjer, men om dette er tilfellet må du bare starte spillet opp på nytt.
Jeg merker også at min MiniMaxPlayer bruker lang tid på å kalkulere valgene sine, spesielt om jeg velger at MiniMaxPlayer skal være på nivå 3 eller høyere. Derfor har jeg endret sånn at jeg spiller mot DumbPlayer.
