package com.mebigfatguy.stringb;

import java.util.ArrayList;
import java.util.List;

public class StringB implements java.io.Serializable, CharSequence {

    private List<String> parts;
    private int length;
    
    public StringB() {
        parts = new ArrayList<>();
        length = 0;
    }
    
    @Override
    public int length() {
        return length;
    }
    
    @Override
    public char charAt(int index) {
        if ((index < 0) || (index >= length))
            throw new StringIndexOutOfBoundsException(index);
        
        int offset = 0;
        for (String s : parts) {
            int len = s.length();
            if ((offset + len) > index) {
                return s.charAt(index - offset);
            }
            offset += len;
        }
        throw new StringIndexOutOfBoundsException(index);
    }
    
    public CharSequence subSequence(int start, int end) {
        return substring(start, end);
    }
    
    public String substring(int start) {
        return substring(start, length);
    }
    
    public String substring(int start, int end) {
        if (start < 0)
            throw new StringIndexOutOfBoundsException(start);
        if (end > length)
            throw new StringIndexOutOfBoundsException(end);
        if (start > end)
            throw new StringIndexOutOfBoundsException(end - start);
        
        StringBuilder sb = new StringBuilder(end - start);
        int offset = 0;
        int pi;
        for (pi = 0; pi < parts.size(); pi++) {
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) < start) {
                if (offset + len < end) {
                    return s.substring(start - offset, end - offset);
                }
                sb.append(s.substring(start - offset));
                offset += len;
                break;
            }
            offset += len;
        }
        for (;pi < parts.size(); pi++) {
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) < end) {
                sb.append(s.substring(0, end - offset));
                return sb.toString();
            } else {
                sb.append(s);
            }
            offset += len;
        }
        
        return sb.toString();
    }
    
    public StringB append(Object obj) {
        String v = String.valueOf(obj);
        parts.add(v);
        length += v.length();
        return this;
    }
    
    public StringB append(String str) {
        String v = (str == null) ? "null" : str;
        parts.add(v);
        length += v.length();
        return this;
    }
    
    public StringB append(CharSequence s) {
        String v = s == null ? "null" : s.toString();
        parts.add(v);
        length += v.length();
        return this;
    }
    
    public StringB append(char[] str) {
        String v = String.valueOf(str);
        parts.add(v);
        length += v.length();
        return this;
    }
    
    public StringB append(char[] str, int offset, int len) {
        String v = new String(str, offset, len);
        parts.add(v);
        length += v.length();
        return this;
    }
    
    public StringB append(boolean b) {
        String v = String.valueOf(b);
        parts.add(v);
        length += v.length();
        return this;
    }

    public StringB append(char c) {
        String v = String.valueOf(c);
        parts.add(v);
        length += v.length();
        return this;
    }

    public StringB append(int i) {
        String v = String.valueOf(i);
        parts.add(v);
        length += v.length();
        return this;
    }

    public StringB append(long lng) {
        String v = String.valueOf(lng);
        parts.add(v);
        length += v.length();
        return this;
    }

    public StringB append(float f) {
        String v = String.valueOf(f);
        parts.add(v);
        length += v.length();
        return this;
    }

    public StringB append(double d) {
        String v = String.valueOf(d);
        parts.add(v);
        length += v.length();
        return this;
    }
/*    
    public StringB delete(int start, int end) {
        if ((start < 0) || (start >= length)) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if ((end < 0) || (end >= length)) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (end < start) {
            throw new StringIndexOutOfBoundsException(end);
        }
        
        super.delete(start, end);
        return this;
    }

    public StringB deleteCharAt(int index) {
        if ((index < 0) || (index >= length)) {
            throw new StringIndexOutOfBoundsException(start);
        }
        super.deleteCharAt(index);
        return this;
    }

    public StringB replace(int start, int end, String str) {
        super.replace(start, end, str);
        return this;
    }

    public StringB insert(int index, char[] str, int offset, int len)
    {
        super.insert(index, str, offset, len);
        return this;
    }

    public StringB insert(int offset, Object obj) {
            super.insert(offset, obj);
            return this;
    }

    public StringB insert(int offset, String str) {
        super.insert(offset, str);
        return this;
    }

    public StringB insert(int offset, char[] str) {
        super.insert(offset, str);
        return this;
    }

    public StringB insert(int dstOffset, CharSequence s) {
            super.insert(dstOffset, s);
            return this;
    }

    public StringB insert(int dstOffset, CharSequence s,
                                int start, int end)
    {
        super.insert(dstOffset, s, start, end);
        return this;
    }

    public StringB insert(int offset, boolean b) {
        super.insert(offset, b);
        return this;
    }

    public StringB insert(int offset, char c) {
        super.insert(offset, c);
        return this;
    }

    public StringB insert(int offset, int i) {
        super.insert(offset, i);
        return this;
    }

    public StringB insert(int offset, long l) {
        super.insert(offset, l);
        return this;
    }

    public StringB insert(int offset, float f) {
        super.insert(offset, f);
        return this;
    }

    public StringB insert(int offset, double d) {
        super.insert(offset, d);
        return this;
    }

    public int indexOf(String str) {
        int offset = 0;
        
        for (String s : parts) {
            int index = s.indexOf(str);
            if (index >= 0) {
                return offset + index;
            }
            offset += s.length();
        }
        return -1;
    }

    public int indexOf(String str, int fromIndex) {
        return super.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        int offset = length;
        
        for (int i = parts.size() - 1; i >= 0; i--) {
            String s = parts.get(i);
            int index = s.lastIndexOf(str);
            if (index >= 0) {
                return offset - (s.length() - index);
            }
            offset -= s.length();
        }
        return -1;
    }

    public int lastIndexOf(String str, int fromIndex) {
        return super.lastIndexOf(str, fromIndex);
    }

    */
    
    
    public String toString() {
        StringBuilder sb = new StringBuilder(length);
        for (String str : parts) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
        s.defaultWriteObject();
        s.writeInt(length);
        s.writeInt(parts.size());
        for (String str : parts) {
            s.writeObject(str);
        }
    }

    private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();
        length = s.readInt();
        int numParts = s.readInt();
        for (int i = 0; i < numParts; i++) {
            parts.add((String) s.readObject());
        }
    }
}
