L1:	GOTO L4
L3:	GOTO L2
L4:	STORE i, #0
L5:	BLE i, #15, L6
L7:	MULT x, y, y
L8:	ADD i, i, #1
	GOTO L5
L6:	STORE i, #0
L9:	BLE i, #45, L3
L10:	MULT x, #7, y
L11:	ADD i, i, #1
	GOTO L9
	GOTO L3
L2:	
