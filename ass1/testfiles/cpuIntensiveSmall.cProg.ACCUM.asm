L1:	GOTO L4
L3:	GOTO L2
L4:	CLEAR
	ADD #0
	STORE i
L5:	BLE i, #15, L6
L7:	CLEAR
	ADD y
	MULT y
	STORE x
L8:	CLEAR
	ADD i
	ADD #1
	STORE i
	GOTO L5
L6:	CLEAR
	ADD #0
	STORE i
L9:	BLE i, #45, L3
L10:	CLEAR
	ADD #7
	MULT y
	STORE x
L11:	CLEAR
	ADD i
	ADD #1
	STORE i
	GOTO L9
	GOTO L3
L2:	
