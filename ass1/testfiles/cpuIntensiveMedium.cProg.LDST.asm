L1:	GOTO L4
L3:	GOTO L2
L4:	STORE a, #5
L5:	STORE b, #2
L6:	STORE c, #3
L7:	LOAD R0, b
	LOAD R1, c
	STORE a, R0
L8:	LOAD R0, c
	MULT R0, R0, #64
	STORE t1, R0
	LOAD R0, t1
	DIV R0, R0, #25
	STORE b, R0
L9:	LOAD R0, a
	LOAD R1, b
	STORE t2, R0
	LOAD R0, b
	ADD R0, R0, #2
	STORE t3, R0
	LOAD R0, c
	LOAD R1, t3
	STORE t4, R0
	LOAD R0, t2
	LOAD R1, t4
	STORE c, R0
L10:	LOAD R0, a
	LOAD R1, b
	STORE t5, R0
	LOAD R0, b
	LOAD R1, c
	STORE t6, R0
	LOAD R0, t5
	LOAD R1, t6
	STORE t7, R0
	LOAD R0, c
	LOAD R1, a
	STORE t8, R0
	LOAD R0, t7
	LOAD R1, t8
	STORE t9, R0
	LOAD R0, b
	LOAD R1, a
	STORE t10, R0
	LOAD R0, c
	LOAD R1, b
	STORE t11, R0
	LOAD R0, t10
	LOAD R1, t11
	STORE t12, R0
	LOAD R0, t9
	LOAD R1, t12
	STORE t13, R0
	LOAD R0, t13
	MULT R0, R0, #56
	STORE a, R0
L11:	LOAD R0, b
	LOAD R1, c
	STORE a, R0
L12:	LOAD R0, c
	MULT R0, R0, #64
	STORE t14, R0
	LOAD R0, t14
	DIV R0, R0, #25
	STORE b, R0
L13:	LOAD R0, a
	LOAD R1, b
	STORE t15, R0
	LOAD R0, b
	ADD R0, R0, #2
	STORE t16, R0
	LOAD R0, c
	LOAD R1, t16
	STORE t17, R0
	LOAD R0, t15
	LOAD R1, t17
	STORE c, R0
L14:	LOAD R0, a
	LOAD R1, b
	STORE t18, R0
	LOAD R0, b
	LOAD R1, c
	STORE t19, R0
	LOAD R0, t18
	LOAD R1, t19
	STORE t20, R0
	LOAD R0, c
	LOAD R1, a
	STORE t21, R0
	LOAD R0, t20
	LOAD R1, t21
	STORE t22, R0
	LOAD R0, b
	LOAD R1, a
	STORE t23, R0
	LOAD R0, c
	LOAD R1, b
	STORE t24, R0
	LOAD R0, t23
	LOAD R1, t24
	STORE t25, R0
	LOAD R0, t22
	LOAD R1, t25
	STORE t26, R0
	LOAD R0, t26
	MULT R0, R0, #56
	STORE a, R0
L15:	LOAD R0, b
	LOAD R1, c
	STORE a, R0
L16:	LOAD R0, c
	MULT R0, R0, #64
	STORE t27, R0
	LOAD R0, t27
	DIV R0, R0, #25
	STORE b, R0
L17:	LOAD R0, a
	LOAD R1, b
	STORE t28, R0
	LOAD R0, b
	ADD R0, R0, #2
	STORE t29, R0
	LOAD R0, c
	LOAD R1, t29
	STORE t30, R0
	LOAD R0, t28
	LOAD R1, t30
	STORE c, R0
L18:	LOAD R0, a
	LOAD R1, b
	STORE t31, R0
	LOAD R0, b
	LOAD R1, c
	STORE t32, R0
	LOAD R0, t31
	LOAD R1, t32
	STORE t33, R0
	LOAD R0, c
	LOAD R1, a
	STORE t34, R0
	LOAD R0, t33
	LOAD R1, t34
	STORE t35, R0
	LOAD R0, b
	LOAD R1, a
	STORE t36, R0
	LOAD R0, c
	LOAD R1, b
	STORE t37, R0
	LOAD R0, t36
	LOAD R1, t37
	STORE t38, R0
	LOAD R0, t35
	LOAD R1, t38
	STORE t39, R0
	LOAD R0, t39
	MULT R0, R0, #56
	STORE a, R0
L19:	LOAD R0, b
	LOAD R1, c
	STORE a, R0
L20:	LOAD R0, c
	MULT R0, R0, #64
	STORE t40, R0
	LOAD R0, t40
	DIV R0, R0, #25
	STORE b, R0
L21:	LOAD R0, a
	LOAD R1, b
	STORE t41, R0
	LOAD R0, b
	ADD R0, R0, #2
	STORE t42, R0
	LOAD R0, c
	LOAD R1, t42
	STORE t43, R0
	LOAD R0, t41
	LOAD R1, t43
	STORE c, R0
L22:	LOAD R0, a
	LOAD R1, b
	STORE t44, R0
	LOAD R0, b
	LOAD R1, c
	STORE t45, R0
	LOAD R0, t44
	LOAD R1, t45
	STORE t46, R0
	LOAD R0, c
	LOAD R1, a
	STORE t47, R0
	LOAD R0, t46
	LOAD R1, t47
	STORE t48, R0
	LOAD R0, b
	LOAD R1, a
	STORE t49, R0
	LOAD R0, c
	LOAD R1, b
	STORE t50, R0
	LOAD R0, t49
	LOAD R1, t50
	STORE t51, R0
	LOAD R0, t48
	LOAD R1, t51
	STORE t52, R0
	LOAD R0, t52
	MULT R0, R0, #56
	STORE a, R0
	GOTO L3
L2:	
