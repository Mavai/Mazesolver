# Viikkoraportti 3

T�ll� viikolla toteutin IDA polunetisnt�algoritmin. IDA suorittaa rekursiivisesti syvyyshakuja lebyrintissa edeten taso kerrallaan aina parhaalta vaikuttavaan suuntaan ja vierailee suunnilleen samoissa labyrintin soluissa kuin tavallinen A* algoritmi. IDA -algoritmi on kuitenkin muistivaativuudeltaan tehokkaampi ratkaisu. Lis�� t�st� aiheesta toteutusdokumentissa. 

Lis�ksi toteutin pino -tietorakenteen ilman javan valmiita kirjastoja. Toteuttamani tietorakenne noudattaa samaa "first in first out" periaatetta ja sis�lt�� saman toiminnallisuuden kuin Javan valmis tietorakenne. Pinon koko on oletuksena 10, mutta sen voi my�s m��ritt�� konstruktorissa. Koko tuplaantuu automaattisesti jos t�ynn� olevaan kekoon yritet��n lis�t� tavaraa.

Seuraavaksi jatkan omien tietorakenteiden toteuttamista.