L1:	GOTO L4
L3:	GOTO L2
L4:	PUSH #5
	POP a
L5:	PUSH #2
	POP b
L6:	PUSH #3
	POP c
L7:	CLEAR
	PUSH b
	PUSH c
	ADD
	POP a
L8:	PUSH c
	PUSH #64
	MULT
	POP t1
	PUSH t1
	PUSH #25
	DIV
	POP b
L9:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t2
	PUSH b
	PUSH #2
	ADD
	POP t3
	CLEAR
	PUSH c
	PUSH t3
	DIV
	POP t4
	CLEAR
	PUSH t2
	PUSH t4
	MULT
	POP c
L10:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t5
	CLEAR
	PUSH b
	PUSH c
	ADD
	POP t6
	CLEAR
	PUSH t5
	PUSH t6
	MULT
	POP t7
	CLEAR
	PUSH c
	PUSH a
	ADD
	POP t8
	CLEAR
	PUSH t7
	PUSH t8
	DIV
	POP t9
	CLEAR
	PUSH b
	PUSH a
	SUB
	POP t10
	CLEAR
	PUSH c
	PUSH b
	ADD
	POP t11
	CLEAR
	PUSH t10
	PUSH t11
	DIV
	POP t12
	CLEAR
	PUSH t9
	PUSH t12
	DIV
	POP t13
	PUSH t13
	PUSH #56
	MULT
	POP a
L11:	CLEAR
	PUSH b
	PUSH c
	ADD
	POP a
L12:	PUSH c
	PUSH #64
	MULT
	POP t14
	PUSH t14
	PUSH #25
	DIV
	POP b
L13:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t15
	PUSH b
	PUSH #2
	ADD
	POP t16
	CLEAR
	PUSH c
	PUSH t16
	DIV
	POP t17
	CLEAR
	PUSH t15
	PUSH t17
	MULT
	POP c
L14:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t18
	CLEAR
	PUSH b
	PUSH c
	ADD
	POP t19
	CLEAR
	PUSH t18
	PUSH t19
	MULT
	POP t20
	CLEAR
	PUSH c
	PUSH a
	ADD
	POP t21
	CLEAR
	PUSH t20
	PUSH t21
	DIV
	POP t22
	CLEAR
	PUSH b
	PUSH a
	SUB
	POP t23
	CLEAR
	PUSH c
	PUSH b
	ADD
	POP t24
	CLEAR
	PUSH t23
	PUSH t24
	DIV
	POP t25
	CLEAR
	PUSH t22
	PUSH t25
	DIV
	POP t26
	PUSH t26
	PUSH #56
	MULT
	POP a
L15:	CLEAR
	PUSH b
	PUSH c
	ADD
	POP a
L16:	PUSH c
	PUSH #64
	MULT
	POP t27
	PUSH t27
	PUSH #25
	DIV
	POP b
L17:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t28
	PUSH b
	PUSH #2
	ADD
	POP t29
	CLEAR
	PUSH c
	PUSH t29
	DIV
	POP t30
	CLEAR
	PUSH t28
	PUSH t30
	MULT
	POP c
L18:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t31
	CLEAR
	PUSH b
	PUSH c
	ADD
	POP t32
	CLEAR
	PUSH t31
	PUSH t32
	MULT
	POP t33
	CLEAR
	PUSH c
	PUSH a
	ADD
	POP t34
	CLEAR
	PUSH t33
	PUSH t34
	DIV
	POP t35
	CLEAR
	PUSH b
	PUSH a
	SUB
	POP t36
	CLEAR
	PUSH c
	PUSH b
	ADD
	POP t37
	CLEAR
	PUSH t36
	PUSH t37
	DIV
	POP t38
	CLEAR
	PUSH t35
	PUSH t38
	DIV
	POP t39
	PUSH t39
	PUSH #56
	MULT
	POP a
L19:	CLEAR
	PUSH b
	PUSH c
	ADD
	POP a
L20:	PUSH c
	PUSH #64
	MULT
	POP t40
	PUSH t40
	PUSH #25
	DIV
	POP b
L21:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t41
	PUSH b
	PUSH #2
	ADD
	POP t42
	CLEAR
	PUSH c
	PUSH t42
	DIV
	POP t43
	CLEAR
	PUSH t41
	PUSH t43
	MULT
	POP c
L22:	CLEAR
	PUSH a
	PUSH b
	ADD
	POP t44
	CLEAR
	PUSH b
	PUSH c
	ADD
	POP t45
	CLEAR
	PUSH t44
	PUSH t45
	MULT
	POP t46
	CLEAR
	PUSH c
	PUSH a
	ADD
	POP t47
	CLEAR
	PUSH t46
	PUSH t47
	DIV
	POP t48
	CLEAR
	PUSH b
	PUSH a
	SUB
	POP t49
	CLEAR
	PUSH c
	PUSH b
	ADD
	POP t50
	CLEAR
	PUSH t49
	PUSH t50
	DIV
	POP t51
	CLEAR
	PUSH t48
	PUSH t51
	DIV
	POP t52
	PUSH t52
	PUSH #56
	MULT
	POP a
	GOTO L3
L2:	
