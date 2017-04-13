---
author: Timmeey
comments: true
date: 2015-09-23 23:11:18+00:00
layout: post
link: https://blog.timmeey.de/performance-issues-blame-yourself-first-blame-the-language-later/
slug: performance-issues-blame-yourself-first-blame-the-language-later
title: Performance Issues? Blame yourself first, blame the language later
wordpress_id: 217
categories:
- IT
- Tipps
---




A friend approached me while we were at a bar for a birthday. He told me about his project where he had coded the same mathematical algorithm in Python AND C. He told me he was having performance issues with the python code being 200 times slower than the same algorithm as a C program.
I said to him that this must be wrong, he is doing something wrong, because even though Python is interpreted it isn't supposed to run that slow.




I'm not a Python guy myself, in extreme cases when a few lines of bash script just doesn't do the trick, i write some short pyhton scripts (about 50-100 loc at MOST). But i still want people to not have a completely wrong impression on what Interpreted/Bytecode languages (like Python or Java) can achieve in relation to native compiled Code like C/++.<!-- more -->


So a couple of days later at university, we grabbed a beer and started working on his problem. At first I thought he might have done some optimization on the C code, but the implementation was exactly the same (ignoring some differences in how C/Python is accessing datastructures). But in general nothing serious, just a couple of divisions multiplications and some array accesses. It was just a program going through every pixel of a picture calculating some weights depending on the pixel itself and the previous pixel's weight (so multithreading was not an option).

Then he ran both programs, the C program took 0.206 seconds, the python program almost 45 seconds. I could not believe what I saw.

Next step I suggested to try a different python interpreter. So we installed pypy real quick. At that time he told me he had never heard about pypy.
So we tried to run the program again, this time using pypy. Fortunately it failed due to some Dependence issues with numpy. At that point i noticed he was using numpy. That made me suspicious whether he really needed numpy, and if the problem might be related to numpy.

So we looked through the code again and came to the conclusion that numpy was really not necessary. So he threw out all numpy dependencies and datastructures and replaced it with native python arrays (the whole refactoring took him not even 5 minutes, so he really did not depend on the advanced features that numpy was made to provide)

After the numpy dependency was no issue anymore we ran the program with pypy. The C part still took 0.206 seconds, while the python part went down to 0.821 seconds :-) that's about 4 times slower than C. That is a result like i would have expected. So we got it down from 200 times the time, to about 4 times slower.

So in the end, C was still more viable for the mathematical algorithm itself (who would have thought that? SURPRISE), but unless it is a big project which is supposed to run for a long time, the additional time investment for running an algorithm in python is hard to overcome with the additional time investment to write some algorithm in C instead of python.

What have we learned that day?
Interpreted languages like python or java are often slower than native code. But if you have serious performance issues:
**Don't look** for the problem in the language first, look for problems with your approach first.
**Don't use** libraries for simple things you could do yourself within a couple of lines (numpy just for the sake of conveniently filling an multidimensional array with 0s)
**Look for** different environments to run your programs and check whether the problem could be linked to the chosen environment (try pypy instead of the normal interpreter). **Check** whether you are using appropriate and minimal datatype/structures(boolean for true/false not string, or native python arrays instead of complex numpy datastructures).
And last thing...
**Ask someone** with more higher/lower level experience than you (depending on where you suspect the problem to be), to have a look at your code.

TL;DR
Before you rewrite an algorithm from your favorite high-level language to C, check whether the problem really is in the language or not.

Runtime
Python - numpy: 45 seconds
Python w/o numpy: 26 seconds
Compiled C - 0.2 seconds
PyPy w/o numpy: 0.8 seonds

[caption id="" align="alignnone" width="650"][![With performance issues, blame yourself first, blame the system later](http://www.commitstrip.com/wp-content/uploads/2014/06/Strip-Probl%C3%A8me-dIndex-650-finalenglish.jpg)](http://www.commitstrip.com/en/2014/06/03/the-problem-is-not-the-tool-itself/) With performance issues, blame yourself first, blame the system later[/caption]





