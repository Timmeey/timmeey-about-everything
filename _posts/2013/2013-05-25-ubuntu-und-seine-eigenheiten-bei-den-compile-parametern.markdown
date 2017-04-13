---
author: Timmeey
comments: true
date: 2013-05-25 19:05:31+00:00
excerpt: 'Neulich startete ich mit einem Kollegen mein erstes C++ Projekt. Das heißt
  also erstmal mit gcc vertraut machen und ein wenig herumprobieren. Als es dann daran
  ging das erste mal unser Projekt zu bauen schmierte mit das ganze beim linken mit
  nem Haufen Fehlermeldungen "undefined reference to `foobar() collect2 : Error:ld
  returned 1 exit status". Nach langer Suche und viel Aggression fanden wir dann endlich
  das Problem, und sogar eine sehr einfache Lösung dazu.'
layout: post
link: https://blog.timmeey.de/ubuntu-und-seine-eigenheiten-bei-den-compile-parametern/
slug: ubuntu-und-seine-eigenheiten-bei-den-compile-parametern
title: Ubuntu und seine Eigenheiten bei den Compile Parametern
wordpress_id: 114
categories:
- IT
tags:
- C++
- collect2
- ld
- linker
- Ubuntu
---

Für einige Mag das ja ein alter Hut sein, aber ich habe mir bald die Haare ausgerissen bei diesem Problem. Neulich startete ich mit einem Kollegen mein erstes C++ Projekt. Das heißt also erstmal mit gcc vertraut machen und ein wenig herumprobieren. Als es dann daran ging das erste mal unser Projekt zu bauen schmierte mit das ganze beim linken mit nem Haufen von diesen Meldungen ab.
` source/Process.o: In function `__static_initialization_and_destruction_0(int, int)':
Process.cpp:(.text+0x6ef): undefined reference to `foobar::foobar1()'
Process.cpp:(.text+0x6fb): undefined reference to `foobar::foobar2()'
Process.cpp:(.text+0x707): undefined reference to `foobar::foobar3()'
collect2: Fehler: ld gab 1 als Ende-Status zurück
collect2: Error: ld returned 1 exit status`

<!-- more -->

Okay, vielleicht nicht die richtigen Libs installiert. Alles dreinmal nachgeprüft und bei meinem Kollegen, der ein Arch Linux benutzt, lief alles mit dem selben Code und dem selben Makefile wunderbar.

Also was heißt undefined reference? Etwas nachforschen ergab dass der Linker die genutzen Funktionen in den Libs nicht finden kann, obwohl er die Libs gefunden hat. Super, sind die Libs kaputt oder falsche Version etc. (ich hatte andere Versionen unter Ubuntu als in Arch). Aber bei ALLEN genutzten Libraries? Das konnte nicht sein. Also lag der Fehler definitiv irgendwo auf meiner Seite. Um das ganze zu verifizieren haben wir dann noch auf SUSE und Fedora getestet, und überall hat der gleiche Code funktioniert.

Gut kommen wir also zur Auflösung des ganzen.

Ubuntu hat seit 11.10 irgendwas umgestellt, was die bei den anderen Distributionen nicht so ist.
Hier ein kleiner Auszug aus unserem Makefile
`$CC $LIBRARY $OBJ -o $PROJECT_NAME`
Dieser Code rufe den Kompiler auf und übergibt ihm die zu linkenden Libs und die vorher kompilierten Dateien.

Was wir nicht wussten war, dass unter Ubuntu die Libs ($LIBRARY) HINTER die zu linkenden Dateien gehört. Aber anstatt ganz rumzumeckern, dass er die Libs nicht findet, sagt es gar nichts und schmiert dann beim linken einfach ab. Anscheinend werden die libs in $LIBRARY irgendwie interpretiert, denn wenn nicht alle nötig da drin stehen sagt er dass Libraries fehlen.

Des Rätsels Lösung war also aus
`$CC $LIBRARY $OBJ -o $PROJECT_NAME`
ein
`$CC $OBJ -o $PROJECT_NAME $LIBRARY`
zu machen. Unter Ubuntu MÜSSEN die Libs also zwingend ans Ende des Befehls.

Diese Erfahrung hat mich verdammt viele Nerven gekostet, und mich beinahe direkt wieder von C/C++ abgebracht.

Ich hoffe dieser Eintrag hilft einigen die ähnliche Probleme haben, Nerven zu sparen.
