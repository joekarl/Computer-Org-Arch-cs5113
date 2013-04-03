L1:	GOTO L4
L3:	GOTO L2
L4:	STORE i, #0
L5:	BLE i, #15, L6
L7:	MULT x, y, y
L8:	MULT t1, i, #400
	MULT t2, x, #4
	ADD t3, t1, t2
	STORE a(t3), y
L9:	STORE y, x
L10:	STORE y, i
L11:	STORE x, i
L12:	STORE k, i
L13:	STORE z, i
L14:	ADD i, i, #1
	GOTO L5
L6:	STORE i, #0
L15:	BLE i, #45, L3
L16:	MULT x, #7, y
L17:	STORE y, x
L18:	STORE y, i
L19:	STORE x, i
L20:	STORE k, x
L21:	MULT z, y, #57
L22:	MULT t4, k, #400
	MULT t5, z, #4
	ADD t6, t4, t5
	STORE a(t6), y
L23:	ADD i, i, #1
	GOTO L15
	GOTO L3
L2:	
