package com.nw.edu.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Home
 */
public class TextFieldLimit extends PlainDocument{
    private final int fldLimit;
    
    public TextFieldLimit(int limit){
        this.fldLimit = limit;
    }
    
    @Override
    public void insertString(int startingOffset, String insertStr, AttributeSet attrSet) throws BadLocationException
    {
        if(insertStr == null){
        }else if((getLength() + insertStr.length()) <= this.fldLimit){
            super.insertString(startingOffset, insertStr,attrSet);
        }
    }
    
}
