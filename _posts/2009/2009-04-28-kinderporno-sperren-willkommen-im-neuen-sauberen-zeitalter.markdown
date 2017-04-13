---
author: Timmeey
comments: true
date: 2009-04-28 20:22:40+00:00
layout: post
link: https://blog.timmeey.de/kinderporno-sperren-willkommen-im-neuen-sauberen-zeitalter/
slug: kinderporno-sperren-willkommen-im-neuen-sauberen-zeitalter
title: Kinderporno Sperren... Willkommen im neuen sauberen Zeitalter
wordpress_id: 17
categories:
- IT
- Politik
tags:
- Content-Industrie
- Pressefreiheit
- Vorratsdaten
- Zensur
---

Es ist ja schon lange in den Focus der Öffentlichkeit gerückt worden... Nun hat die Bundesregierung unter Führung der Großen Koalition (Schwarz-Rot) und gegen den Widerstand der Opposition, ein Gesetz zur Sperrung von Seiten die Kinderpornographisches Material bereitstellen, beschlossen. Ja wunderbar dann haben wir ja das Allheilmittel gefunden, und es war so einfach[![](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/stoppschild_kipo_dns_sperre-296x300.jpg)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/stoppschild_kipo_dns_sperre.jpg)


Warum sind wir nicht schon früher auf die Idee gekommen??

<!-- more -->Naja diese Frage lässt sich relativ einfach beantworten. Weil dieses Stopp-Schild beim besten willen nicht hilft. Wahrscheinlich werden einige Zugriffe auf Kinderpornos zwar verhindert. Die Personen die wirklich an Kinderpornos interessiert sind, haben

A.) Schon lange ein funktionierendes Netzwerk aufgebaut über dieses sie ihre triebe befriedigen.

B.)Mit ein wenig Ahnung das Stopp-Schild umgehen.

Nun zur Erklärung wie diese Sperren Technisch umgesetzt werden, und warum man sie so einfach umgehen kann. Wenn jemand in seinen Browser (Beispieladresse) www.geilekinderpornos.de eingibt, dann kann der Browser damit erstmal nicht viel anfangen, da das Internet mit einmaligen Nummern (IPs) als Adressen funktioniert(über diese Nummern weiß der Browser dnan an welchen Server er die eigentliche anfrge schicken soll). Um also nun diese IP zu erhalten, schickt der Browser die Adresse (www.geilekinderpornos.de) an den Server eines Providers (dessen IPAdresse von Haus aus bekannt ist), und bittet diesen um rücksendung der IP Adresse des gesuchten Servers der die Seite (www.geilekinderpornos.de) beinhaltet.. Dieser Server des Providers (DNS-Server) kennt die Adressen aller Internetseiten, oder weiß zumindest welcher andere DNS-Server die Adresse kennen müsste. So nun zum Teil wie die Sperrung funktioniert. Der Provider dem der DNS-Server gehört, erhält vom BKA (oder in Zukunft auch von sonstigen Firmen) Adressen wie "www.geilekinderpornos.de", der Provider trägt diese Adressen nun in Listen ein. Wenn der DNS-Server nun nach einer IP-Adresse gefragt wird, durchsucht er die vorher erstellte Liste, ob die Seite die der Browser erfragt, dort auch steht. Steht die Seite dort nicht, schickt er dem Browser die Ip des Servers auf dem die Seite lagert. Steht die angeforderte Seite (in unserem Fall www.geilekinderpornos.de) auf der Liste, dann schickt der Server dem fragenden Browser nicht die IP des Servers auf dem die Seite liegt, sondern dieses Stopp-Schild. Kennt allerdings die Person die auf die Kinderporno Seite will, die IP-Adresse des eigentlichen Servers, umgeht er den DNS-Serer des Providers und bekommt also kein Stopp-Schild sondern die richtige Siete angezeigt... Diese IP-Adressen werden z.t. durch Spam e-mails, normale Mails, oder "Mund zu Mund" Propaganda verteilt...

Mal ganz davon abgesehen, werden die meisten Kinderpornos sowieso nicht über Internetseiten verbreitet sondern über verschiedene Netzwerke wie BitTorrent, oder Tauschbörsen wie Kazzaa o.ä.

Naja für die Bundesregierung ist das Thema Kinderpornos nun mit dieser Sperre gegessen, da sie noch nie etwas von DNS-Servern IP-Adressen oder sonstetwas gehört haben.

Die meisten sollten jetzt hellhörig geworden sein. Listen mit Internetseiten die gesperrt werden sollen?? Da steckt doch ein enormes missbrauchs potential drin. Alles nur Panikmache wird uns suggeriert. Und dennoch kaum ist das Gesetz beschlossen, kommen die ersten Rechte-Verwerter und fordern die Sperrung von weiteren Seiten, wie Rapidshare oder anderen. Das solche Forderungen kommen war klar, allerdings so schnell das ist eine Frechheit, das Gesetz ist nichmals eine Woche alt und schon soll es für "persönlcihe" Zwecke missbraucht werden. Naja ist ja egal. Ist der Ruf erst ruiniert, lebt es sich ganz ungeniert.( dazu sei noch die Anmerkung erlaub. "Thema Vorratsdaten, die Rechte-Verwalter haben angemeldet auch auf die gesammelten Daten zugreifen zu dürfen, ohne richtelrichen beschluss, ohne alles)

Abschließend noch eine Frage. Ist es nicht sinnvoller die Internetseiten die Kinderpornos anbieten zu entfernen bzw. die Hintermänner festzusetzen und sie zu bestrafen... Naja es ist einfacher die Seiten pseudo-zu-sperren als sich arbeit machen und die Seiten wirklich aus dem Netz zu bekommen. Generell finde ich die Gratwanderung zwischen Informationsfreiheit und notwendige Zensur von Illegalen Inhalten sehr schwierig...
