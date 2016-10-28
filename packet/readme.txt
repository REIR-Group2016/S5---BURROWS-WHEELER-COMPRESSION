/******************************************************************************
 *  Name:     Grímur Garpsson  
 *  LoginID:  grimur14@ru.is   
 *  Group#:    1
 *
 *  Partner Name: Raquelita Rós Anguilar       
 *  Partner Login ID:    raquelita15@ru.is  
 *  Partner Group#:         HMV
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

                         !!!!!!!NOTE!!!!!!!

The following is an Piazza-answer from the teacher:
,,Það er heimilt að nota þá klasa sem eru notaðir í algs4 forritum, þ.m.t.
Comparator StringBuilder og Character. Ekki hins vegar List eða ArrayList."

/******************************************************************************
 *  List in table format which input files you used to test your program.
 *  Fill in columns for how long your program takes to compress and
 *  decompress these instances (by applying BurrowsWheeler, MoveToFront,
 *  and Huffman in succession). Also, fill in the third column for
 *  the compression ratio (number of bytes in compressed message 
 *  divided by the number of bytes in the message).
 *****************************************************************************/

File     Encoding Time    Decoding time      Compression ratio
------------------------------------------------------------------------
amendments.txt	0,200 sec	0,202 sec	17,9/5,41 (kilobytes) = 3,309
alphanum.txt	0,005 sec	0,007 sec	36/77 = 0,46 (grew bigger!?)
stars.txt	0,005sec	0,005 sec	13/9 = 1,444


/******************************************************************************
 *  Compare the results of your program (compression ratio and running
 *  time) on mobydick.txt to that of the most popular Windows
 *  compression program (pkzip) or the most popular Unix/Mac one (gzip).
 *  If you don't have pkzip, use 7zip and compress using zip format.
 *****************************************************************************/

Our Compression Time:	See known bugs/limitations
Our Compression Ratio:	See known bugs/limitations
pkzip Compression Time:	0,301 sec (I could hardly react fast with my stopwatch, blink and you will miss it)
pkzip Compression Ratio: 416/175 = 2,37

/******************************************************************************
 *  Give the order of growth of the running time of each of the 6
 *  methods as a function of the input size N and the alphabet size R
 *  both in practice (on typical English text inputs) and in theory
 *  (in the worst case), e.g., N, N + R, N log N, N^2, or R N.
 *
 *  Include the time for sorting circular suffixes in the
 *  Burrows-Wheeler encoder.
 *****************************************************************************/

                                      typical            worst
---------------------------------------------------------------------
BurrowsWheeler transform()	      3N + N log N	3N + N log N
BurrowsWheeler inverseTransform()     N^2 + 2N log N	N^2 + 2N log N
MoveToFront encode()		      256N		256 N
MoveToFront decode()		      256N		256 N
Huffman compress()                    N + R log R        N + R log R
Huffman expand()                      N                  N





/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/

Runs out of heap space for larger text files such as aesop and mobydick.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/

Raquelita discussed this project with a friend Mr. Cabrera who helped
her to understand this algorithm and forced her to answer questions regarding this
project.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/

The restriction to use only algs4.jar


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

I did testing and time  complexity.
Me and my partner did together the BurrowsWheeler class.
Raquelita implemented CSA and MTF

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
