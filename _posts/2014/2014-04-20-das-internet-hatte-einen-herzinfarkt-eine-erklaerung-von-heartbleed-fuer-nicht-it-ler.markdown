---
author: Timmeey
comments: true
date: 2014-04-20 18:16:08+00:00
layout: post
link: https://blog.timmeey.de/das-internet-hatte-einen-herzinfarkt-eine-erklaerung-von-heartbleed-fuer-nicht-it-ler/
slug: das-internet-hatte-einen-herzinfarkt-eine-erklaerung-von-heartbleed-fuer-nicht-it-ler
title: Das Internet hat(te) einen Herzinfarkt. Eine Erklärung von Heartbleed für nicht
  IT-ler
wordpress_id: 175
categories:
- IT
tags:
- Heartbleed
- OpenSSL
---

Am Montag den 7.April des Jahres 2014[[0]](http://heartbleed.com/) fegte ein Tsunami durch das Internet, und legte dabei fast die gesamte globale Sicherheits- und Verschlüsselungsinfrastruktur in Schutt und Asche. Die Schäden sind noch nicht abzuschätzen, allerdings ist klar, dass die Aufräumarbeiten sehr lange dauern werden.
Was war passiert?<!-- more -->

Am 1. April 2014 bzw. am 3. April 2014 wurde unabhängig von einander von zwei "security-engeneering" Teams ein schwerwiegender Fehler in der Software OpenSSL gefunden[[0]](http://heartbleed.com/), welcher die SSL-Verschlüsselung im Internet unbrauchbar machen kann.

Zur Erklärung: Wenn jemand in seinem Browser eine Internetseite betrachtet, dann wird diese Internetseite meist unverschlüsselt übertragen. Das bedeutet, dass jeder Knoten über den die Daten zum Nutzer weitergeleitet werden, theoretisch die Möglichkeit hat, die gesendeten Daten auszulesen. Bei einem einfachen Artikel wie diesem hier, mag das noch unbedenklich erscheinen. Geht es aber um Bankdaten oder private Nachrichten, dann darf diese Möglichkeit nicht bestehen. Deshalb werden solche Daten nur Verschlüsselt übertragen, sodass nur der Empfänger diese Daten lesen kann.
Zur Verschlüsselung der Daten wird meist ein Programm namens **_OpenSSL_**_ _eingesetzt. Sollte hier ein schwerer Fehler auftreten hätte dies fatale Folgen für das gesamte Netz, da etwa 60% bis 70% [[1]](http://heartbleed.com/%20)[[2]](http://news.netcraft.com/archives/2014/04/02/april-2014-web-server-survey.html) der Interseiten auf Webservern laufen, welche meistens OpenSSL einsetzen. Anzumerken ist hierbei allerdings, dass auch Mail/Chat/Telefonie/VPN/etc. -Server OpenSSL einsetzen, und der Webserver hier nur Beispielhaft beschrieben wird.

Was ist OpenSSL:


<blockquote>**OpenSSL**, ursprünglich _SSLeay_, ist eine [freie Software](https://de.wikipedia.org/wiki/Freie_Software) für [Transport Layer Security](https://de.wikipedia.org/wiki/Transport_Layer_Security), ursprünglich _Secure Sockets Layer_ (SSL). [[3]](https://de.wikipedia.org/wiki/OpenSSL)</blockquote>


Das bedeutet im Grunde nur, dass OpenSSL zur Verschlüsselung von Verbindungen über ein Netzwerk (Internet) gedacht ist. (Was die verschiedenen Protokolle bedeuten, wird hier nicht weiter betrachtet). OpenSSL ist _[open source](https://de.wikipedia.org/wiki/Open_Source) _Software, und wird zum größten Teil von Menschen in deren Freizeit entwickelt. Um genau zu sein, hat die _[OpenSSL Software Foundation](http://opensslfoundation.com/), _unter deren Dach OpenSSL entwickelt wird, nur einen einzigen Vollzeitangestellten Entwickler [[4]](http://veridicalsystems.com/blog/of-money-responsibility-and-pride/).

Zur Technik:


_Vorweg, in diesem Artikel wird nicht darauf eingegangen wie [asymmetrische Verschlüsselung (Private-/Public-Key)](https://de.wikipedia.org/wiki/Asymmetrisches_Kryptosystem) funktioniert. Zur Erklärung des Bugs reicht es aus zu Verstehen, dass der Webserver eine Art geheimes Passwort hat, durch welches die verschlüsselte Kommunikation in beide Richtungen ermöglicht wird._


Auf jedem Webserver muss ein geheimes Passwort (Private-Key) gespeichert sein, mit welchem die Daten an den Browser verschlüsselt werden. Jeder der dieses Passwort kennt, kann alle Daten lesen die so verschlüsselt worden sind. (Es gibt Ausnahmen wie z.b. [Perfect-Forward-Secrecy](https://de.wikipedia.org/wiki/Perfect_Forward_Secrecy))
Damit nun ein Server verschlüsselte Daten senden und empfangen kann, wird die Verbindung (vom Browser zum Server und zurück) durch das OpenSSL-Programm geleitet. Da der Aufbau einer sicher verschlüsselten Verbindung recht aufwändig ist, wurde im Dezember 2011[[5]](http://heartbleed.com/) eine Funktion zu OpenSSL hinzugefügt, welche es erlaubt eine einmal geöffnete Verbindung aufrecht zu erhalten, für den Fall dass sie kurzfristig wieder gebraucht wird. Diese Funktion wird [Heartbeat-Erweiterung](https://tools.ietf.org/html/rfc6520) genannt. Heartbeat wird in der IT-Welt oft als Begriff genutzt, um etwas zu kennzeichnen was dafür gedacht ist zu bestimmen ob ein Programm/Verbindung/Partner noch aktiv oder schon "tot" ist. Daher der Begriff _Herzschlag._

Wenn nun also eine verschlüsselte Verbindung aufgebaut wurde, kann ein Partner ein sogenanntes Heartbeat Paket senden, die Gegenseite antwortet dann ihrerseits. Damit wissen beide Partner dass der Andere noch aktiv ist. Bleibt eine Antwort aus, ist fehlerhaft oder wird nicht innerhalb einer bestimmten Zeitspanne ein solches Heartbeat-Paket gesendet, wird angenommen dass die Verbindung nicht weiter gebraucht wird und geschlossen werden kann.

Diese Heartbeat-Funktion wurde am 01.01.2012 um 00:00:36Uhr [[6]](https://github.com/openssl/openssl/commit/bd6941cfaa31ee8a3f8661cb98227a5cbcc0f9f3) in OpenSSL aufgenommen. Das Verfahren um neuen Code in das Programm zu bekommen sieht vor, dass _irgendjemand _im Internet das Programm kopiert, und daran Änderungen vornimmt. Dieses nun veränderte Programm wird dann an das Team von OpenSSL geschickt. Ein Mitglied des Teams schaut sich nun die Änderungen an, und sofern keine Fehler gefunden werden und die Änderung nützlich ist (und noch eine ganze Reihe an anderen Kriterien erfüllt [[7]](http://www.opensslfoundation.com/contributions.html)) werden die Änderungen dann in das original Programm eingefügt.

Zum Kern der Sache:
Die Heartbeat-Erweiterung sieht vor, dass der Client (Browser) ein Datenpaket über die verschlüsselte Verbindung schickt. Dieses Datenpaket enthält mehrere Einträge. Davon sind Zwei entscheidend für den Fehler. Der erste (relevante) Eintrag ist eine kleine Menge von zufälligen TEST-Daten. Im zweiten Eintrag steht wie viele Daten der erste Eintrag haben soll `{"Test-BlaBla",11Buchstaben}`. Der Server nimmt dann dieses Datenpaket und schickt die Test-Daten wieder zurück. So kann der Browser erkennen, dass die Verbindung noch funktioniert.



Damit klar werden kann wie der Fehler zustande kommt, muss leider noch ein wenig weitere Erklärung sein:

[caption id="attachment_188" align="alignright" width="300"][![Schematische Darstellung des Speicherbereichs mit Private-Key](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/initial-300x205.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/initial.png) Schematische Aufteilung des Speicherbereichs von OpenSSL in 55 gleichgroße Bereiche/Blöcke. Der Private-Key (Geheimes Passwort) belegt dabei zufällig 10 Blöcke.[/caption]

Das Programm OpenSSL bekommt vom Server einen gewissen Bereich in dessen [Hauptspeicher (RAM)](http://de.wikipedia.org/wiki/Random-Access-Memory) zugewiesen, in dem es machen kann was es will. Dieser Speicher ist in Blöcke eingeteilt. In diesem Speicher liegt nun also auch irgendwo das geheime Passwort (welches ja notwendig ist zur Verschlüsselung)







Wenn nun ein valides Heartbeat-Paket vom Client beim Server ankommt  z.b. `Heartbeat-Paket{Krims,Krams,"Testdaten",9Buchstaben}`, wird dieses im

[caption id="attachment_186" align="alignleft" width="300"][![Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key und Heartbeat Daten](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/data-saved-300x205.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/data-saved.png) Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key und Heartbeat Daten[/caption]

Speicher von OpenSSL abgelegt. Dabei wird automatisch dafür gesorgt, dass kein Speicherblock doppelt vergeben wird. Allerdings können die Daten irgendwo im Speicher Blöcke belegen. Dass die Heartbeat-Daten hier die ersten Speicherblöcke belegen ist reiner Zufall. Dann sieht der Speicher in etwa so aus.





[caption id="attachment_189" align="alignright" width="300"][![Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key, Heartbeat Daten und angegebener Länge der Daten (rot markiert)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/regular-return-300x205.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/regular-return.png) Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key, Heartbeat Daten und angegebener Länge der Daten (rot markiert)[/caption]

Um zu antworten, verarbeitet der Server das Heartbeat-Paket indem er ausliest wie viele Daten der Client geschickt hat, und diese einfach zurück schickt. Dabei bezieht er sich auf die vom Client angegebene Länge (rot markiert). Der Server wird nun also einfach alle Daten, welche rot markiert sind, zurückschicken.





Das Problem hier kommt allerdings davon, dass der Server das Heartbeat Paket nicht richtig prüft/verarbeitet. Nochmal zur Info, der Client schickt Daten UND sagt gleichzeitig auch noch wie viele Daten er geschickt hat. Um zu verstehen was daran so schlimm ist müssen wir leider kurz zwei Zeilen Originalcode ansehen.



    
    /* Allocate memory for the response, size is 1 byte
    * message type, plus 2 bytes payload length, plus
    * payload, plus padding
    */
    buffer = OPENSSL_malloc(1 + 2 + payload + padding);
    bp = buffer;
    
    /* Enter response type, length and copy payload */
    *bp++ = TLS1_HB_RESPONSE;
    s2n(payload, bp);
    memcpy(bp, pl, payload);
    


Dieses kleine Stück Code[[7]](http://www.opensslfoundation.com/contributions.html) (das ist wirklich ein Stück **unbearbeiteter** Originalcode) enthält den Fehler. Dieser Code <del>wird</del> wurde vom Server beim Empfang einer Heartbeat Nachricht ausgeführt.

Nehmen wir weiterhin das Beispiel `Heartbeat-Paket{Krims,Krams,"Testdaten",9Buchstaben} `und gehen dabei durch wie der Code diese Daten verarbeitet.



	
  * In Zeile 5 werden einige Blöcke (Arbeits)Speicher bereitgestellt, und in der Variable **_bp_** wird notiert an welcher Stelle des Arbeitsspeichers sich diese Blöcke befinden. In diese Blöcke werden später die Daten, welche die Antwort bilden, hereingeschrieben bevor die Antwort versendet wird.

	
  * In Zeile 11 passiert dann das Unglück. Die Variable **_payload_**, ist die Angabe wie viele Speicherblöcke die Test-Daten, laut Client, angeblich belegen (Hier also 9 Blöcke/Buchstaben).

	
  * Die Variable _**pl** _sagt, bei welchem Speicherblock der Eintrag mit den Test-Daten anfängt (in unserem Beispiel bei 1). Diese Variable wird kompliziert an anderer Stelle erzeugt.

	
  * Die Funktion **_memcpy(bp, pl, payload)_** wird nun also mit den Informationen über die Speicherblöcke der Antwort, den ersten Block der Test-Daten (Block 1) und der Angabe wie viele Blöcke die Test-Daten groß sind (9 Blöcke), aufgerufen. Die Funktion kopiert dann den Inhalt von insgesamt 9 Blöcken, angefangen von Block 1 an, in den Speicherbereich _**bp**. _(bp wurde hier nicht grafisch dargestellt, da es nicht relevant ist)


Soweit, so gut. Wenn ein Client nun also ein korrektes Heartbeat-Paktet verschickt, funktioniert alles wie gewollt.

Angenommen der Client sendet nun aber absichtlich ein falsches Heartbeat-Paket an den Server. Z.b. `Heartbeat-Paket{Krims,Krams,"Testdaten",38Buchstaben}`

[caption id="attachment_187" align="alignright" width="300"][![Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key, Heartbeat Daten und falsch angegebener Länge der Daten (rot markiert)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/heartbleed-returnpng-300x191.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/heartbleed-returnpng.png) Schematische Darstellung des Speicherbereichs in Blöcken von OpenSSL, mit Private-Key, Heartbeat Daten und falsch angegebener Länge der Daten (rot markiert)[/caption]

Dann denkt der Server, dass 38 Blöcke im Speicher mit Test-Daten belegt sind, obwohl in Wirklichkeit nur 9 Blöcke mit Test-Daten belegt sind. Er wird nun also fröhlich 38 Blöcke des Speichers als Antwort an den Client senden. Das könnte dann ungefähr so aussehen







Wenn nun in diesen zusätzlichen Blöcken, die zurückgeschickt werden, zufällig das geheime Passwort (Private-Key) stehen, bekommt der Client also das Passwort geschickt.  Das ist der fatale Fehler, welcher fast die gesamte _sichere_ Kommunikation im Internet der letzten Zwei Jahre gefährdet.

Die Beseitigung dieses Fehlers hingegen ist recht trivial.

    
    	
            if (1 + 2 + payload + 16 > s->s3->rrec.length)
    		return 0; /* silently discard per RFC 6520 sec. 4 */
    	pl = p;
            [...]
    
    		/* Allocate memory for the response, size is 1 byte
    		 * message type, plus 2 bytes payload length, plus
    		 * payload, plus padding
    		 */
    		buffer = OPENSSL_malloc(write_length);
    		bp = buffer;
    
    		s2n(payload, bp);
    		memcpy(bp, pl, payload);
    
    


Im Grunde wurden hier nur zwei Zeilen hinzugefügt, und der Rest des Codes funktioniert weiterhin wie vorher.



	
  * In Zeile 1 (1 + 2 + payload + 16 > s->s3->rrec.length) wird nun eine Überprüfung vorgenommen ob die Gesamtlänge des Heartbeat-Paket mit der Längenangabe des Clients übereinstimmt.

	
    * Die 1+2+16 sind notwendige Detailangaben die für das Verständnis unwichtig sind.

	
    * **_payload_** ist die angebliche Länge der Test-Daten, die der Client gemeldet hat

	
    * **_rrec.length_**_ ist die echte Länge des ganzen Heartbeat-Pakets_




	
  * Zeile 2 wird dann ausgeführt, wenn in Zeile 1 herauskommt, dass die Längenangabe nicht mit der echten Länge überseinstimmt.

	
    * **_return 0_**_ _bedeutet dass der gesamte Vorgang abgebrochen wird und die Verbindung beendet wird.




	
  * Der Rest der Funktion funktioniert wie vorher, wird aber nur ausgeführt, wenn bei der Längenprüfung herauskommt, dass alles in Ordnung ist.




Fehler in der Art wie der oben beschrieben, sind recht häufig. Hier jedoch ist er erstens sehr fatal, und zweitens besonders unprofessionell. Sowas hätte auch definitiv bei einer Überprüfung durch das OpenSSL Team auffallen müssen. Allerdings muss dabei bedacht werden, dass das Team, welches dafür zuständig ist, nur genau einen festangestellten Mitarbeiter hat. Es kann also gesagt werden, dass es genau eine Person gibt, welche festangestellt ist für eine Software welche für über 60% der Verschlüsselung des gesamten Internets eingesetzt wird.
Das könnte die Frage aufwerfen, ob es nicht also besser wäre für solche Zwecke keine freie Software einzusetzen, sondern [geschlossene proprietäre Software](https://de.wikipedia.org/wiki/Propriet%C3%A4r). Meine Antwort ist dabei ein absolut und ganz klares **_NEIN._**_ _Denn nur in freier Software können solche Sicherheitslücken überhaupt erst entdeckt und dann auch geschlossen werden.

Dazu ein Beispiel. Diese Sicherheitslücke wurde am 01.04.2014 entdeckt, und bereits am 7.4.2014 wurde der Fehler beseitigt und bekanntgegeben was passiert war. Ob dies jetzt eine gute oder schlechte Reaktionszeit war, muss jeder selbst entscheiden. Allerdings sollte dabei bedacht werden, dass wenn ein solcher Fehler in einem z.B. Adobe Produkt auftreten würde. Dann würde diese Sicheitslücke WENN ÜBERHAUPT frühestens mit dem nächsten [Patchday](http://de.wikipedia.org/wiki/Patch_Day) geschlossen. Einen Patchday gibt es bei Adobe in etwa vierteljährlich. Zusätzlich würde je nach Unternehmen noch nichteinmal darüber Informiert werden, dass die Nutzer in Gefahr waren, und ihre Passwörter ändern sollten.

Was also tun, damit so etwas nicht noch einmal passiert?
Ausschließen lassen sich solche Fehler nie zu 100%. Allerdings wäre es schon viel Wert, wenn kostenlose freie Software nicht wie ein Selbstbedienungsladen behandelt werden würde, sondern große Unternehmen, welche sich durch die Nutzung von freier Software die Entwicklung und Wartung eigener Software sparen können, etwas zurückgeben würden an die Community anstatt nur einseitig zu profitieren. Besonders wenn es um so essentielle Dinge wie OpenSSL geht.

Abschließend hier noch ein xkcd der den Fehler in SEHR SEHR kurzer Form zusammenfasst.

[caption id="" align="alignnone" width="640"][![](http://imgs.xkcd.com/comics/heartbleed_explanation.png)](http://xkcd.com/1354/) XKCD: Heartbleed erklärt[/caption]

Quellen:[
[0] http://heartbleed.com/ When was Heartbleed discovered ](http://heartbleed.com/)[
[1] http://heartbleed.com/ How widespread is this?
](http://heartbleed.com/)[[2] http://news.netcraft.com/archives/2014/04/02/april-2014-web-server-survey.html Webserver Marketshare of active Sites
](http://news.netcraft.com/archives/2014/04/02/april-2014-web-server-survey.html)[[3] https://de.wikipedia.org/wiki/OpenSSL Was ist OpenSSL
](https://de.wikipedia.org/wiki/OpenSSL)[[4] http://veridicalsystems.com/blog/of-money-responsibility-and-pride/ Nur eine Vollzeitkraft bei openssl
](http://veridicalsystems.com/blog/of-money-responsibility-and-pride/)[[5] http://heartbleed.com/ What versions of the OpenSSL are affected?
](http://heartbleed.com/)[[6] https://github.com/openssl/openssl/commit/bd6941cfaa31ee8a3f8661cb98227a5cbcc0f9f3 Der fehlerhafte Commit
](https://github.com/openssl/openssl/commit/bd6941cfaa31ee8a3f8661cb98227a5cbcc0f9f3)[[7] http://www.opensslfoundation.com/contributions.html Richtlinien zur Beteiligung](http://www.opensslfoundation.com/contributions.html)



Anmerkung:
Einige Details wurden hier absichtlich vereinfacht oder ungenau dargestellt, da sie für das Verständnis des Fehlers unerheblich sind, die Erklärung zu umständlich machen würde oder zu viel Hintergrundwissen erfordern würde.  Für eine noch tiefere Erklärung des Fehlers am Code empfehle ich diesen [Eintrag](http://www.theregister.co.uk/2014/04/09/heartbleed_explained/)
