# OOP-Game-S16
Objected Oriented Programming Spring 2016, Iteration 2.

**Team KANYE 2020**
- Sergio Puleri
- Bradley Treuherz
- Denzel Mathew
- Ben Chen
- David Peguero
- Austin Seber
- David Yeung


## How to run
- Clone the repository if you do not have the source code, with:
```bash
git clone https://github.com/kanye2021/OOP-Game-S162.git
```
- Run the following command at the root directory to compile and run the game:   
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt && cp -R src bin/ && cd bin && java RunGame && cd .. && rm -rf bin
```
- To just compile the game run:
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt && cp -R src bin/
```
- Then, to run the game, run:
```bash
cd bin && java RunGame && cd ..
```
