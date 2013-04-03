L1:	GOTO L4
L3:	GOTO L2
L4:	ADD c, c, c
L5:	ADD t1, a, b
	ADD t2, t1, c
	SUB t3, t2, b
	SUB c, t3, a
L6:	STORE a, b
L7:	STORE b, a
L8:	STORE a, b
L9:	MULT t4, #1, 4
	STORE x(t4), a
L10:	MULT t5, #1, 4
	STORE y(t5), b
L11:	MULT t6, a, #4
	MULT t7, a, #4
	STORE t8, x(t7)
	MULT t9, b, #4
	STORE t10, y(t9)
	ADD t11, t8, t10
	STORE z(t6), t11
L12:	STORE b, a
L13:	STORE a, b
L14:	STORE b, a
L15:	STORE a, b
L16:	STORE b, a
L17:	STORE a, b
L18:	STORE b, a
L19:	MULT t12, a, #4
	STORE x(t12), a
L20:	MULT t13, b, #4
	STORE y(t13), b
L21:	MULT t14, a, #4
	MULT t15, a, #4
	STORE t16, x(t15)
	MULT t17, b, #4
	STORE t18, y(t17)
	ADD t19, t16, t18
	STORE z(t14), t19
L22:	STORE a, b
L23:	STORE b, a
L24:	STORE a, b
L25:	STORE b, a
L26:	STORE a, b
L27:	STORE b, a
L28:	STORE a, b
L29:	STORE b, a
L30:	STORE a, b
L31:	STORE b, a
L32:	STORE a, b
L33:	STORE b, a
L34:	STORE a, b
L35:	STORE b, a
L36:	STORE a, b
L37:	STORE b, a
L38:	STORE a, b
L39:	MULT t20, a, #4
	STORE x(t20), a
L40:	MULT t21, b, #4
	STORE y(t21), b
L41:	MULT t22, a, #4
	MULT t23, a, #4
	STORE t24, x(t23)
	MULT t25, b, #4
	STORE t26, y(t25)
	ADD t27, t24, t26
	STORE z(t22), t27
L42:	STORE b, a
L43:	STORE a, b
L44:	STORE b, a
L45:	STORE a, b
L46:	STORE b, a
L47:	STORE a, b
L48:	STORE b, a
L49:	MULT t28, a, #4
	STORE x(t28), a
L50:	MULT t29, b, #4
	STORE y(t29), b
L51:	MULT t30, a, #4
	MULT t31, a, #4
	STORE t32, x(t31)
	MULT t33, b, #4
	STORE t34, y(t33)
	ADD t35, t32, t34
	STORE z(t30), t35
L52:	STORE a, b
L53:	STORE b, a
L54:	STORE a, b
L55:	STORE b, a
L56:	STORE a, b
L57:	STORE b, a
L58:	STORE a, b
L59:	STORE b, a
L60:	STORE a, b
L61:	STORE b, a
L62:	STORE a, b
L63:	MULT t36, a, #4
	STORE x(t36), a
L64:	MULT t37, b, #4
	STORE y(t37), b
L65:	MULT t38, a, #4
	MULT t39, a, #4
	STORE t40, x(t39)
	MULT t41, b, #4
	STORE t42, y(t41)
	ADD t43, t40, t42
	STORE z(t38), t43
L66:	STORE b, a
L67:	STORE a, b
L68:	STORE b, a
L69:	MULT t44, a, #4
	STORE x(t44), a
L70:	MULT t45, b, #4
	STORE y(t45), b
L71:	MULT t46, a, #4
	MULT t47, a, #4
	STORE t48, x(t47)
	MULT t49, b, #4
	STORE t50, y(t49)
	ADD t51, t48, t50
	STORE z(t46), t51
L72:	STORE a, b
L73:	STORE b, a
L74:	STORE a, b
L75:	STORE b, a
L76:	STORE a, b
L77:	MULT t52, a, #4
	STORE x(t52), a
L78:	MULT t53, b, #4
	STORE y(t53), b
L79:	MULT t54, a, #4
	MULT t55, a, #4
	STORE t56, x(t55)
	MULT t57, b, #4
	STORE t58, y(t57)
	ADD t59, t56, t58
	STORE z(t54), t59
L80:	STORE b, a
L81:	STORE a, b
L82:	STORE b, a
L83:	STORE a, b
L84:	STORE b, a
L85:	STORE a, b
L86:	STORE b, a
L87:	STORE a, b
L88:	STORE b, a
L89:	STORE a, b
L90:	STORE b, a
L91:	STORE a, b
L92:	STORE b, a
L93:	MULT t60, a, #4
	STORE x(t60), a
L94:	MULT t61, b, #4
	STORE y(t61), b
L95:	MULT t62, a, #4
	MULT t63, a, #4
	STORE t64, x(t63)
	MULT t65, b, #4
	STORE t66, y(t65)
	ADD t67, t64, t66
	STORE z(t62), t67
L96:	STORE a, b
L97:	STORE b, a
L98:	STORE a, b
L99:	STORE b, a
L100:	STORE a, b
L101:	STORE b, a
L102:	MULT t68, a, #4
	STORE x(t68), a
L103:	MULT t69, b, #4
	STORE y(t69), b
L104:	MULT t70, a, #4
	MULT t71, a, #4
	STORE t72, x(t71)
	MULT t73, b, #4
	STORE t74, y(t73)
	ADD t75, t72, t74
	STORE z(t70), t75
L105:	STORE a, b
L106:	STORE b, a
L107:	STORE a, b
L108:	MULT t76, a, #4
	STORE x(t76), a
L109:	MULT t77, a, #4
	STORE x(t77), a
L110:	MULT t78, b, #4
	STORE y(t78), b
L111:	MULT t79, a, #4
	MULT t80, a, #4
	STORE t81, x(t80)
	MULT t82, b, #4
	STORE t83, y(t82)
	ADD t84, t81, t83
	STORE z(t79), t84
L112:	MULT t85, b, #4
	STORE y(t85), b
L113:	MULT t86, a, #4
	MULT t87, a, #4
	STORE t88, x(t87)
	MULT t89, b, #4
	STORE t90, y(t89)
	ADD t91, t88, t90
	STORE z(t86), t91
L114:	STORE b, a
L115:	MULT t92, a, #4
	STORE x(t92), a
L116:	MULT t93, b, #4
	STORE y(t93), b
L117:	MULT t94, a, #4
	MULT t95, a, #4
	STORE t96, x(t95)
	MULT t97, b, #4
	STORE t98, y(t97)
	ADD t99, t96, t98
	STORE z(t94), t99
L118:	STORE a, b
L119:	STORE b, a
L120:	STORE a, b
L121:	MULT t100, a, #4
	STORE x(t100), a
L122:	MULT t101, b, #4
	STORE y(t101), b
L123:	MULT t102, a, #4
	MULT t103, a, #4
	STORE t104, x(t103)
	MULT t105, b, #4
	STORE t106, y(t105)
	ADD t107, t104, t106
	STORE z(t102), t107
L124:	STORE b, a
	GOTO L3
L2:	
