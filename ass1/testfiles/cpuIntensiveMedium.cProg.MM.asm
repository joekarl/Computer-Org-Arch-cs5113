L1:	GOTO L4
L3:	GOTO L2
L4:	STORE a, #5
L5:	STORE b, #2
L6:	STORE c, #3
L7:	ADD a, b, c
L8:	MULT t1, c, #64
	DIV b, t1, #25
L9:	ADD t2, a, b
	ADD t3, b, #2
	DIV t4, c, t3
	MULT c, t2, t4
L10:	ADD t5, a, b
	ADD t6, b, c
	MULT t7, t5, t6
	ADD t8, c, a
	DIV t9, t7, t8
	SUB t10, b, a
	ADD t11, c, b
	DIV t12, t10, t11
	DIV t13, t9, t12
	MULT a, t13, #56
L11:	ADD a, b, c
L12:	MULT t14, c, #64
	DIV b, t14, #25
L13:	ADD t15, a, b
	ADD t16, b, #2
	DIV t17, c, t16
	MULT c, t15, t17
L14:	ADD t18, a, b
	ADD t19, b, c
	MULT t20, t18, t19
	ADD t21, c, a
	DIV t22, t20, t21
	SUB t23, b, a
	ADD t24, c, b
	DIV t25, t23, t24
	DIV t26, t22, t25
	MULT a, t26, #56
L15:	ADD a, b, c
L16:	MULT t27, c, #64
	DIV b, t27, #25
L17:	ADD t28, a, b
	ADD t29, b, #2
	DIV t30, c, t29
	MULT c, t28, t30
L18:	ADD t31, a, b
	ADD t32, b, c
	MULT t33, t31, t32
	ADD t34, c, a
	DIV t35, t33, t34
	SUB t36, b, a
	ADD t37, c, b
	DIV t38, t36, t37
	DIV t39, t35, t38
	MULT a, t39, #56
L19:	ADD a, b, c
L20:	MULT t40, c, #64
	DIV b, t40, #25
L21:	ADD t41, a, b
	ADD t42, b, #2
	DIV t43, c, t42
	MULT c, t41, t43
L22:	ADD t44, a, b
	ADD t45, b, c
	MULT t46, t44, t45
	ADD t47, c, a
	DIV t48, t46, t47
	SUB t49, b, a
	ADD t50, c, b
	DIV t51, t49, t50
	DIV t52, t48, t51
	MULT a, t52, #56
	GOTO L3
L2:	
