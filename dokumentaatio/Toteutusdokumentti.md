# Toteutusdokumentti

Ohjelma sisältää labyrintinluontialgoritmin sekä kaksi polunetsintäalgoritmia, A* ja IDA*.
Näiden algoritmien etenemistä labyrintissa on tarkoitus simloida graafisesti. Lisäksi kaikki ohjelmassa käytettävät tietorakenteet
(Stack, ArrayList, PriorityQueue, ArrayDeque) on toteutettu ilman Javan valmiita kirjastoja.

##Tietorakenteiden metodien aikavaativuudet

**MyStack**
- push() O(1) (O(n) jos pino on täynnä)
- pop() O(1)
- peek() O(1)

**MyArrayList**
- add() O(1) (O(n) jos lista on täynnä)
- get() O(1)
- remove O(N) pahin tapaus

**MyPriorityQueue**
- poll() O(log(n))
- add() O(log(n)) (O(n) jos keko on täynnä)
- peek() O(1)

**MyQueue** 
- add() O(1) (O(n) jos jono on täynnä)
- poll O(1)
- peek() O(1)

##Polunetsintäalgoritmien aika- ja tilavaativuudet

w = leveys, h = korkeus
**A***
A* algoritmi käy pahimmassa tapauksessa läpi kaikki labyrintin solut ja suorittaa jokaisen kohdalla MyPriorityQueue.add() operaatioita.
- Aikavaativuus O(w * h * log(w * h))
- Tilavaativuus O(w * h)

**IDA***
IDA* suorittaa pahimmassa tapauksessa w * h syvyyshakua, syvyyden kasvaessa aina yhdellä. Tällöin myös tilavaativuus on lyhin polku, eli w * h, 
mutta käytännössä lyhin polku ei koskaan käy labyrintin kaikissa soluissa.
- Aikavaativuus O(w * h)^2
- Tilavaativuus O(w * h)

##Analyysia

Kuten aikavaativuuksista nähdään A* on keolla toteutettuna nopeampi kuin A*. Tämä käy myös ilmi ohjelma suorituskykytesteistä. Ohjelmassa sallituilla 
syötteillä suurta eroa ei kuitenkaan ole huomattavissa. Sopivasti optimoituna IDA* olisi mahdollista saada samaan aikavaativuusluokkaan kuin A*. 
Tilavaativuudeltaan IDA* on kuitenkin lähes aina parempi vaihtoehto, sillä se pitää muistissa vain kullakin hetkellä käsiteltävänä olevan polun, jonka 
maksimipituus on sama kuin labyrintin ratkaisulla.

Ohjelmassa toteutettu IDA* hidastuu rajusti isoilla syötteillä johtuen algoritmin simuloinnista. Algoritmia simuloitaessa joudutaan tekemään 
MyQueue.add() operaatioita aina kun IDA* saapuu soluun ja poistuu solusta. Operaatidoiden määrä on suuri isoilla syötteillä. Ero algoritmin kuluttamaan aikaan 
on huomattava isoilla syötteillä ilman simulointia ja kyseisiä operaatioita.

Molemmat algoritmit toimivat nopeasti käyttöliittymässä sallituilla syötteillä ja niiden simulointi osoittaa selvästi kunkin algoritmin toimintaperiaatteen.