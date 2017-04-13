---
author: Timmeey
comments: true
date: 2016-05-13 13:24:31+00:00
layout: post
link: https://blog.timmeey.de/advanced-netflix-unblocking-setup-with-openwrtopenvpn-and-uflix/
slug: advanced-netflix-unblocking-setup-with-openwrtopenvpn-and-uflix
title: Advanced Netflix unblocking setup with OpenWrt/OpenVPN and uflix (DISCONTINUED)
wordpress_id: 237
categories:
- IT
- Tipps
---

****************UPDATE: Uflix discontinued Netflix support************
****As of this week uflix announced ([in this Statement](https://uflix.com.au/netflix-update-2/)) they would discontinue their Netflix unblocking service. Other services like AmazonPrime etc. are not affected.
**




First of all. If you don't know what OpenWrt/OpenVPN/iptables/routes are/mean this is **NOT** for you. If you just want the average netflix unblocking solution, head over to [google.com](https://google.com) or try the simple [[1]uflix.com.au](https://uflix.com.au/) setup. This is not meant to be a clickByClick tutorial, you need to understand what is happening here to apply it to your own network architecture. By the way, i'm not affiliated with uflix, i just like their service for a reasonable price.
<!-- more -->
Okay. Let's get started:


## The Problem


Netflix started blocking smartDNS Proxies in early 2016. They also started blocking known VPN addresses. (I'm not quite sure how they could tell apart my Proxy in the US [which i was using before] from legit US IPs)[[2]](https://torrentfreak.com/netflix-announces-crackdown-on-vpn-and-proxy-pirates-160114/) [[3]](https://media.netflix.com/en/company-blog/evolving-proxy-detection-as-a-global-service)
Since Netflix sold the right for their own production _House of Cards_ to their competitor [[4]](http://www.hollywoodreporter.com/news/netflix-subscribers-europe-wont-get-714647), i wasn't able to watch the new season anymore. I had to do something....


## My Setup




    Berlin, Germany (US-Netflix is blocked here)
    TP-Link Archer C7 v2
    OpenWrt Chaos Calmer 15.05 / LuCI
    uFlix subscription (that is not optional)




## How it works


uFlix is offering smartDNS and an openVPN gateway. In theory you could do that yourself with a couple of US IPs, but you probably would have to change IPs all the time when they get blocked. So i offloaded the endpointmanagement to uFlix.
uFlix is providing a smartDNS where they can control to what all the Netflix Domains resolve. That way you can set routes in your openVPN to funnel all(only) the Netflix traffic through the VPN. For a normal enduser this is quite an easy setup. Change your main DNS to the uFlix provided DNS and fire up the provided openVPN config. The only downside with that is, that from now on ALL DNS requests will go to the uFlix DNS, which is not a technical problem but could be a privacy problem. But if you don't have a regular network setup (like me), you run into all kinds of problems. Some of your iptables rules could get confused, and your DNS adblocking setup definitely goes down the drain.


## The solution


I use OpenWrt on a _TP-Link Archer C7 v2_  as my router (and the default ISP modem, but that is encapsulated from my network by the TP-Link). Thanks to uFlix using openVPN you can setup openWrt to do all the vpn/routing/dns stuff for your whole network.


#### DNS


You don't want all the DNS request to go through the uFlix DNS. To make that happen, use the DNSForwarding settings to only forward the necessary Domains to uFlix.On your openWrt webinterface go to  "Network -> DHCP and DNS", there you will find the section _DNS forwards. _There you need to add some Netflix domains to be resolved via the uFlix server. Currently (13.05.2016) the Domains are:




  1. /nflxvideo.net/ IMPORTANT ( i found this address by looking in my DNSCache)


  2. /netflix.com/


  3. /uflixdns.com/


  4. /uflix.com.au/ Just for good measure


[![DNSForwards](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/DNSForwards-300x262.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/DNSForwards.png)You need to append the uFlix DNS IP of your "most local" Server according to [uflix/setup](https://uflix.com.au/setup/) which was _139.162.161.46_ (Europe)for me.
I don't know if that is the minimal setup. What i do know is that these Domain names could change over time. So if your Netflix stops working go check on that (just have a look at your local DNSCache, which domains are related to netflix).

From now on all DNS requests to those domains (and their subdomains) will get forwarded to the uFlix DNS.

Additionally to that (thanks [@DB9MAT](https://twitter.com/DB9MAT) for reminding me to mention that) the android app and chromecast use the hardcoded googledns server, so you can't just switchout the used dns server for those devices. In order to still get them to use the uFlix DNS you have to setup some iptables rules which redirect all dns traffic which goes to 8.8.8.8 and 8.8.4.4 to the right dns server (in this case localhost /the router). For that go to "Network->Firewall->Port Forwards" and add a rule which forwards all traffic that is going to 8.8.8.8 on port UDP:53 to localhost port UDP:53. Do the same thing for 8.8.4.4.
This is how it looks for me [![dnsForward](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/dnsForward-235x300.png)](/media/images/{{ page.date | date: '%Y-%m-%d' }}-{{ page.slug }}/dnsForward.png)


#### OpenVPN


Now comes the "hard" part. Setting up the VPN is a little bit fiddly since the luCI interface does not offer all the needed vpn settings. Anyway.
I have the following openvpn packages installed




  * luci-app-openvpn (for the luCI webinterface)


  * luci-i18n-openvpn-de (just for convenience)


  * openvpn-openssl - 2.3.6-5 (the main part)


If you run into problems with opening the webinterface for openvpn try deleting/touching /etc/config/openvpn (there are even some bug reports about that)

To get your openVPN config go to [uflix/setup/router](https://uflix.com.au/setup/router/).
Now in the openwrt Interface create a new vpn config with "Client configuration for a routed multi-client VPN" (i'm not sure if it really makes a difference what you choose here, it will just setup some default fields). Setup all the options (you will need to use the extended options too), and upload the ca.crt you got from uFlix. After you set up everything hit **Save&Apply_._**

The catch here is that the webinterface does not support to set the option for your username/password that you need to connect. This is where you have edit the generated configfile yourself.

First create a new username/password file somewhere on the router in a persistent location (do not put it into /tmp). I put it into /root/uflix/authpass.txt.That file needs to contain your uFlix username and the password in the format


    uFlixUsername
    uFlixPassword


Once you saved that, open _/etc/config/openvpn. T_here you will now see a section "config openvpn $theNameYouChose". That section contains all the settings you just set up via the Interface. You now need to add the Username/Password. That option is called _auth_user_pass _and it accepts a path to a textfile containing the username/password combination (maybe it accepts the username/password directly but i didn't find out how). So add option auth_user_pass '/root/uflix/authpass.txt' to the section, and save the file. In my case it now looks like this


    config openvpn 'uflix'
    	option float '1'
    	option client '1'
    	option dev 'tun'
    	option reneg_sec '0'
    	option verb '3'
    	option persist_key '1'
    	option nobind '1'
    	option remote_cert_tls 'server'
    	option remote 'vpn-gw-usa2.uflix.com.au'
    	option comp_lzo 'no'
    	option cipher 'none'
    	option ca '/lib/uci/upload/cbid.openvpn.uflix.ca'
    	option auth_user_pass '/root/uflix/authpass.txt'
    	option enabled '1'
    	option pull '1'



Go ahead and start the VPN via the interface. if you now have a look at the logs logread  you should see openVPN printing out what it is doing, including the routes that it is setting up (or atleast some error messages as to why it is not working).




## Caveats


Since you restricted the DNS to only forward relevant queries to the uFlix DNS, there should be no problems.

The vpn is a different story. You saw the routes that openvpn is setting up in the background once it connects? Those routes are getting pushed to openVPN by uFlix. Those routs determine how your router is routing your traffic. I theory uFlix could push a route that all your Internet traffic is going through their VPN, which would be a huge violation of privacy and trust. I don't think they would do that, but it a theoretical possibility you need to be aware of. But if you are not lazy (like me) and don't just want to accept the routes uFlix is giving you, you could tell openVPN to ignore those pushed routes and configure them yourself. Since you see all the routes in the log, it should not be too hard to make that happen, just takes some effort. But you would also have to to some maintenance every now and then if those routes change.

Another thing is. The vpn tunnel is not encrypted. It's not a big deal, since the connections to Netflix itself are still (or should be) going over an encrypted https connection.

You should now have a working unblocking solution for Netflix. If you run into any problems you might want to check if the DNS resolution is correct. dig netflix.com  should now give you _uflix.com.au_ as the AUTHORITY SECTION.

**UPDATE:**

For whatever reason the vpn is currently (13.05.2016) not being needed/used, and it still works. The smartDNS alone seems to do the trick at the moment. I have no clue what is going on.



[[1]](https://uflix.com.au/): uflix.com.au
[[2]](https://torrentfreak.com/netflix-announces-crackdown-on-vpn-and-proxy-pirates-160114/): Torrentfreak: Netflix announces crackdown on VPN and Proxy pirates
[[3]](https://media.netflix.com/en/company-blog/evolving-proxy-detection-as-a-global-service): Netflix: Evolving Proxy Detection as a Global Service
[[4]](http://www.hollywoodreporter.com/news/netflix-subscribers-europe-wont-get-714647):Hollywoodreporter: Why Netflix Subscribers in Europe Won't Get 'House of Cards'
