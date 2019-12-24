package com.apple.mywork;
/**
Problem Two:

Alice and Bob  devised a new way of encrypting messages. They first decide  on the number of columns and write the message (letters only) down the columns, padding with extra random letters so as to make a rectangular array of letters. For example, if the message is “There’s no place like home on a snowy night” and there are five columns, Alice would write down

t o i o y
h p k n n
e l e a i
r a h s g
e c o n h
s e m o t
n l e w x

Note that 'Alice' includes only letters and writes them all in lower case. In this example, Alice used the character ‘x’ to pad the message out to make a rectangle, although he could have used any letter. Alice then sends the message to Bob by writing the letters in each row, alternating left-to-right and right-to-left. So, the above would be encrypted as

toioynnkpheleaigshareconhtomesnlewx
Your job is to recover for Bob the original message (along with any extra padding letters) from the encrypted one.

Input format
An integer indicating the number of columns used and a string lower case letters.


Example

1.)

Input
{5, toioynnkpheleaigshareconhtomesnlewx}

Output
theresnoplacelikehomeonasnowynightx

2.)

Input
{3, ttyohhieneesiaabss}

Output:
thisistheeasyoneab

**/

public class Decryption {
    /*
     * Decrypt the input string.
     * @param : columns --An integer indicating the number of columns used and a string lower case letters
     * @return: encrypt --An String encrypted
     */
    public String decrypt(int columns , String encrypt){

        if(encrypt == null || columns <=0 ){
            return encrypt;
        }
        if(columns >0 && encrypt.length() % columns !=0){
            int dif = columns - encrypt.length()% columns;
            for(int i = 0 ; i < dif ; i++){
                encrypt= encrypt +'x';
            }
        }

        //compute the rows of the rectangle
        int len = encrypt.length();
        int rows = len/columns;

        StringBuilder res = new StringBuilder();

        //original input rectangle
        char[][]  chs = new char[rows][columns];

        int old = 0;
        int ne = 1;
        int r = 0;

        //build the original rectangle in a left->right, then right->left recursive way.
        for(int i = 0 ; i+columns<= len ; i+=columns){
            old = ne;
            ne = 1-old;
            char[] s= encrypt.substring(i,i+columns).toCharArray();

            if(ne <1){
                   System.arraycopy(s,0,chs[r],0,columns);
            }else{
                for(int k =s.length ; k>0; k--){
                    chs[r][k-1] = s[s.length-k];
                }
            }

            r++;

        }

        //out put the decrypt string by concat each column of the char array
        for(int k = 0 ; k<columns; k++){
            for(int i = 0 ; i<rows; i++){
                res.append(chs[i][k]);
            }
        }

        return res.toString();

    }

}
