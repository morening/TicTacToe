## Tic-Tac-Toe
```
1. Based on Minimax Algorithm, promoted with Alpha-Beta Pruning;
2. There are 4 mode like below description;
3. UI implemented by Command line;
```

## Current Implementation:
```
1. If it will win, there will be no other situations;
2. Evaluate the situation after this turn;
3. If there are two same marks and a blank mark in a line, it means a advantage;
4. So it should be one point when evaluated;
5. Computer is MAX layer(which want the min of Human);
6. Human is MIN layer(which want the max of Computer);
```

## Run like below:
```
==========================
==      井字棋 V1.0      ==
==========================
请选择游戏模式：
[1] Human    vs Computer
[2] Computer vs Human
[3] Human    vs Human
[4] Computer vs Computer
1
井字棋比赛开始 人类 VS 计算机
请 人类 选择落子位置
1 1
H _ _
_ _ _
_ _ _

请 计算机 选择落子位置
H _ _
_ C _
_ _ _

请 人类 选择落子位置
3 3
H _ _
_ C _
_ _ H

请 计算机 选择落子位置
H _ _
_ C _
_ C H

请 人类 选择落子位置
1 2
H H _
_ C _
_ C H

请 计算机 选择落子位置
H H C
_ C _
_ C H

请 人类 选择落子位置
3 1
H H C
_ C _
H C H

请 计算机 选择落子位置
H H C
C C _
H C H

请 人类 选择落子位置
2 3
H H C
C C H
H C H

平局！
```

## Promotion in future
```
1. Good UI;
2. Try to implement FIR(Five in A Row)
```