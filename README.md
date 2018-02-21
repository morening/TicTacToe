## Tic-Tac-Toe
```
1. Based on Minimax Algorithm, promoted with Alpha-Beta Pruning;
2. There are 4 mode like below description;
3. UI implemented by Command line;
```

## Current Implementation:
```
1. Win is the highest priority;
2. Defend should be the second class;
3. 10 points added, if 2 nodes linked as a line;
4. Center has 4 points, the four conner has 3 points and the others has 2 points;
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
The below situation has been fixed by wrapping the evaluated score between Computer and Human.
But I don't know the reason. So I think I have to explore more details about the evaluate function.
H _ _      H _ _       H _ _      H _ _
_ C _  =>  C C _  NOT  _ C _  =>  _ C _
_ _ H      _ _ H       _ _ H      C _ H
```