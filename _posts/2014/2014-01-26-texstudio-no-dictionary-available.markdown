---
author: Timmeey
comments: true
date: 2014-01-26 11:09:56+00:00
layout: post
link: https://blog.timmeey.de/texstudio-no-dictionary-available/
slug: texstudio-no-dictionary-available
title: TeXstudio no Dictionary available
wordpress_id: 159
categories:
- Tipps
tags:
- Dictionary
- Rechtschreibprüfung
- spell-check
- TeXmaker
- TeXstudio
- Wörterbuch
---

I like TeXstudio, i think it is a great Program when working with LaTeX Projects.

But one thing is bugging me. TeXstudio has a Problem with spellchecking, more accurate with the dictionaries. First of all, you have to set the right path to you dictionaries in two Places in the Config Menu:
Options->Configure TeXstudio-> General -> "Spelling Dictionary Directoy" = (as i use Ubuntu) /usr/share/myspell/dicts
Options->Configure TeXstudio -> Grammar -> "Wordlist Directory" = /usr/share/myspell/dicts

But sometimes even that will not do the trick. When TeXstudio still refuses to work properly. In Config -> General choose a default spell-check Language wich is definitely not available. TeXstudio will then complain about it cannot find the files. When you then switch back to you desired langue (which, of course, should be installed and available) it should work.

Just as a small tip, so you don't have to search and click around forever.

I guess this should work also with TeXmaker if you have the same problems there.
