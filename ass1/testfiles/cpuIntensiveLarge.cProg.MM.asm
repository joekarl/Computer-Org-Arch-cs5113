L1:	GOTO L4
L3:	GOTO L2
L4:	STORE i, #0
L5:	BLE i, #15, L6
L7:	MULT x, y, y
L8:	ADD i, i, #1
	GOTO L5
L6:	STORE i, #0
L9:	BLE i, #45, L10
L11:	MULT x, #7, y
L12:	ADD i, i, #1
L13:	MULT t1, i, #8
	ADD t2, x, y
	MULT t3, i, f
	SUB t4, t2, t3
	STORE fArray(t1), t4
	GOTO L9
L10:	BLE x, #15, L16
L15:	MULT x, y, y
L17:	ADD i, i, #1
	GOTO L14
L16:	MULT t5, #5, 60
	ADD t6, #50, t5
	SUB t7, t6, #94
	ADD t8, x, y
	SUB t9, t8, i
	SUB t10, #0.560000, 8
	MULT t11, t9, t10
	DIV f, t7, t11
L18:	ADD i, i, #1
L14:	BLE i, #15, L19
L20:	MULT x, y, y
L21:	ADD i, i, #1
	GOTO L14
L19:	STORE i, #0
L22:	BLE i, #45, L23
L24:	MULT x, #7, y
L25:	ADD i, i, #1
L26:	MULT t12, i, #8
	ADD t13, x, y
	MULT t14, i, f
	SUB t15, t13, t14
	STORE fArray(t12), t15
	GOTO L22
L23:	BLE x, #15, L29
L28:	MULT x, y, y
L30:	ADD i, i, #1
	GOTO L27
L29:	MULT t16, #5, 60
	ADD t17, #50, t16
	SUB t18, t17, #94
	ADD t19, x, y
	SUB t20, t19, i
	SUB t21, #0.560000, 8
	MULT t22, t20, t21
	DIV f, t18, t22
L31:	ADD i, i, #1
L27:	BLE i, #15, L32
L33:	MULT x, y, y
L34:	ADD i, i, #1
	GOTO L27
L32:	STORE i, #0
L35:	BLE i, #45, L36
L37:	MULT x, #7, y
L38:	ADD i, i, #1
L39:	MULT t23, i, #8
	ADD t24, x, y
	MULT t25, i, f
	SUB t26, t24, t25
	STORE fArray(t23), t26
	GOTO L35
L36:	BLE x, #15, L42
L41:	MULT x, y, y
L43:	ADD i, i, #1
	GOTO L40
L42:	MULT t27, #5, 60
	ADD t28, #50, t27
	SUB t29, t28, #94
	ADD t30, x, y
	SUB t31, t30, i
	SUB t32, #0.560000, 8
	MULT t33, t31, t32
	DIV f, t29, t33
L44:	ADD i, i, #1
L40:	BLE i, #15, L45
L46:	MULT x, y, y
L47:	ADD i, i, #1
	GOTO L40
L45:	STORE i, #0
L48:	BLE i, #45, L49
L50:	MULT x, #7, y
L51:	ADD i, i, #1
L52:	MULT t34, i, #8
	ADD t35, x, y
	MULT t36, i, f
	SUB t37, t35, t36
	STORE fArray(t34), t37
	GOTO L48
L49:	BLE x, #15, L55
L54:	MULT x, y, y
L56:	ADD i, i, #1
	GOTO L53
L55:	MULT t38, #5, 60
	ADD t39, #50, t38
	SUB t40, t39, #94
	ADD t41, x, y
	SUB t42, t41, i
	SUB t43, #0.560000, 8
	MULT t44, t42, t43
	DIV f, t40, t44
L57:	ADD i, i, #1
L53:	BLE i, #15, L58
L59:	MULT x, y, y
L60:	ADD i, i, #1
	GOTO L53
L58:	STORE i, #0
L61:	BLE i, #45, L62
L63:	MULT x, #7, y
L64:	ADD i, i, #1
L65:	MULT t45, i, #8
	ADD t46, x, y
	MULT t47, i, f
	SUB t48, t46, t47
	STORE fArray(t45), t48
	GOTO L61
L62:	BLE x, #15, L67
L66:	MULT x, y, y
L68:	ADD i, i, #1
	GOTO L3
L67:	MULT t49, #5, 60
	ADD t50, #50, t49
	SUB t51, t50, #94
	ADD t52, x, y
	SUB t53, t52, i
	SUB t54, #0.560000, 8
	MULT t55, t53, t54
	DIV f, t51, t55
L69:	ADD i, i, #1
	GOTO L3
L2:	
