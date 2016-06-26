# Testausdokumentti

Ohjelman jokaiselle luokalle on luotu junit testit, jotka varmistavat luokkien oikeanlaisen toiminnan.
Tietorakenteiden kohdalla testataan yksinkertaisesti, ett� metodien suorittaminen saa aikaan halutun tuloksen.
Polunetsint�algoritmeja testaamalla tarkistetaan samalla labyrintinluontialgoritmin toimiminnasta, kun varmistetaan ett� 
labyrintin l�pi todella l�ytyy polku. Lis�ksi tarkistetaan, ett� molemmat polunetsint�algoritmit p��tyv�t samaan tulokseen.

Suorituskykytestaukset tehtiin PerformanceTest -luokalla, joka testaa ohjelmassa k�ytettyj� polunetsint�algoritmeja eri kokoisilla labyrinteilla. 
Labyrintien maksimikoko on 200x200, jonk� ylittyess� sen rekursiivinen generointialgoritmi lakkaa toimimasta rekursiopinon ylittyess�. T�m�n voisi 
est�� esimerkiksi pienempi� labyrinttej� yhdistelem�ll�, mutta t�t� ei ole toteutettu harjoitusty�ss�.

![](Suorituskykytestaus.png)


Suorituskykytestauksessa ei erikseen testattu toteutettuja tietorakenteita, sill� niiden toiminta on testattu JUnit testeill� ja aikavaativuudet 
on yksinkertaista n�hd� suoraan koodista. Aikavaativuuksia k�sitell��n toteutusdokumentissa.

