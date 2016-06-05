# Viikkoraportti 3

Tällä viikolla toteutin IDA polunetisntäalgoritmin. IDA suorittaa rekursiivisesti syvyyshakuja lebyrintissa edeten taso kerrallaan aina parhaalta vaikuttavaan suuntaan ja vierailee suunnilleen samoissa labyrintin soluissa kuin tavallinen A* algoritmi. IDA -algoritmi on kuitenkin muistivaativuudeltaan tehokkaampi ratkaisu. Lisää tästä aiheesta toteutusdokumentissa. 

Lisäksi toteutin pino -tietorakenteen ilman javan valmiita kirjastoja. Toteuttamani tietorakenne noudattaa samaa "first in first out" periaatetta ja sisältää saman toiminnallisuuden kuin Javan valmis tietorakenne. Pinon koko on oletuksena 10, mutta sen voi myös määrittää konstruktorissa. Koko tuplaantuu automaattisesti jos täynnä olevaan kekoon yritetään lisätä tavaraa.

Seuraavaksi jatkan omien tietorakenteiden toteuttamista.