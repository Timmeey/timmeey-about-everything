---
layout: post
title: German lawyers are now required to install a crypto backdoor
date: '2017-12-27 00:21'
---

# German lawyers are now required to install a crypto backdoor
Starting on 1.1.2018 all german lawyers are **required** to use the [_special electronic lawyer mailbox_ (beA)][a0bf7d8b] for communication with authorities and courts, which requires installation of a ROOT-CA(with a publicly known private-key) into the system.

## Technical background for non-IT experts
Internet connections are mostly encrypted to protect sensitive data. To make sure the connection is not only encrypted, but you are also talking to the intended server, connections are _signed_. To do this, windows/mac comes with a list of _signatures_ which are considered trustworthy (ROOT-CA). Websites who want to support trusted and encrypted connections are buying a custom signature (imagine a real signature like on a paper document) from the trustworthy ROOT-CA. When someone now tries to connect to such a website, it presents this custom made signature, the connecting system compares this signature to the known trusted signature(the ROOT-CA) (which came with windows/mac). If it is a match, the system knows it is talking to the correct website and not a _man in the middle_([^MITM]). Otherwise the connection is refused and a warning is shown.

_beA_ requires the users to add a new ROOT-CA to their systems. The problem here is that the software contains the original _signature_ of the ROOT-CA. With this original signature it is possible to create custom-signatures for any website (like google.com or others). Since the system now trusts all custom-signatures by this ROOT-CA, the whole trust/encryption infrastructure of the Internet is broken for everyone who has this ROOT-CA installed.

## beA delivered a Certificate including the private key
This software requires communication with _bealocalhost.de_ (which resolves to a server running on localhost). To enable TLS(https) for this connection the software brought its own Certificate signed by a regular Certificate Authority [(TeleSec)](https://www.telesec.de/en/), but also included the private-key. Therefore TeleSec revoked this certificate in accordance with general CA [regulations](https://cabforum.org/baseline-requirements-documents/).

## Fixing the issue by making it even worse
Up until then the compromised private Key could only be used to [^MITM] connections in the context of the _beA_ (which is already bad enough). But after the CA revoked the certificate, systems refused to establish a connection with the revoked certificate. To fix this issue the company behind _beA_ [(Atos IT Solutions and Services)](https://atos.net/en/) released an update including instructions how to install a new ROOT-CA into the System. For the _beA_ software running on localhost to present a valid certificate it again contained the private key. But this time it was the private key for the new ROOT-CA. With this private key it is now possible to issue valid certificates for ANY domain.

## Consequences
Since the Systems now trust this new root-ca, it is possible to [^MITM] every connection of such a system which has the **mandatory** _beA_ software installed, instead of **just** connections related to _beA_.

## Now what?
German lawyers are required to use this software. But as lawyers they are also trusted with very sensitive information. As an IT-security aware person i cannot see any acceptable solution to the current situation, besides NOT installing _beA_. But since important documents are sent via _beA_ you cannot just not use the software. To revert back to the old situation, where **only** connections related to _beA_ are compromised, you could have a computer just for _beA_ and nothing else, so only data sent/received via _beA_ would be compromised. I have no idea what the legal situation is in that case.

For now the _beA_ servers are unavailable due to [_infrequent connection issues_](http://bea.brak.de/2017/12/22/wartungsarbeiten-am-bea/). The tutorial on how to install the new ROOT-CA is also unavailable, so is the new ROOT-CA itself. Shutting down the _beA_ servers does **NOT** fix the issue. Only manually removing the new ROOT-CA from all systems does, but also prevents usage of _beA_.

## How could this happen?
I have no idea how such a completely broken system could ever be released, especially if you consider the special confidentiality needs of lawyers. The only way i can describe it is as "A total FAIL. Happy Birthday!". You are welcome to quote me on that.

Stay tuned for more information and more issues with _beA_ as the original reporter of this vulnerability is giving a session during this years [#34C4](https://events.ccc.de/congress/2017/wiki/index.php/Session:BeA_-_das_neue_Anwaltspostfach)

## Sources
Unfortunately most sources are in german
[Golem](https://www.golem.de/news/bea-bundesrechtsanwaltskammer-verteilt-https-hintertuere-1712-131845.html) reported on the issue on 23.12.2017

[Heise](https://www.heise.de/newsticker/meldung/beA-Schwere-Panne-beim-besonderen-elektronischen-Anwaltspostfach-3927314.html) reported on the issue first on 22.12.2017

[Markus Drenger](https://twitter.com/reg_nerd) originally found the issue

[Matthias Bergt warnung vor dem beA](http://www.cr-online.de/blog/2017/12/23/warnung-vor-dem-besonderen-elektronischen-anwaltspostfach-bea/) Lawyer specialized in IT

[a0bf7d8b]: http://bea.brak.de/ "Das besondere elektronische Anwaltspostfach"
[^MITM]:  [MITM](https://de.wikipedia.org/wiki/Man-in-the-middle-Angriff) Attack on connections to gain confidential data by intercepting a regular connection, reading the content and then relaying the original/modified request/response to the correct host, without the server/client knowledge.
