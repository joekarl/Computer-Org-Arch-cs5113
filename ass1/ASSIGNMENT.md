##Assignment 1

####Performance Evaluation of ISAs

This assignment is to simulate and evaluate the performance
of various ISAs as provided in the homework assignment (1).

The simulation is to be performed at the assembly code level 
as their instruction formats are laid out in the homework assignement (1).
In order to facilitate the simulation you are to write 
a mini-compiler for each ISA, i.e., Memory-Memory Architecture,
Accumulator Architecture, Stack Architecture, and Load-Store Architecture.

Each ISA to be simulated is the same as described as in the homework (1);
note that the # of registers, the size of memory in bytes, the # of operations
available have to be simulated as a variable.

The operations; memory addressing modes
and log-blocks to be simulated are as follows:

* Operations: assignment, add, sub, 
* Memory addressing mode: register direct, base register, and offset
* Logic-blocks: if-then-else, while, switch-case, procedure calls


The instructions to be simulated is based on the 
the operations, memory addressing mode and logic blocks to be simulated.
The mini-compiler for each ISA is to have limited instructions available:
such as :

* add
* sub
* mult
* div
* load
* store
* goto
* cmp 
* and anything else as necessary.

The formats of the instructions are to be determined based on a few
design variables such as the # of registers, the size of memory and
the # of operations, and etc. as applicable.
Each mini-compiler has to carry an appropriate set of instructions that
are equivalent to one or some or all of the above instructions 
as applicable to each specific ISA.
For example, the Stack Machine can carry the following instructions:

* push (load)
* pop (store)
* add
* sub
* mult
* div
* goto
* cmp

Lastly, your mini-compiler is to take the CPI for each defined instruction
as a variable.

The input to the simulator is a segment of C program along with 
the CPI of each instruction:

The basic sample segments of C code are:

1. A = (B + C) * D - E;
2. F = (G + H) - (I + J);
3. G = H + A[I];
4. If (I == J) F = G + H;
   Else F = G - H;
5. Loop: G = G + A[I];
         I = I + J;
         If (I != H) Goto Loop;
6. If (G < H) Goto Less;
7. While (save[I] == K)
         I = I + J;

First, test your compiler with the above simple codes; then
by using them, generate various types of inputs such that

1. Memory access intensive program (about 90% memory access instructions)
2. CPU access intensive program (about 90% CPU operation instructions)
3. Balanced (such as 50/50).
at a small scale (about 100 instructions), a medium scale 
(about 200 instructions) and a large scale (about 500 instructions).

The outputs from the simulator are:

1. Resulting assembly code from your mini-compiler and the source code.
2. Instruction count.
3. Size of the resulting machine code in bits (or bytes).
4. # of memory access.
5. Overall CPI value of the architecture per input program 
on each architecture.
6. Take the frequency of each instruction and attempt a 10% (this ratio
has to be given as a variable) speedup
on the highest, medium, or lowest frequency instruction; and 
the load-store, ALU or branch intructions. So, 2 sets of performance
analyses are to be reported as mentioned; and then extend the simulation
for 20%, 30%, 50%, 70%. Finally, given all the design variables,
simulate and demonstrate spped up on which instruction will
return the highest overall speedup for each 10%, 20%, 30%, 50% and 70%
speedup.

Write a report on the above observation in which
a comparative study on the ISAs is conducted with respect to
each performance metric of interest versus each combination of
possible patterns; and conclude which ISA is the best 
along with a justification both in theoretical and empirical
standpoints.

####Grading Guideline:

1. 60% on mini-compiler writing; e.g., 10% on each mini-compiler.
2. 40% on performance analysis.

NOTICE: The specification of the assignment might undergo
incremental modification or clarification, so please check back the
description when told to do so.

N. Park

--------------------------------------------------------------------------------

####Handin instructions:

To handin the assignment you must be logged into your CSA account
and type:

	$handin cs5113 pgm01 <filename1> <filename2> ...

To check the status of your individual handin folder type:

	$handin cs5113 pgm01

Each time you submit a file of the same name, the previous will
be overwriten by the new version. Multiple files may be submitted.
Each student, upon submission, will have a folder created for their
individual files. All programs MUST compile and run on CSA and 
each program must have explicit compile and run instructions included
in the main source code file or in a separate file appropriately
named. 

Students who have a problem handing in programs should email the TA. 
If this occures, DO NOT modify your files in CSA as we may ask to 
see the time stamp on the files that were submitted if they must
be submitted late. 

N. Park
