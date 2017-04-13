---
author: Timmeey
comments: true
date: 2012-05-07 23:20:11+00:00
excerpt: 'So können Kontakte vom lokalen iPhone Adressbuch zu einem CardDav Account
  exportiert werden. Oder: How to export iPhone Contacts to an external CardDav Account(Owncloud
  CardDav)'
layout: post
link: https://blog.timmeey.de/iphone-kontakte-zu-carddav-exportieren/
slug: iphone-kontakte-zu-carddav-exportieren
title: iPhone Kontakte zu CardDav exportieren
wordpress_id: 77
categories:
- IT
tags:
- carddav
- export
- iPhone
- owncloud
---

**<UPDATE September 2014> Dieser Artikel ist ur-alt und vermutlich geht es inzwischen auch einfacher. Ich werde ihn aber gerne updaten, wenn ich zufällig mal was höre.

This post is outdated. Please keep that in mind
</UPDATE>**




Nachdem ich nun einige Zeit damit vebracht habe [Owncloud](http://owncloud.org) auf meinem Server zu installieren, wollte ich es nun auch benutzen.




Weitere Infos bzgl. den Besonderheiten bei iOS Geräten [hier](http://owncloud.org/synchronisation/ios/).
Zusätzlich noch hier mein .htaccess File(Achtung nur OC Version 3.0.3)


`ErrorDocument 404 /core/templates/404.php
<IfModule mod_php5.c>
php_value upload_max_filesize 512M
php_value post_max_size 512M
SetEnv htaccessWorking true
</IfModule>
<IfModule mod_rewrite.c>
RewriteEngine on
RewriteRule .* - [env=HTTP_AUTHORIZATION:%{HTTP:Authorization},last]
RewriteRule ^.well-known/carddav /apps/contacts/carddav.php [R]
RewriteRule ^.well-known/caldav /apps/calendar/caldav.php [R]
</IfModule>
Options -Indexes`

Nachdem der CalDav und CardDav Account auf dem iPhone nun also eingerichtet und funktionstüchtig war, wollte ich nun<!-- more --> natürlich auch meine "lokal" auf dem iPhone gespeicherten(ignorieren wir mal die iCloud funktionalität) Kontakte zum CardDav Account kopieren/verschieben.
Dies war der Anfang einer endlose Google Odyssee. Nach einigen Stunden rumgegoogle musste ich feststellen dass es wohl keine vernünftige Lösung ohne viel extra Software oder Jailbreaks gibt. Also probierte ich auf eigene Faust einen Weg zu finden. Soviel Vorweg: Es gibt ihn, den Weg ohne viel Extra Software.

Also: Wie gehts?

Zuerstmal booten wir ein Windows(z.b. ein XP in einer VM). Dann muss das iPhone einmal vollständig mit iTunes synchronisiert werden(damit die Kontaktdaten auf dem Rechner landen). Dazu muss darauf geachtet werden, dass in iTunes beim Reiter "info" Kontakte synchronisieren ausgewählt ist.

Ich habe sie einfach von iTunes ins Windows Adressbuch kopieren lassen. Nachdem die Synchronisation beendet ist, wirdThunderbird geöffnet und dort alles vom Assistenten importieren lassen(Tools/Extras --> Import). Danach sind die Kontakte im Thunderbird Adressbuch verfügbar. Zum Glück hat Thunderbird eine Exportfunktion für ganze Adressbücher.
Also einmal alle Kontakte markieren und dann nach cvs(Comma seperated Values) exportieren(Tools/Extras --> export).
Diese Datei habe ich dann auf mein normales Ubuntu zurückkopiert. Der Windows-Teil wäre nun abgeschlossen, und die möglicherweise benutzte VM kann beendet werden.[![Fenster LibreOffice import cvs](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/OOimport-300x237.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/OOimport.png)

Diese rohe CSV Datei kann nun Problemlos in ein Adressbuch von Evolution Importiert werden. Allerdings musste ich dabei feststellen dass Evolution mit dem Format der Daten nicht wirklich gut klar kam, und daher einige Felder durcheinander geworfen hatte. Also die Datei mit LibreOffice öffnen und beim dort erscheinenden Import Dialog nur Kommata als Trenner anklicken.


Das ganze erschien in einer schön formatierten Tabelle. In der ersten Zeile stehen die Bezeichner für jede Spalte. Thunderbird hatte diese Spaltenbzeichnungen allerdings in Deutsch eingefügt. Ich nehme an das ist der Grund warum Evolution damit nicht klar kam. Aufjedenfall habe ich dann die Spalten Bezeichnungen angepasst und einige gelöscht. [Hier eine Liste an valider Bezeichner](http://wiki.ubuntuusers.de/Evolution#Datenmigration)




Vorher:[![cvs vor der Bearbeitung](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/vorher-300x24.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/vorher.png)




Nachher:[![cvs nach der Bearbeitung](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/nachher-300x29.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/nachher.png)


Beim zweiten Versuch die CVS Datei in Evolution zu importieren ging dann alles gut, und ich hatte alle meine Kontakte schön sauber sortiert vor mir stehen. Der rest war simpel. Ich habe ein weiteres Adressbuch in Evolution angelegt (Art: WebDav). Bei Adresse habe ich https://<pfad zur Owncloud instanz>/apps/contacts/carddav.php/addressbooks/<benutzername>/<adressbuch name> angegeben, wobei <adressbuch name> für den Namen des jeweiligen Adressbuches steht, dass ihr in Owncloud angelegt habt, Standard ist dort "default". [![Eigenschaften des Evolution Adressbuchs](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/evolutionbook-300x114.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/evolutionbook.png)Nachdem ich dann noch mein Passwort eingegeben habe war das Adressbuch nun zu sehen(zwar noch leer aber das wird ja gleich befüllt). Jetzt müssen nur noch alle Kontaktdaten, die kopiert werden sollen, markiert werden und rübergezogen werden. Evolution beginnt nun diese Daten auf den Server zu laden. Kurze Zeit später sollten dann auch schon die ersten Kontakte auf dem iPhone erscheinen.



Das war es auch "schon". Es ist sicherlich nicht der beste Weg seine Kontakte vom iPhone zu kopieren, aber immerhin ist es ein WEG :-)

Natürlich kann man so auch seine iPhone Kontakte auf ein neues Android Handy oder ähnliches kopieren.



Noch einmal in Kurzform:

iPhone backup machen in iTunes, iTunes die Kontakte zum Windows Adressbuch hinzufügen lassen. Thunderbird öffnen und "outlook" importieren lassen. Danach das Adressbuch als CVS im Thunderbird exportieren. Dann diese CVS Datei mit einem Editor bearbeiten bis die Spalten passen und einige Daten korrigieren. Danach das bearbeitete CVS in Evolution importieren(checken obs gut ausschaut) und das dann mit dem WebDav synchronisieren.



The short form, in English:
**This post is outdated.**
1. Backup your iPhone with iTunes(Keep an eye on the Info tab, make sure the Contacts are synced). Let iTunes sync the Contacts to your Windows Adressbook
2. Open Thunderbird and let it import everything from "Outlook"(It will import the Windows Addressbook)
3. Export the entire Addressbook to CVS
4. Edit the CVS file to your needs, Evolution may dont like the standart syntax( [before](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/vorher.png), [after](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/nachher.png))
5. Import the CVS File to Evolution
6. Set up an WebDav Addressbook in Evolution (like [here](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/OOimport.png))
7. Copy the imported Contacts to the newly created WebDav Addressbook
8. And BINGO we're done :-) The first Contacts should now show up on your iPhone

I'm sure there are other better ways. But this is a WAY, without a whole bunch of software and without the need to root your phone.
