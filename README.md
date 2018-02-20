## Tic-Tac-Toe
```
1. Based on Minimax Algorithm, promoted with Alpha-Beta Pruning
2. There are many matches, including Human vs Human, Human vs Computer, Computer vs Human and Computer vs Computer
3. UI implemented by Command line
```

## Current Implementation:
```
1. Win is the highest priority
2. Defend should be the second class
3. 10 points added, if 2 nodes linked as a line
4. Center has 4 points, the four conner has 3 points and the others has 2 points
```

## Run like below:
```
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
C _ H

请 人类 选择落子位置
1 3
H _ H
_ C _
C _ H

请 计算机 选择落子位置
H C H
_ C _
C _ H

请 人类 选择落子位置
2 3
H C H
_ C H
C _ H

人类 先手获胜
```

## Promotion in future
```
More TALENT when handle the below status
H _ _      H _ _       H _ _      H _ _
_ C _  =>  C C _  NOT  _ C _  =>  _ C _
_ _ H      _ _ H       _ _ H      C _ H
```