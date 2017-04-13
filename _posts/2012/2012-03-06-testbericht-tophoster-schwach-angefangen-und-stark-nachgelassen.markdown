---
author: Timmeey
comments: true
date: 2012-03-06 00:05:16+00:00
excerpt: 'TopHoster - Schwach angefangen und stark nachgelassen. Ein Erfahrungsbericht

  Gute Angebote aber keine ausreichende Leistung, und dann noch das Widerrufsrecht
  in Frage stellen und absprechen.'
layout: post
link: https://blog.timmeey.de/testbericht-tophoster-schwach-angefangen-und-stark-nachgelassen/
slug: testbericht-tophoster-schwach-angefangen-und-stark-nachgelassen
title: Testbericht TopHoster - Schwach angefangen und stark nachgelassen
wordpress_id: 50
categories:
- Erfahrungsberichte
tags:
- Erfahrungsbericht
- Testbericht
- tophoster
- vServer
---

Im Herbst 2011 entschied ich, dass es langsam Zeit wird mir einen eigenen vServer anzuschaffen. Nach einigem rumgegoogle entschied ich mich aufgrund der guten Konditionen und der vergleichsweise wenigen negativen und vielen positiven Meinungen für das Angebot vServer S2.0 von TopHoster (media:Webline Internet Solutions GmbH).



Das Angebot:

vServer-S 2.0



	
  * Ram 512mb. Davon garantiert 256MB die anderen 256MB gibt es als Swap.

	
  * 25Gb Festplatten Speicher

	
  * Traffic Flat

	
  * tun/tap(z.b. für OpenVPN) nur gegen Aufpreis

	
  * 1x .de Domain enthalten

	
  * Serverstandort Hamburg

	
  * Öko-Strom

	
  * 4,99€ im Monat


<!-- more -->

Die Bestellung:
Die Bestellung verlief problemlos. Ein paar Formulare ausgefüllt(Name Alter Adresse Wunschdomain) und das wars dann auch schon. Am nächsten morgen hatte ich dann auch schon die Zugangsdaten für den vServer im Postfach, auch die Domain war schon aufgeschaltet.



Der erste Login:

Es begrüßte mich ein doch schon etwas angestaubtes Ubuntu. Gut dachte ich mir, also ab zur Onlineverwaltung und flux ne neuere Version installieren lassen. Nunja Pustekuchen. 8.04 war schon die aktuellste LTS, aber selbst dafür ist der Supportzeitraum ausgelaufen. Es gab zwr noch ein 9.04 aber dessen Supportzeitraum ist ja noch weit früher ausgelaufen). Ein manuelles Distupgrade schlug natürlich fehl(was ja aufgrund der Virtualisierungstechnik auch klar ist), aber man kanns ja mal versuchen.
Nachdem durch den Versuch natürlich das ganze System verhunzt war, nochmal schnell zum Kontrollpanel und 8.04 neu installiert.

Dann gings ans einrichten. Beim Installieren der ersten Software begannen die Probleme dann auch schon. Die Installation der kleinsten Dinge hat eine gefühlte Ewigkeit gebraucht(im Vergleich zu meiner uralt Kiste mit 500MHz 250MB Ram). Naja vllt temporäre Last auf des Hostsystems. Also erstmal die schon vorhandene Software konfigurieren.
Da ich, wie die meisten anderen wohl auch, recht faul bin, mache ich gerne und viel gebrauch von der Autovervollständigungsfunktion der Shell(zsh). Da gab es dann die gleiche Probleme, alles reagierte nur sehr träge, teilweise hatte ich schon bei nur wenigen Dateien/Ordnern wartezeiten von 5 bis 6Sekunden oder mehr. Das ist einfach ein absolutes NoGo, denn immerhin zeigten iotop und top nichts ungewöhnliches an, ein ganz normales System was idled, und trotzdem reagierte alles so unglaublich träge. Das darf einfach nicht sein.
An den darauffolgenden Tagen und Nächten hat sich leider nichts an der Situation geändert. Es war also die erste Mail an den Support fällig mit der Frage was denn da auf dem Hostsystem los sei. Am Tag drauf bekam ich dann die Antwort, dass mit dem Hostsystem alles in Ordnung sei.
Inzwischen waren einige Tage vergangen und ich hatte soweit alles Installiert und konfiguriert. Aber ich schaffte es einfach nicht Mails zu verschicken, die remote server haben sie immer abgewiesen. Das lag wie ich später herausfand am fehlenden reverseDNS Eintrag. Es gab dazu keinerlei Option im Kontrollpanel. Möglicherweise kann man diesen Eintrag über den Support setzen lassen(nicht aufprobiert)

Weiter habe ich mir dann zum basteln einfach mal ein Gentoo draufgemacht. Dieses konnte ich aber selbst nach der frischen Installation nicht zum booten bewegen, daher kompletter fail.



Die Servicequalität:

Nach den Supportanfragen und ein paar Telefonaten kann ich sagen, dass der Support technisch Kompetent gewirkt hat und sehr freundlich war. Aber der Service an sich ist eine Katastrophe. Durch die Trägheit des vServers und des fehlenden revereseDNS Eintrags entnervt, entschied ich mich, mein Glück bei einem anderen Anbieter zu suchen.

Ich habe also innerhalb der ersten 14Tage(Widerrufsfrist) meinen Widerruf an TopHoster gefaxt, kurz danach bekam ich einen Anruf, was man denn tun könnte um mich als Kunde zu behalten. Nichts in diesem Fall... Nachdem ich dann nach einer Woche immernoch nichts gehört hatte, schrieb ich meine erste Supportmail bzgl. meines Widerrufs. Dazu gab es nur einen fertigen Textbaustein zurück:



    
    Mit Ihrer Bezahlung der Forderung für die Rechnung ********** ist der Kaufvertrag zustande gekommen
    und Sie haben dadurch jeglichen Anspruch auf Ihr Widerrufsrecht verloren.




Das ist natürlich grober Unfug. Also das Telefon geschnappt und angerufen. Ein Support Mitarbeiter erklärte mir dann noch, dass ich die Domain nicht widerrufen könne, daraufhin erklärte ich mich bereit die Domain zu den Konditionen die die Domain ohne Server gekostet hätte zu bezahlen. Das ganz habe ich als Email hingeschickt, in der Annahme, dass der Rest jetzt schnell erledigt wird. Als dann wieder eine Woche um war und ich immernoch keine Antwort hatte, bemühte ich abermals das Ticketsystem. Und wieder bekam ich nur den Textbaustein dass ein Widerruf nicht möglich ist. Also wieder beim Support angerufen und nach Verantwortlichen verlangt, dann wurde ich nur noch abgewimmelt(haben schon Feierabend, sind noch nicht im Haus, sind außer Haus, sind zu Tisch[...]). Inzwischen war fast ein Monat vergangen.

Ende vom Lied war dann, dass ich noch nen extra KK-Antrag stellen musste der dann nach etwa einer Woche und zwei weiteren Telefonaten bearbeitet wurde und ich meine Domain ENDLICH hierher umziehen konnte. Mein Geld habe ich bis heute nicht wiedergesehen.



Fazit:

TopHoster - Schwach angefangen und stark nachgelassen. Besonders nach der Aktion ,dass ich nicht widerrufen könne, steht meine Meinung fest: Absolut NICHT empfehlenswert.

Motivierte Mitarbeiter am Helpdesk allein reichen halt nicht aus.



Links:

[vServer Angebot](http://www.tophoster.de/server/vserver/vserver-s)

[top!hoster](http://www.tophoster.de/)
