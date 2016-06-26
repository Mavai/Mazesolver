# Toteutusdokumentti

Ohjelma sis�lt�� labyrintinluontialgoritmin sek� kaksi polunetsint�algoritmia, A* ja IDA*.
N�iden algoritmien etenemist� labyrintissa on tarkoitus simloida graafisesti. Lis�ksi kaikki ohjelmassa k�ytett�v�t tietorakenteet
(Stack, ArrayList, PriorityQueue, ArrayDeque) on toteutettu ilman Javan valmiita kirjastoja.

##Tietorakenteiden metodien aikavaativuudet

**MyStack**
- push() O(1) (O(n) jos pino on t�ynn�)
- pop() O(1)
- peek() O(1)

**MyArrayList**
- add() O(1) (O(n) jos lista on t�ynn�)
- get() O(1)
- remove O(N) pahin tapaus

**MyPriorityQueue**
- poll() O(log(n))
- add() O(log(n)) (O(n) jos keko on t�ynn�)
- peek() O(1)

**MyQueue** 
- add() O(1) (O(n) jos jono on t�ynn�)
- poll O(1)
- peek() O(1)

##Polunetsint�algoritmien aika- ja tilavaativuudet

w = leveys, h = korkeus
**A***
A* algoritmi k�y pahimmassa tapauksessa l�pi kaikki labyrintin solut ja suorittaa jokaisen kohdalla MyPriorityQueue.add() operaatioita.
- Aikavaativuus O(w * h * log(w * h))
- Tilavaativuus O(w * h)

**IDA***
IDA* suorittaa pahimmassa tapauksessa w * h syvyyshakua, syvyyden kasvaessa aina yhdell�. T�ll�in my�s tilavaativuus on lyhin polku, eli w * h, 
mutta k�yt�nn�ss� lyhin polku ei koskaan k�y labyrintin kaikissa soluissa.
- Aikavaativuus O(w * h)^2
- Tilavaativuus O(w * h)

##Analyysia

Kuten aikavaativuuksista n�hd��n A* on keolla toteutettuna nopeampi kuin A*. T�m� k�y my�s ilmi ohjelma suorituskykytesteist�. Ohjelmassa sallituilla 
sy�tteill� suurta eroa ei kuitenkaan ole huomattavissa. Sopivasti optimoituna IDA* olisi mahdollista saada samaan aikavaativuusluokkaan kuin A*. 
Tilavaativuudeltaan IDA* on kuitenkin l�hes aina parempi vaihtoehto, sill� se pit�� muistissa vain kullakin hetkell� k�sitelt�v�n� olevan polun, jonka 
maksimipituus on sama kuin labyrintin ratkaisulla.

Ohjelmassa toteutettu IDA* hidastuu rajusti isoilla sy�tteill� johtuen algoritmin simuloinnista. Algoritmia simuloitaessa joudutaan tekem��n 
MyQueue.add() operaatioita aina kun IDA* saapuu soluun ja poistuu solusta. Operaatidoiden m��r� on suuri isoilla sy�tteill�. Ero algoritmin kuluttamaan aikaan 
on huomattava isoilla sy�tteill� ilman simulointia ja kyseisi� operaatioita.

Molemmat algoritmit toimivat nopeasti k�ytt�liittym�ss� sallituilla sy�tteill� ja niiden simulointi osoittaa selv�sti kunkin algoritmin toimintaperiaatteen.