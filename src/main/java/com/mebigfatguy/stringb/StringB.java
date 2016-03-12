package com.mebigfatguy.stringb;

import java.util.ArrayList;
import java.util.List;

public class StringB implements java.io.Serializable, CharSequence {

    private static final long serialVersionUID = -2518739511725819653L;

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
    
    @Override
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
        
        StringB sb = null;
        int offset = 0;
        int pi;
        for (pi = 0; pi < parts.size(); pi++) {
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) > start) {
                if (offset + len > end) {
                    return s.substring(start - offset, end - offset);
                }
                if (sb == null) {
                    sb = new StringB();
                }
                sb.append(s.substring(start - offset));
                offset += len;
                break;
            }
            offset += len;
        }
        
        for (pi++; pi < parts.size(); pi++) {
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) > end) {
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
        
        if (str.length() > 0) {
            parts.add(v);
            length += v.length();
        }
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
    
    public StringB insert(int index, String str) {
        if (index < 0)
            throw new StringIndexOutOfBoundsException(index);
        if (index > length)
            throw new StringIndexOutOfBoundsException(index);
        
        if (str.length() > 0) {
            int offset = 0;
            int pi;
            for (pi = 0; pi < parts.size(); pi++) {
                if (index == offset) {
                    parts.add(pi, str);
                    return this;
                }
                
                String s = parts.get(pi);
                int len = s.length();
                if ((offset + len) > index) {
                    String pre = s.substring(0, index - offset);
                    String post = s.substring(index - offset);
                    parts.set(pi, pre);
                    parts.add(pi+1, str);
                    parts.add(pi+2, post);
                    return this;
                }
                offset += len;
            }
            
            parts.add(str);
        }
        
        return this;
    }
    
    public StringB deleteCharAt(int index) {
        if ((index < 0) || (index >= length)) {
            throw new StringIndexOutOfBoundsException(index);
        }
        
        int offset = 0;
        int pi;
        for (pi = 0; pi < parts.size(); pi++) {
            
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) > index) {
                if (len == 1) {
                    parts.remove(pi);
                    return this;
                }
                
                if (index - offset == 0) {
                    parts.set(pi, s.substring(1));
                    return this;
                } else if  (index - offset == (len-1)) {
                    parts.set(pi, s.substring(0, len - 1));
                    return this;
                } else {
                    String pre = s.substring(0, index - offset);
                    String post = s.substring(index - offset+1);
                    parts.set(pi, pre);
                    parts.add(pi+1, post);
                }
                return this;
            }
            offset += len;
        }
        
        return this;
    }

   

    public StringB delete(int start, int end) {
        if ((start < 0) || (start >= length)) {
            throw new StringIndexOutOfBoundsException(start);
        }
        if ((end < 0) || (end > length)) {
            throw new StringIndexOutOfBoundsException(end);
        }
        if (end < start) {
            throw new StringIndexOutOfBoundsException(end);
        }
        
        int offset = 0;
        int pi;
        for (pi = 0; pi < parts.size(); pi++) {
            
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) > start) {
                if ((offset + len) >= end) {
                    if ((offset == start) && ((offset + len) == end)) {
                        parts.remove(pi);
                        return this;
                    }
                    s = s.substring(0, start - offset);
                    parts.set(pi, s);
                    if ((offset + len) == end) {
                        return this;
                    }
                } else {
                    s = s.substring(0, start);
                    parts.set(pi, s);
                    offset += len;
                }
                break;
            }
            offset += len;
        }
        
        for (pi++; pi < parts.size(); pi++) {
            String s = parts.get(pi);
            int len = s.length();
            if ((offset + len) >= end) {
                s = s.substring(end - offset);
                parts.set(pi, s);
                return this;
            } else {
                parts.remove(pi--);
            }
            offset += len;
        }
        
        return this;
    }


    public StringB replace(int start, int end, String str) {
        delete(start, end);
        insert(start, str);
        return this;
    }

    public StringB insert(int index, char[] str, int offset, int len)
    {
        if (len < 0) {
            throw new StringIndexOutOfBoundsException(len);
        }
        
        if (len > 0) {
            insert(index, new String(str, offset, len));
        }
        return this;
    }

    public StringB insert(int index, Object obj) {
            insert(index, String.valueOf(obj));
            return this;
    }

    public StringB insert(int index, char[] str) {
        insert(index, new String(str));
        return this;
    }

    public StringB insert(int index, CharSequence s) {
            insert(index, (s == null) ? "null" : s.toString());
            return this;
    }

    public StringB insert(int index, CharSequence s,
                                int start, int end)
    {
        insert(index, (s == null) ? "null" : s.subSequence(start, end).toString());
        return this;
    }

    public StringB insert(int index, boolean b) {
        insert(index, String.valueOf(b));
        return this;
    }

    public StringB insert(int index, char c) {
        insert(index, String.valueOf(c));
        return this;
    }

    public StringB insert(int index, int i) {
        insert(index, String.valueOf(i));
        return this;
    }

    public StringB insert(int index, long l) {
        insert(index, String.valueOf(l));
        return this;
    }

    public StringB insert(int index, float f) {
        insert(index, String.valueOf(f));
        return this;
    }

    public StringB insert(int index, double d) {
        insert(index, String.valueOf(d));

        return this;
    }

    public int indexOf(String str) {
       return indexOf(str, 0);
    }

    public int indexOf(String str, int fromIndex) {
        String s = toString();
        return s.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return lastIndexOf(str, length);
    }

    public int lastIndexOf(String str, int fromIndex) {
        String s = toString();
        return s.lastIndexOf(str, fromIndex);
    }
    
    @Override
    public String toString() {
        
        if (parts.size() < 2) {
            if (parts.isEmpty()) {
                return "";
            }
            
            return parts.get(0);
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (String str : parts) {
            sb.append(str);
        }
        
        String combined = sb.toString();
        parts.clear();
        parts.add(combined);
        return combined;
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
