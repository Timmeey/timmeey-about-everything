---
author: Timmeey
comments: true
date: 2013-08-07 12:51:50+00:00
layout: post
link: https://blog.timmeey.de/speed-up-your-ssh-transfers-in-trusted-environments-from-14mbs-to-80mbs/
slug: speed-up-your-ssh-transfers-in-trusted-environments-from-14mbs-to-80mbs
title: Speed up your SSH transfers in trusted environments, from 14MB/s to 80MB/s
wordpress_id: 141
categories:
- IT
- Kurzmeldungen
---

I just finished editing a Video, now i tried to copy it over to my backup server over SSH.
But i noticed the slow transfer rate. Just around 14MB/s, over  Gigabit Ethernet...

So i started to play around with the SSH settings. I found out it is the encryption which was slowing down my transfer.

Here is my littel test setup.

I copied around 1000mb /dev/urandom into my RAM (/tmp/muhtest). I used /dev/urandom to have "real" data.

<!-- more -->So here are my tests and results

Default settings:
`cat /tmp/muhtest |pv | ssh  xxx@192.168.1.2 "cat >> /dev/null" `

Which gave me 1000MB 0:01:04 [15.7MB/s]

With blowfish encryption
` cat /tmp/muhtest |pv | ssh -c blowfish-cbc xxx@192.168.1.2 "cat >> /dev/null"`
Which gave me 1000MB 0:00:40 [25.1MB/s]

With arcfour encryption
` cat /tmp/muhtest |pv | ssh -c arcfour xxx@192.168.1.2 "cat >> /dev/null" `
Which gave me 1000MB 0:00:25 [39.6MB/s]

No encryption over netcat
` cat /tmp/muhtest |pv | nc 192.168.1.2 9876`

Which gave me 1000MB 0:00:12 [78.4MB/s]



You can say that if you are in a trusted environment, you can use the unencrypted transfer method over netcat. But if you do not want to setup a new transfer method and want to use standart scp(or just mount with sftp in nautilus or something), you can use the arcfour encryption to get good speeds. I don't know anything about this cipher, but when i'm in an environment where i would use unecrypted transfer, i could also use a POSSIBLY weak encryption, without spending too much effort setting up a new method for file transfers.

Now i just need a method to determine whether I'm at home, in my trusted environment or not, to set the cipher automatically.
