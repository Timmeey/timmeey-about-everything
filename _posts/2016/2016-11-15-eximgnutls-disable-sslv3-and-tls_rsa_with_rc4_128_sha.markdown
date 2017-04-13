---
author: Timmeey
comments: true
date: 2016-11-15 17:01:34+00:00
layout: post
link: https://blog.timmeey.de/eximgnutls-disable-sslv3-and-tls_rsa_with_rc4_128_sha/
slug: eximgnutls-disable-sslv3-and-tls_rsa_with_rc4_128_sha
title: Exim/GnuTLS disable SSLv3 and TLS_RSA_WITH_RC4_128_SHA ;-)
wordpress_id: 281
categories:
- IT
---

How to get rid of the weak cipher (TLS_RSA_WITH_RC4_128_SHA) and SSLv3 in Exim4 with GnuTLS (which is the default on Ubuntu 14.04) using the tls_require_ciphers instruction in the exim config.

<!-- more -->

A few weeks ago i fiddled around with letsencrypt and exim. Getting the certs working in exim took a while, but that is for another story)

After i got the Certs working i did some testing. For this i used [ssl-tools.net](https://ssl-tools.net/mailservers/) (among others). It reported several issues. I had SSLv3 enabled and this weird **TLS_RSA_WITH_RC4_128_SHA **was reported as a weak cipher. Now began the journey that lasted several hours. I quickly arrived at the [gnutls manual](https://www.gnutls.org/manual/gnutls.html) with a list of "valid"config options. Which gave me the syntax to disable SSLv3 (just add **-VERS-SSL3.0 **to the available ciphers (the **-** disables this cipher))

Now ss-tools reported only a single issue with the weak TLS_RSA_WITH_RC4_128_SHA cipher. After some more googling around, and talking to other sysadmins (who were using openssl instead of gnutls) i just tried our the several options from the gnutls manual. A lot of stuff didn't work because my version is probably too old. Often when i got a working combination of enabled/disabled ciphers ssl-tools reported that my exim does not support [PerfectForwardSecrecy](https://en.wikipedia.org/wiki/Perfect_Forward_Secrecy)(PFS), but at least all weak ciphers were gone. It was ping-pong between weak ciphers, no PFS or some random error. I just could not find the gnutls name that would disable the reported weak cipher. Then i found a name [translation table](http://backreference.org/2009/11/18/openssl-vs-gnutls-cipher-names/) for cipher names according to the standard and their corresponding names in gnutls and openssl. That finally gave me the idea. I had to disable **ARCFOUR-128. **So my final working _tls_require_ciphers_ looked something like that **"SECURE:+VERS-TLS-ALL:-VERS-SSL3.0:-ARCFOUR-128**".

Tl;DR

In exim4 with GnuTLS on Ubuntu 14.04 to disable SSLv3 and TLS_RSA_WITH_RC4_128_SHA while keeping PFS, disable TLS_RSA_WITH_RC4_128_SHA with **-ARCFOUR-128 **and SSLv3 with **-VERS-SSLv3.0**
